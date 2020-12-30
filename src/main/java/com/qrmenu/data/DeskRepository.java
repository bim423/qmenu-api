package com.qrmenu.data;

import com.qrmenu.domain.Desk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Integer> {

    Optional<Desk> findDeskByCode(String code);
}
