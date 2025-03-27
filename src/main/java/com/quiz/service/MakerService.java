package com.quiz.service;

import com.quiz.dao.repository.*;
import com.quiz.dto.ApiResponse;
import com.quiz.dto.CategoryDto;
import com.quiz.dto.SubCategoryRequestDto;
import com.utility.service.MongoDbBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.utility.constant.UtilityConstant.*;

@Service
public class MakerService {
    private CategoryRepository categoryRepository;
    private CourseRepo courseRepo;
    private MongoDbBuilder mongoDbBuilder;
    private SubCategoryRepository subCategoryRepository;

    public MakerService(CategoryRepository categoryRepository,
                        CourseRepo courseRepo,
                        MongoDbBuilder mongoDbBuilder, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.courseRepo = courseRepo;
        this.mongoDbBuilder = mongoDbBuilder;
        this.subCategoryRepository = subCategoryRepository;
    }

    public ApiResponse createCategoryEntity(CategoryDto categoryDto) {

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setTitle(categoryDto.getTitle());
        categoryEntity.setDescription(categoryDto.getDescription());
        //   categoryEntity = categoryRepository.save(categoryEntity);
        categoryEntity = mongoDbBuilder.saveEntity(categoryEntity, "category");
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
                .insertToNode(id, subCategoryEntity, CategoryEntity.class);
        return new
                ApiResponse
                        .ApiResponseBuilder()
                .setMsg(SUCCESS)
                .setData(categoryEntity)
                .setFlag(TRUE)
                .build();

    }

    public ApiResponse findById(String id) {
        /*return
                new
                        ApiResponse
                                .ApiResponseBuilder()
                        .setData(Objects.equals(id, "id") ? categoryRepository.findAll()
                                : mongoDbBuilder.findById(id, "subCategory.id", CategoryEntity.class))
                        .setFlag(Boolean.TRUE)
                        .setMsg("Success")
                        .build();*/

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
        SubCategoryEntity subCategoryEntity = subCategoryRepository
                .findById(subCategoryRequestDto.getId())
                .get();

        subCategoryEntity.setSubcategory(subCategoryRequestDto.getSubcategory().stream().map(e -> {
            SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity();
            subCategoryEntity1.setName(e.getName());
            subCategoryEntity1.setDescription(e.getDescription());
            return subCategoryEntity1;
        }).collect(Collectors.toList()));

        return new ApiResponse
                .ApiResponseBuilder()
                .setFlag(true)
                .setMsg("Success")
                .setData(subCategoryRepository.save(subCategoryEntity))
                .build();
    }

}
