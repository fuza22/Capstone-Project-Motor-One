package it.epicode.Capstone.service;

import it.epicode.Capstone.exception.NotFoundException;
import it.epicode.Capstone.model.entities.Constructors;
import it.epicode.Capstone.model.entities.Drivers;
import it.epicode.Capstone.model.entities.User;
import it.epicode.Capstone.model.request.ConstructorsRequest;
import it.epicode.Capstone.model.request.DriversRequest;
import it.epicode.Capstone.repository.ConstructorsRepository;
import it.epicode.Capstone.repository.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConstructorsService {


    @Autowired
    private ConstructorsRepository constructorsRepository;

    @Autowired
    private UserService userService;



    public Page<Constructors> getAll(Pageable pageable){

        return constructorsRepository.findAll(pageable);

    }

    public Constructors getConstructorById(int id) throws NotFoundException {
        return constructorsRepository.findById(id).orElseThrow(()->new NotFoundException("Costruttore con id = " + id + " non trovato"));
    }


    public Constructors saveConstructor(ConstructorsRequest constructorsRequest){
        Constructors c = new Constructors();
        c.setConstructorsName(constructorsRequest.getConstructorsName());
        c.setConstructorsNationality(constructorsRequest.getConstructorsNationality());

        return constructorsRepository.save(c);
    }

    public Constructors updateConstructor(int id, ConstructorsRequest constructorsRequest) throws NotFoundException {
        Constructors c = new Constructors();
        c.setConstructorsName(constructorsRequest.getConstructorsName());
        c.setConstructorsNationality(constructorsRequest.getConstructorsNationality());

        return constructorsRepository.save(c);
    }

    public void deleteConstructorById(int id) throws NotFoundException {
        Constructors c = getConstructorById(id);

        constructorsRepository.delete(c);
    }


}
