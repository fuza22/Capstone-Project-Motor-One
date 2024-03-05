package it.epicode.Capstone.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Constructors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "constructors_name")
    private String constructorsName;
    @Column(name = "constructors_nationality")
    private String constructorsNationality;


}
