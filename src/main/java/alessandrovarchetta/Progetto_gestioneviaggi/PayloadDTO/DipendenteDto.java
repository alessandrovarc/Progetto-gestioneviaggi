package alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDto(
        @NotEmpty(message = "l'username è obbligatorio")
        String username,
        @NotEmpty(message = "Il nome  è obbligatorio")
        @NotEmpty(message = "Il cognome è obbligatorio!")
        @NotEmpty(message = "l'email è obbligatoria!")
        @Email(message = "inserire una email valida!")
        String email
)
{
}
