package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="communication",path="communication")
public interface CommunicationDAO extends JpaRepository<Communication,Long> {}