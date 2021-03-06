package com.qrmenu.data;

import com.qrmenu.domain.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends CrudRepository<Personnel, Integer> {
}
