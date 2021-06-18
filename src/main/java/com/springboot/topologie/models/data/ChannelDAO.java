package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ChannelDAO extends CrudRepository<Channel, Integer> {}