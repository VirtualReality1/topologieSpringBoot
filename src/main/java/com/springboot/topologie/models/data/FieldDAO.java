package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FieldDAO extends CrudRepository<Field,Integer> {}