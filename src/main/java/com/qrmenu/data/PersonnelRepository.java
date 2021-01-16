package com.qrmenu.data;

import com.qrmenu.domain.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelRepository extends CrudRepository<Personnel, Integer> {

    Optional<Personnel> findByUsername(String username);

    Iterable<Personnel> findAllByOrderByIdAsc();
}
