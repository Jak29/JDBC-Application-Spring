package ie.jak.service;

import ie.jak.entites.Salon;

import java.util.List;

public interface SalonService {
    int count();
    Salon findById(int id);
    List<Salon> findAll();
    void deleteById(int id);
    Salon add(Salon salon);
    boolean changeName(String newName, int id);
}
