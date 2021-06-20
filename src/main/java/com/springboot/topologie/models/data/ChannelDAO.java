package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="channel",path="channel")
public interface ChannelDAO extends JpaRepository<Channel,Long> {}