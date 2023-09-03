package com.practice.notion.adapter.in.rest;

import com.practice.notion.domain.Content;
import com.practice.notion.persist.PageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final PageRepository pageRepository;

    public Controller(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @GetMapping("/api/pages/{id}")
    public Content requestFindByIdPage(@PathVariable long id) {
        return pageRepository.findById(id).orElseThrow();
    }
}
