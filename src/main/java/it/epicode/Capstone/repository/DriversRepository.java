package it.epicode.Capstone.repository;

import it.epicode.Capstone.model.entities.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversRepository extends JpaRepository<Drivers, Integer>, PagingAndSortingRepository<Drivers, Integer> {
}
