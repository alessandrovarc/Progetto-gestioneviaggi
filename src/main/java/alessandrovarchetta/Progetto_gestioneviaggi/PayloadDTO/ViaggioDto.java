package alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViaggioDto(
        @NotNull( message = "è necessaria la destinazione")
        String destinazione,
        @NotNull(message = "inserire la data del viaggio")
        LocalDate dataviaggio,
        @NotNull(message = "bisogna sapere se è ancora in corso")
        String statoprenotazione

) {
}
