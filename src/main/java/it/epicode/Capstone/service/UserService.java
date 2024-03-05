package it.epicode.Capstone.service;

import it.epicode.Capstone.enums.Role;
import it.epicode.Capstone.exception.NotFoundException;
import it.epicode.Capstone.model.entities.Circuits;
import it.epicode.Capstone.model.entities.Constructors;
import it.epicode.Capstone.model.entities.Drivers;
import it.epicode.Capstone.model.entities.User;
import it.epicode.Capstone.model.request.RegisterRequest;
import it.epicode.Capstone.repository.CircuitsRepository;
import it.epicode.Capstone.repository.ConstructorsRepository;
import it.epicode.Capstone.repository.DriversRepository;
import it.epicode.Capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriversRepository driversRepository;

    @Autowired
    private CircuitsRepository circuitsRepository;

    @Autowired
    private ConstructorsRepository constructorsRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<User> getAll(Pageable pageable){

        return userRepository.findAll(pageable);

    }

    public User getUserById(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("Utente con id = " + id + " non trovato"));
    }

    public User getUserByUsername(String username){

        return userRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));

    }

    public User saveUser(RegisterRequest registerRequest){
        User u = new User();
        u.setName(registerRequest.getName());
        u.setSurname(registerRequest.getSurname());
        u.setUsername(registerRequest.getUsername());
        u.setEmail(registerRequest.getEmail());
        u.setPassword(encoder.encode(registerRequest.getPassword()));
        u.setRole(Role.USER);

        User savedUser = userRepository.save(u);
        if(savedUser != null){

            sendEmail(registerRequest.getEmail());

        }

        return savedUser;
    }

    public User updateUser(int id, RegisterRequest registerRequest) throws NotFoundException {
        User u = getUserById(id);
        u.setName(registerRequest.getName());
        u.setSurname(registerRequest.getSurname());
        u.setUsername(registerRequest.getUsername());
        u.setEmail(registerRequest.getEmail());
        u.setPassword(encoder.encode(registerRequest.getPassword()));


        return userRepository.save(u);
    }

    public void deleteUserById(int id) throws NotFoundException {
        User u = getUserById(id);
        userRepository.delete(u);
    }

    public void deleteUserByUsername(String username) throws NotFoundException {
        userRepository.deleteByUsername(username);
    }

    public User updateRoleUser(String username, String role){
        User u = getUserByUsername(username);
        u.setRole(Role.valueOf(role));
        return userRepository.save(u);

    }

    public void sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione al sito Motor One");
        message.setText("Benvenuto su Motor One, registrazione avvenuta con successo.");

        javaMailSender.send(message);
    }

    public User uploadAvatar(int id, String url) throws NotFoundException {
        User u = getUserById(id);
        u.setAvatar(url);

        return userRepository.save(u);

    }

    public User addDriverToFavorites(int userId, int driverId) throws NotFoundException {
        User user = getUserById(userId);
        Drivers driver = driversRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException("Pilota non trovato"));
        user.getFavDrivers().add(driver);
        return userRepository.save(user);
    }

    public User removeDriverFromFavorites(int userId, int driverId) throws NotFoundException {
        User user = getUserById(userId);
        Drivers driver = driversRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException("Pilota non trovato"));
        user.getFavDrivers().remove(driver);
        return userRepository.save(user);
    }

    public User addConstructorsToFavorites(int userId, int constructorsId) throws NotFoundException {
        User user = getUserById(userId);
        Constructors constructor = constructorsRepository.findById(constructorsId)
                .orElseThrow(() -> new NotFoundException("Costruttore non trovato"));
        user.getFavConstructors().add(constructor);
        return userRepository.save(user);
    }

    public User removeConstructorsFromFavorites(int userId, int constructorsId) throws NotFoundException {
        User user = getUserById(userId);
        Constructors constructor = constructorsRepository.findById(constructorsId)
                .orElseThrow(() -> new NotFoundException("Costruttore non trovato"));
        user.getFavConstructors().remove(constructor);
        return userRepository.save(user);
    }

    public User addCircuitsToFavorites(int userId, int circuitsId) throws NotFoundException {
        User user = getUserById(userId);
        Circuits circuit = circuitsRepository.findById(circuitsId)
                .orElseThrow(() -> new NotFoundException("Circuito non trovato"));
        user.getFavCircuits().add(circuit);
        return userRepository.save(user);
    }

    public User removeCircuitsFromFavorites(int userId, int circuitsId) throws NotFoundException {
        User user = getUserById(userId);
        Circuits circuit = circuitsRepository.findById(circuitsId)
                .orElseThrow(() -> new NotFoundException("Circuito non trovato"));
        user.getFavConstructors().remove(circuit);
        return userRepository.save(user);
    }

}
