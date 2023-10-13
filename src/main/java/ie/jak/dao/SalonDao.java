package ie.jak.dao;

import ie.jak.entites.Salon;

import java.util.List;
import java.util.Optional;

public interface SalonDao {
    int count();
    List<Salon> findAll();
    Optional<Salon> findById(int id);
    boolean deleteById(int id);
    void save(Salon salon);
    boolean editName(String newName, int id);
}
