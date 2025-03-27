package com.quiz.dao.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subcategory")
public class SubCategoryEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private List<SubCategoryEntity> subcategory;

    public List<SubCategoryEntity> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<SubCategoryEntity> subcategory) {
        this.subcategory = subcategory;
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
