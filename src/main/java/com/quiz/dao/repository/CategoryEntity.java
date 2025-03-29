package com.quiz.dao.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryEntity {
    @Id
    private String id;
    private String name;
    private String title;
    private String description;
    private List<CategoryEntity> category;
    private List<SubCategoryEntity> subcategories = new ArrayList<>();

    public CategoryEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public List<CategoryEntity> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    public void setSubCategories(List<SubCategoryEntity> subCategories) {
        this.subcategories = subCategories;
    }

    public List<SubCategoryEntity> getSubCategories() {
        return subcategories;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
