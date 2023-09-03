package com.practice.notion.persist;

import com.practice.notion.persist.entity.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PageRepositoryTest {

    @Autowired
    private PageRepository pageRepository;

    @Test
    public void save_success() throws Exception{
        //given
        Page simple = Page.createSimple("테스트", "테스트 내용", null);

        //when
        Long save = pageRepository.save(simple);
    }

    @Test
    public void findBy_id() throws Exception{
        //given
        Page simple = Page.createSimple("테스트", "테스트 내용", null);
        Long save = pageRepository.save(simple);

        //when
        Page byId = pageRepository.findById(save).get();
        assertThat(byId.getTitle()).isEqualTo("테스트");
    }

    @Test
    public void findBy_withParent() throws Exception{
        //given
        Page simple = Page.createSimple("자식 글", "자식 내용5", 2L);
        Long save = pageRepository.save(simple);

        //when
        Page byId = pageRepository.findById(save).get();
        System.out.println(byId.printBreadcumbs());
        assertThat(byId.printBreadcumbs()).contains("테스트");
    }
}
