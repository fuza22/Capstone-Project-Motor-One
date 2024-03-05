package it.epicode.Capstone.controller;


import it.epicode.Capstone.exception.BadRequestException;
import it.epicode.Capstone.exception.LoginFaultException;
import it.epicode.Capstone.model.entities.User;
import it.epicode.Capstone.model.request.LoginRequest;
import it.epicode.Capstone.model.request.RegisterRequest;
import it.epicode.Capstone.security.JwtTools;
import it.epicode.Capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.support.DefaultMessageSourceResolvable;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public User register(@RequestBody @Validated RegisterRequest registerRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            throw new BadRequestException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        }

        return userService.saveUser(registerRequest);

    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        User u = userService.getUserByUsername(loginRequest.getUsername());

        if(encoder.matches(loginRequest.getPassword(), u.getPassword())){
            return jwtTools.createToken(u);
        }
        else{
            throw new LoginFaultException("Username/password errate");
        }

    }
}
