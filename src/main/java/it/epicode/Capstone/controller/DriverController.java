package it.epicode.Capstone.controller;


import it.epicode.Capstone.exception.BadRequestException;
import it.epicode.Capstone.exception.CustomResponse;
import it.epicode.Capstone.exception.NotFoundException;
import it.epicode.Capstone.model.request.DriversRequest;
import it.epicode.Capstone.model.request.RegisterRequest;
import it.epicode.Capstone.service.DriversService;
import it.epicode.Capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriversService driversService;

    @GetMapping("/search")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){

        try{

            return CustomResponse.success(HttpStatus.OK.toString(), driversService.getAll(pageable), HttpStatus.OK);

        }catch(Exception e){

            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<CustomResponse> getDriverById(@PathVariable int id){

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), driversService.getDriverById(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse> saveDriver(@RequestBody @Validated DriversRequest driversRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), driversService.saveDriver(driversRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> updateDriver(@PathVariable int id, @RequestBody @Validated DriversRequest driversRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), driversService.updateDriver(id, driversRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteDriver(@PathVariable int id){

        try {
            driversService.deleteDriverById(id);
            return CustomResponse.emptyResponse("Il pilota con id = " + id + " è stato cancellato", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
