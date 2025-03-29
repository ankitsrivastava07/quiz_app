package com.quiz.dao.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "subcategory")
public class SubCategoryEntity {
    private String id;
    private String name;
    private String description;
    private List<SubCategoryEntity> subcategories = new ArrayList<>();

    public List<SubCategoryEntity> getSubcategory() {
        return subcategories;
    }

    public void setSubcategory(List<SubCategoryEntity> subcategory) {
        this.subcategories = subcategory;
    }

    public SubCategoryEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

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
}
