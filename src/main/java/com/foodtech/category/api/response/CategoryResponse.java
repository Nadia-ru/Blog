package com.foodtech.category.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class CategoryResponse {
    private String id;
    private String title;
    private String description;
    private String fileType;
}
