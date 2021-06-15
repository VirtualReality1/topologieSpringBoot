package com.springboot.topologie.models.data;

import com.springboot.topologie.models.Communication;
import com.springboot.topologie.models.Comtype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ComTypeDAO extends CrudRepository<Comtype, Integer> {}