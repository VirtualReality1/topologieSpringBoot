package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Messagetype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MessagetypeDAO extends CrudRepository<Messagetype,Integer> {}