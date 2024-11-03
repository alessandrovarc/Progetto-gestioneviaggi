package alessandrovarchetta.Progetto_gestioneviaggi.exceptions;


import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(long id) {
		super("l'elemento " + id + "non è stato trovato");
	}
}
