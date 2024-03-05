package it.epicode.Capstone.model.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CollectionId;

import java.time.LocalDate;
@Entity
@Data
public class Drivers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "permanent_number")
    private int permanentNumber;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "family_name")
    private String familyName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String nationality;

}
