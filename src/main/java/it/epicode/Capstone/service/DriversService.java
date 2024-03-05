package it.epicode.Capstone.service;

import it.epicode.Capstone.exception.NotFoundException;
import it.epicode.Capstone.model.entities.Drivers;
import it.epicode.Capstone.model.entities.User;
import it.epicode.Capstone.model.request.DriversRequest;
import it.epicode.Capstone.repository.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DriversService {

    @Autowired
    private DriversRepository driversRepository;

    @Autowired
    private UserService userService;



    public Page<Drivers> getAll(Pageable pageable){

        return driversRepository.findAll(pageable);

    }

    public Drivers getDriverById(int id) throws NotFoundException {
        return driversRepository.findById(id).orElseThrow(()->new NotFoundException("Pilota con id = " + id + " non trovato"));
    }


    public Drivers saveDriver(DriversRequest driversRequest){
        Drivers d = new Drivers();
        d.setPermanentNumber(driversRequest.getPermanentNumber());
        d.setGivenName(driversRequest.getGivenName());
        d.setFamilyName(driversRequest.getFamilyName());
        d.setDateOfBirth(driversRequest.getDateOfBirth());
        d.setNationality(driversRequest.getNationality());

        return driversRepository.save(d);
    }

    public Drivers updateDriver(int id, DriversRequest driversRequest) throws NotFoundException {
        Drivers d = new Drivers();
        d.setPermanentNumber(driversRequest.getPermanentNumber());
        d.setGivenName(driversRequest.getGivenName());
        d.setFamilyName(driversRequest.getFamilyName());
        d.setDateOfBirth(driversRequest.getDateOfBirth());
        d.setNationality(driversRequest.getNationality());


        return driversRepository.save(d);
    }

    public void deleteDriverById(int id) throws NotFoundException {
        Drivers d = getDriverById(id);
        driversRepository.delete(d);
    }


}
