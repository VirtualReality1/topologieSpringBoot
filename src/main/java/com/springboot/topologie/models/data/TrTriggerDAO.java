package com.springboot.topologie.models.data;

import com.springboot.topologie.models.TrTrigger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TrTriggerDAO extends CrudRepository<TrTrigger, Integer> {}