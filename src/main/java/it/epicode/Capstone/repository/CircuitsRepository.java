package it.epicode.Capstone.repository;

import it.epicode.Capstone.model.entities.Circuits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitsRepository extends JpaRepository<Circuits, Integer>, PagingAndSortingRepository<Circuits, Integer> {
}
