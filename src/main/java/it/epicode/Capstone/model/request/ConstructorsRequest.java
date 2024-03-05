package it.epicode.Capstone.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConstructorsRequest {

    @NotBlank(message = "Campo obbligatorio")
    private String constructorsName;
    @NotBlank(message = "Campo obbligatorio")
    private String constructorsNationality;

}
