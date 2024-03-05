package it.epicode.Capstone.repository;

import it.epicode.Capstone.model.entities.Constructors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructorsRepository extends JpaRepository<Constructors, Integer>, PagingAndSortingRepository<Constructors, Integer> {
}
