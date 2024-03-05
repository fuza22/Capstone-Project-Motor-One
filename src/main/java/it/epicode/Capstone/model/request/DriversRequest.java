package it.epicode.Capstone.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriversRequest {

    @NotBlank(message = "Campo obbligatorio")
    private int permanentNumber;
    @NotBlank(message = "Campo obbligatorio")
    private String givenName;
    @NotBlank(message = "Campo obbligatorio")
    private String familyName;
    @NotBlank(message = "Campo obbligatorio")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Campo obbligatorio")
    private String nationality;

}
