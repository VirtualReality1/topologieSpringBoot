package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@RepositoryRestResource(collectionResourceRel="software",path="software")
public interface SoftwareDAO extends JpaRepository<Software,Integer> {}

