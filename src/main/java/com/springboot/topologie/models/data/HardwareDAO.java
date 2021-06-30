package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="hardware",path="hardware")
public interface HardwareDAO extends JpaRepository<Hardware,Long> {}