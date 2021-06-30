package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="segment",path="segment")
public interface SegmentDAO extends JpaRepository<Segment,Long> {
    List<Segment> findByMessagetype_id (Long id);
}