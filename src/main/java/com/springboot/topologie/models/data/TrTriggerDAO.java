package com.springboot.topologie.models.data;

import com.springboot.topologie.models.TrTrigger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="trtrigger",path="trtrigger")
public interface TrTriggerDAO extends JpaRepository<TrTrigger,Long> {}