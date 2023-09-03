package com.practice.notion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedList;
import java.util.List;

public abstract class Content {

    protected ContentId id;
    protected List<Long> breadcrumbIds = new LinkedList<>();
    protected List<String> breadcrumbs = new LinkedList<>();

    protected Content() {
    }

    protected Content(Long id) {
        this.id = new ContentId(id);
    }

    public Long getId() {
        return id.getValue();
    }

    public List<String> getBreadcrumbs() {
        return breadcrumbs;
    }

    protected void updateBreadcumbs(Long parentPageId, String title) {
        if (parentPageId==null) return;

        this.breadcrumbIds.add(0, parentPageId);
        this.breadcrumbs.add(0, title);
    }

    public String getPrintBreadcumbs() {
        StringBuilder value = new StringBuilder();
        for (String title : breadcrumbs) {
            value.append(title).append(" > ");
        }
        value.delete(value.lastIndexOf(" >"), value.length());
        return value.toString();
    }
}
