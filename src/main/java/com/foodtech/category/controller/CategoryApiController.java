package com.foodtech.category.controller;


import com.foodtech.base.api.request.SearchRequest;
import com.foodtech.base.api.response.SearchResponse;
import com.foodtech.category.api.response.CategoryResponse;
import com.foodtech.category.mapping.CategoryMapping;
import com.foodtech.category.routes.CategoryApiRoutes;
import com.foodtech.category.service.CategoryApiService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryApiService categoryApiService;

    @PostMapping(CategoryApiRoutes.ROOT)
    public @ResponseBody CategoryResponse create(@RequestPart MultipartFile file,
                                                 @RequestParam String title, @RequestParam String description) throws IOException {
        return CategoryMapping.getInstance().getResponse().convert(categoryApiService.create(file,title,description));
    }

    @GetMapping(CategoryApiRoutes.ROOT)
    public SearchResponse<CategoryResponse> search(
            @ModelAttribute SearchRequest request
    ){
        return  CategoryMapping.getInstance().getSearch().convert(
                categoryApiService.search(request));
    }

    @DeleteMapping(CategoryApiRoutes.BY_ID)
    public String deleteById( @PathVariable ObjectId id)  {
        categoryApiService.delete(id);
        return HttpStatus.OK.toString();
    }


}
