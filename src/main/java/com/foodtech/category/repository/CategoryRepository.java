package com.foodtech.category.repository;

import com.foodtech.category.model.CategoryDoc;
import org.apache.el.stream.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryDoc, ObjectId> {

}
