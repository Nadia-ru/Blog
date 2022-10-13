package com.foodtech.base.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    protected String query = null;
    protected Integer size = 100;
    protected Long skip = Long.valueOf(0);
}
