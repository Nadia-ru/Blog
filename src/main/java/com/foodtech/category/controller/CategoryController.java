package com.foodtech.category.controller;


import com.foodtech.category.api.response.CategoryResponse;
import com.foodtech.category.mapping.CategoryMapping;
import com.foodtech.category.routes.CategoryApiRoutes;
import com.foodtech.category.service.CategoryApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryApiService categoryApiService;

    @PostMapping(CategoryApiRoutes.ROOT)
    public @ResponseBody CategoryResponse create(@RequestParam MultipartFile file,
                                                 @RequestParam String title,
                                                 @RequestParam String description) throws IOException {
        return CategoryMapping.getInstance().getResponse().convert(categoryApiService.create(file,title, description));
    }
}
