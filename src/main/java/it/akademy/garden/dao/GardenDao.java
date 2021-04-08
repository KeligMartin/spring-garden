package it.akademy.garden.dao;

import it.akademy.garden.models.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenDao extends JpaRepository<Garden, Integer> {

    List<Garden> findAll();

    List<Garden> findAllByName(String name);

    Garden findById(int id);

    Garden save(Garden garden);

    void deleteById(int id);
}
