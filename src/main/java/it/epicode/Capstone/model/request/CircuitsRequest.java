package it.epicode.Capstone.model.request;

import it.epicode.Capstone.model.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CircuitsRequest {

    @NotBlank(message = "Campo obbligatorio")
    private String circuitName;
    @NotBlank(message = "Campo obbligatorio")
    private Double lat;
    @NotBlank(message = "Campo obbligatorio")
    private Double lon;
    @NotBlank(message = "Campo obbligatorio")
    private String locality;

}
