package com.practice.notion.persist.entity;

import com.practice.notion.domain.Content;

public class Page extends Content {
    // 페이지 제목, 컨텐츠, 서브 페이지 리스트, 브로드 크럼스 ( 페이지 1 > 페이지 3 > 페이지 5)
    private String title;
    private String content;
    private Long parentPage;

    public Page(Long id, String title, String content, Long parentPage) {
        super(id);
        super.updateBreadcumbs(id, title);
        this.title = title;
        this.content = content;
        this.parentPage = parentPage;
    }

    public static Page createSimple (String title, String content, Long parentPageId) {
        return new Page(null, title, content, parentPageId);
    }

    @Override
    public String toString() {
        return String.format("[%s](%s)", this.title, this.id) + System.lineSeparator();
    }

    public String printPage() {
        return content;
    }

    public String printBreadcumbs() {
        StringBuilder value = new StringBuilder();
        for (String title : breadcrumbs) {
            value.append(title).append(" > ");
        }
        value.delete(value.lastIndexOf(" >"), value.length());
        return value.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getParentPage() {
        return parentPage;
    }

    public void updateParent(Page parent) {
        super.updateBreadcumbs(parent.getId(), parent.getTitle());
    }
}
