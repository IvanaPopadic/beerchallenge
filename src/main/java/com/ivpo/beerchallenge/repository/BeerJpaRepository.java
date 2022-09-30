package com.ivpo.beerchallenge.repository;

import com.ivpo.beerchallenge.model.BeerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerJpaRepository extends JpaRepository<BeerEntity, Long> {

}
