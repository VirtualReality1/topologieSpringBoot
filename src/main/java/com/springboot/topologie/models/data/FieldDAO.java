package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="field",path="field")
public interface FieldDAO extends JpaRepository<Field,Long> {}