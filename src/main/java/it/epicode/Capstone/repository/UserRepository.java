package it.epicode.Capstone.repository;

import it.epicode.Capstone.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
    public Optional<User> deleteByUsername(String username);

}
