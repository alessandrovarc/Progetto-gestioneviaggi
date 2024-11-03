package alessandrovarchetta.Progetto_gestioneviaggi.repository;

import alessandrovarchetta.Progetto_gestioneviaggi.entities.Dipendente;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Optional<Prenotazione> findByDipendenteAndDataPrenotazione(Dipendente dipendente, LocalDate dataPrenotazione);
}