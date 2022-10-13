package com.foodtech.category.mapping;

import com.foodtech.base.api.response.SearchResponse;
import com.foodtech.category.api.response.CategoryResponse;
import com.foodtech.category.model.CategoryDoc;
import lombok.Getter;

import java.util.stream.Collectors;

@Getter
public class CategoryMapping {

    public static class ResponseMapping{

        public CategoryResponse convert(CategoryDoc categoryDoc){
            return CategoryResponse.builder()
                    .id(categoryDoc.getId().toString())
                    .title(categoryDoc.getTitle())
                    .description(categoryDoc.getDescription())
                    .fileType(categoryDoc.getFileType())
                    .build();
        }

    }



    public static class SearchMapping{
        private ResponseMapping responseMapping=new ResponseMapping();

        public SearchResponse<CategoryResponse>convert(SearchResponse<CategoryDoc> searchResponse){
            return SearchResponse.of(
                    searchResponse.getList().stream().map(responseMapping::convert).collect(Collectors.toList()),
                    searchResponse.getCount()
            );

        }

    }

    private final ResponseMapping response=new ResponseMapping();
    private final SearchMapping search=new SearchMapping();

    public static CategoryMapping getInstance(){
        return new CategoryMapping();
    }
}
