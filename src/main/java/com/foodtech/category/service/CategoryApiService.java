package com.foodtech.category.service;

import com.foodtech.base.api.request.SearchRequest;
import com.foodtech.base.api.response.SearchResponse;

import com.foodtech.category.model.CategoryDoc;
import com.foodtech.category.repository.CategoryRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryApiService {
    private final CategoryRepository categoryRepository;
    private final MongoTemplate mongoTemplate;
    private final GridFsTemplate gridFsTemplate;


    public CategoryDoc create(MultipartFile file, String title, String description) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type",file.getContentType());


        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData
        );

        CategoryDoc categoryDoc= CategoryDoc.builder()
                .id(id)
                .title(title)
                .description(description)
                .fileType(file.getContentType())
                .build();

        categoryRepository.save(categoryDoc);
        return  categoryDoc;
    }

    public Optional<CategoryDoc> findByID(ObjectId id){
        return categoryRepository.findById(id);
    }


    public SearchResponse<CategoryDoc> search(
            SearchRequest request
    ){
        Criteria criteria = new Criteria();
        if(request.getQuery() != null && request.getQuery()!=""){
            criteria = criteria.orOperator(
                    Criteria.where("title").regex(request.getQuery(), "i"),
                    Criteria.where("description").regex(request.getQuery(), "i")

            );
        }

        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query, CategoryDoc.class);
        query.limit(request.getSize());
        query.skip(request.getSkip());

        List<CategoryDoc> categoryDocs = mongoTemplate.find(query, CategoryDoc.class);
        return SearchResponse.of(categoryDocs, count);
    }

    public void delete(ObjectId id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
        categoryRepository.deleteById(id);
    }


}
