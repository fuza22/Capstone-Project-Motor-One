package it.epicode.Capstone.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Circuits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "circuit_name")
    private String circuitName;
    private Double lat;
    private Double lon;
    private String locality;


}
