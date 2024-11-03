package alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO;


import java.time.LocalDateTime;

public record ErrorsResponseDto(String message, LocalDateTime timestamp) {
}
