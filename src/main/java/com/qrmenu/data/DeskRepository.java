package com.qrmenu.data;

import com.qrmenu.domain.Desk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Integer> {


}
