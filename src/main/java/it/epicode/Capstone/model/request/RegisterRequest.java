package it.epicode.Capstone.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Campo obbligatorio")
    private String username;
    @Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,}$",
            message = "La password deve contenere: 1 carattere maiuscolo, 1 carattere minuscolo, 1 numero, 1 carattere speciale, Min 8 caratteri")
    private String password;
    @NotBlank(message = "Campo obbligatorio")
    private String name;
    @NotBlank(message = "Campo obbligatorio")
    private String surname;
    @Email(message = "Inserisci una mail valida")
    private String email;


}
