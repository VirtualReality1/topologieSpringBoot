package com.springboot.topologie.models.data;


import com.springboot.topologie.models.Messagetype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="messagetype",path="messagetype")
public interface MessagetypeDAO extends JpaRepository<Messagetype,Long> {}