package com.quiz.dto;

import com.quiz.dao.repository.SubCategoryEntity;

import java.util.List;

public class SubCategoryRequestDto {
    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubCategoryEntity> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<SubCategoryEntity> subcategory) {
        this.subcategory = subcategory;
    }

    private String title;
    private List<SubCategoryEntity> subcategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
