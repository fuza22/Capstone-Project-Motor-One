package it.epicode.Capstone.service;

import it.epicode.Capstone.exception.NotFoundException;
import it.epicode.Capstone.model.entities.Circuits;
import it.epicode.Capstone.model.entities.Constructors;
import it.epicode.Capstone.model.entities.User;
import it.epicode.Capstone.model.request.CircuitsRequest;
import it.epicode.Capstone.model.request.ConstructorsRequest;
import it.epicode.Capstone.repository.CircuitsRepository;
import it.epicode.Capstone.repository.ConstructorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CircuitsService {

    @Autowired
    private CircuitsRepository circuitsRepository;

    @Autowired
    private UserService userService;



    public Page<Circuits> getAll(Pageable pageable){

        return circuitsRepository.findAll(pageable);

    }

    public Circuits getCircuitById(int id) throws NotFoundException {
        return circuitsRepository.findById(id).orElseThrow(()->new NotFoundException("Circuito con id = " + id + " non trovato"));
    }


    public Circuits saveCircuit(CircuitsRequest circuitsRequest){
        Circuits c = new Circuits();
        c.setCircuitName(circuitsRequest.getCircuitName());
        c.setLat(circuitsRequest.getLat());
        c.setLon(circuitsRequest.getLon());
        c.setLocality(circuitsRequest.getLocality());

        return circuitsRepository.save(c);
    }

    public Circuits updateCircuit(int id, CircuitsRequest circuitsRequest) throws NotFoundException {
        Circuits c = new Circuits();
        c.setCircuitName(circuitsRequest.getCircuitName());
        c.setLat(circuitsRequest.getLat());
        c.setLon(circuitsRequest.getLon());
        c.setLocality(circuitsRequest.getLocality());

        return circuitsRepository.save(c);
    }

    public void deleteCircuitById(int id) throws NotFoundException {
        Circuits c = getCircuitById(id);

        circuitsRepository.delete(c);
    }



}
