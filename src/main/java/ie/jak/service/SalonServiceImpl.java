package ie.jak.service;

import ie.jak.dao.SalonDao;
import ie.jak.entites.Salon;
import ie.jak.service.exceptions.SalonAlreadyExists;
import ie.jak.service.exceptions.SalonMalformedException;
import ie.jak.service.exceptions.SalonNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServiceImpl implements SalonService{

    @Autowired
    private SalonDao salonDao;

    @Override
    public int count() {
        return salonDao.count();
    }

    @Override
    @SneakyThrows
    public Salon findById(int id) {
        return salonDao.findById(id).orElseThrow(() -> new SalonNotFoundException("Salon with id " + id + " was not found. "));
    }

    @Override
    public List<Salon> findAll() {
        return salonDao.findAll();
    }

    @SneakyThrows
    @Override
    public void deleteById(int id) {
            if (salonDao.deleteById(id)) {}
            else {throw new SalonNotFoundException("Salon with id " + id + " was not found. ");}
        }

    @SneakyThrows
    @Override
    public Salon add(Salon salon) {
        if (salon.getSalonName().isBlank())
            throw new SalonMalformedException("Salon name cannot be blank");
        if (salon.getSalonAddress().isBlank())
            throw new SalonMalformedException("Salon address cannot be blank");
        if (salon.getSalonId() < 0)
            throw new SalonMalformedException("ID is invalid");
        if (salonDao.findById(salon.getSalonId()).isPresent())
            throw new SalonAlreadyExists("Salon with ID " + salon.getSalonId() + " already exists");
        salonDao.save(salon);
        return salonDao.findById(salon.getSalonId()).get();
    }

    @SneakyThrows
    @Override
    public boolean changeName(String newName, int id) {
        if (newName.isBlank())
            throw new SalonMalformedException("Salon name cannot be blank");
        if (salonDao.findById(id).isEmpty())
            throw new SalonNotFoundException("Salon with ID " + id + " does not exist");
        return salonDao.editName(newName, id);
    }
}
