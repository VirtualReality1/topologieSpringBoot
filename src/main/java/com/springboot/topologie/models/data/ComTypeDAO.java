package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Comtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="comtype",path="comtype")
public interface ComTypeDAO extends JpaRepository<Comtype,Long> {}