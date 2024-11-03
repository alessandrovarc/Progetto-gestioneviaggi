package alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrenotazioneDto(
        @NotNull(message = "la data di prenotazione è obbligatoria")
        LocalDate dataPrenotazione,
        @NotNull(message = "devi inserire le preferenze del dipendente")
        String preferenzeDipendente,
        @NotNull( message = "id dipendente obbligatorio ")
        long iddipendente,
        @NotNull( message = "id viaggio obbligatorio ")
        long idviaggio
        )
{
}
