package it.epicode.Capstone.controller;

import it.epicode.Capstone.exception.BadRequestException;
import it.epicode.Capstone.exception.CustomResponse;
import it.epicode.Capstone.exception.NotFoundException;
import it.epicode.Capstone.model.request.CircuitsRequest;
import it.epicode.Capstone.model.request.ConstructorsRequest;
import it.epicode.Capstone.service.CircuitsService;
import it.epicode.Capstone.service.ConstructorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/circuit")
public class CircuitController {

    @Autowired
    private CircuitsService circuitsService;

    @GetMapping("/search")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){

        try{

            return CustomResponse.success(HttpStatus.OK.toString(), circuitsService.getAll(pageable), HttpStatus.OK);

        }catch(Exception e){

            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<CustomResponse> getCircuitById(@PathVariable int id){

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), circuitsService.getCircuitById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse> saveCircuit(@RequestBody @Validated CircuitsRequest circuitsRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), circuitsService.saveCircuit(circuitsRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> updateCircuit(@PathVariable int id, @RequestBody @Validated CircuitsRequest circuitsRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), circuitsService.updateCircuit(id, circuitsRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteCircuit(@PathVariable int id){

        try {
            circuitsService.deleteCircuitById(id);
            return CustomResponse.emptyResponse("Il circuito con id = " + id + " è stato cancellato", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
