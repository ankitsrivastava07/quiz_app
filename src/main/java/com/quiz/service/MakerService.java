package com.quiz.service;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.*;
import com.mongodb.client.result.UpdateResult;
import com.quiz.dao.repository.*;
import com.quiz.dto.ApiResponse;
import com.quiz.dto.CategoryDto;
import com.quiz.dto.SubCategoryRequestDto;
import com.utility.service.MongoDbBuilder;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.utility.constant.UtilityConstant.*;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MakerService {
    private CategoryRepository categoryRepository;
    private CourseRepo courseRepo;
    private MongoDbBuilder mongoDbBuilder;
    private SubCategoryRepository subCategoryRepository;
    private MongoTemplate mongoTemplate;

    public MakerService(CategoryRepository categoryRepository,
                        CourseRepo courseRepo,
                        MongoDbBuilder mongoDbBuilder,
                        SubCategoryRepository subCategoryRepository, MongoTemplate mongoTemplate) {
        this.categoryRepository = categoryRepository;
        this.courseRepo = courseRepo;
        this.mongoDbBuilder = mongoDbBuilder;
        this.subCategoryRepository = subCategoryRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public ApiResponse createCategoryEntity(CategoryDto categoryDto) {

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setTitle(categoryDto.getTitle());
        categoryEntity.setDescription(categoryDto.getDescription());
        //   categoryEntity = categoryRepository.save(categoryEntity);
        //  categoryEntity = mongoDbBuilder.saveEntity(categoryEntity, "category");
        categoryEntity = categoryRepository.save(categoryEntity);
        return
                new
                        ApiResponse
                                .ApiResponseBuilder()
                        .setData(categoryEntity)
                        .setFlag(Boolean.TRUE)
                        .setMsg("Success")
                        .build();
    }

    public ApiResponse getAllCategories() {
        return
                new
                        ApiResponse
                                .ApiResponseBuilder()
                        .setData(categoryRepository.findAll())
                        .setFlag(Boolean.TRUE)
                        .build();
    }

    public ApiResponse createSubcategory(String id, CategoryDto categoryDto) {
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setName(categoryDto.getName());
        subCategoryEntity.setDescription(categoryDto.getDescription());
        CategoryEntity categoryEntity = mongoDbBuilder
                .insertToNode(id, "subcategories", subCategoryEntity, CategoryEntity.class);
        return new
                ApiResponse
                        .ApiResponseBuilder()
                .setMsg(SUCCESS)
                .setData(categoryEntity)
                .setFlag(TRUE)
                .build();

    }

    public ApiResponse findById(String id) {
        return new ApiResponse
                .ApiResponseBuilder()
                .setFlag(TRUE)
                .setData(categoryRepository.findById(id))
                .setMsg("Success")
                .build();
    }

    public ApiResponse getSubCategoryById() {
        return new ApiResponse
                .ApiResponseBuilder()
                .build();
    }

    public ApiResponse saveSubcategoryToSubcategory(SubCategoryRequestDto subCategoryRequestDto) {
        List<SubCategoryEntity> subCategoryEntityList = subCategoryRequestDto.getSubcategory().stream().map(e -> {
            SubCategoryEntity s = new SubCategoryEntity();
            s.setName(e.getName());
            s.setDescription(e.getDescription());
            return s;
        }).toList();

        Query query = new Query(Criteria.where("_id").is(subCategoryRequestDto.getCategoryId())
                .and("subcategories._id").is(subCategoryRequestDto.getId()));
        Update update = new Update().push("subcategories.$[elem].subcategories").each(subCategoryEntityList)
                .filterArray(Criteria.where("elem._id").is(subCategoryRequestDto.getId()));

        CategoryEntity updatedCategory = mongoTemplate.findAndModify(query, update,
                FindAndModifyOptions.options().returnNew(true), CategoryEntity.class);
        return new ApiResponse
                .ApiResponseBuilder()
                .setFlag(true)
                .setMsg("Success")
                .setData(updatedCategory)
                .build();
    }
}
