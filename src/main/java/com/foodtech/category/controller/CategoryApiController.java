package com.foodtech.category.controller;

import com.foodtech.category.api.request.CategoryRequest;
import com.foodtech.category.api.response.CategoryResponse;
import com.foodtech.category.mapping.CategoryMapping;
import com.foodtech.category.routes.CategoryApiRoutes;
import com.foodtech.category.service.CategoryApiService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.message.AuthException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryApiService categoryApiService;

//    @GetMapping(CategoryApiRoutes.ROOT)
//    public CategoryResponse search(
//            @ModelAttribute SearchRequest request
//    ){
//        return  CategoryMapping.getInstance().getSearch().convert(
//                fileApiService.search(request)
//        );
//    }




}
