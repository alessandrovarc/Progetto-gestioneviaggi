package alessandrovarchetta.Progetto_gestioneviaggi.Repositories;

import alessandrovarchetta.Progetto_gestioneviaggi.Models.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    // Metodi personalizzati se necessari
}
