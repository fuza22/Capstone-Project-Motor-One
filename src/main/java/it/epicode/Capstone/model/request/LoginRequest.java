package it.epicode.Capstone.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Campo obbligatorio")
    private String username;
    @NotBlank(message = "Campo obbligatorio")
    private String password;

}
