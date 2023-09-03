package com.practice.notion.persist;

import com.practice.notion.persist.entity.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PageRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcTemplate simpleTemplate;

    public PageRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcTemplate simpleTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleTemplate = simpleTemplate;
    }

    public Long save(Page page) {
        return savePage(page);
    }

    private Long savePage(Page page) {
        String pageSql = "INSERT INTO page (title, parent_page_id, content) VALUE (:title, :parentPageId, :content);";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", page.getTitle())
                .addValue("parentPageId", page.getParentPage())
                .addValue("content", page.getContent());
        jdbcTemplate.update(pageSql, parameterSource);

        String idSql = "SELECT MAX(id) FROM page;";
        return simpleTemplate.queryForObject(idSql, Long.class);
    }

    public Optional<Page> findById(Long id) {
        String sql = "SELECT id, title, parent_page_id, content FROM page WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        Page page = jdbcTemplate.queryForObject(sql, params, new PageMapper());

        if (page.getParentPage() != null && page.getParentPage() != 0) {
            Page parentPage = findById(page.getParentPage()).orElseThrow();
            page.updateParent(parentPage);
        }

        return Optional.ofNullable(page);
    }
}

class PageMapper implements RowMapper<Page> {

    @Override
    public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Page(rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getLong("parent_page_id")
        );
    }
}
