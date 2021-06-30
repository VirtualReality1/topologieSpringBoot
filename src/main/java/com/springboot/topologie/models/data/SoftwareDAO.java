package com.springboot.topologie.models.data;


import com.springboot.topologie.models.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="software",path="software")
public interface SoftwareDAO extends JpaRepository<Software,Long> {

}

