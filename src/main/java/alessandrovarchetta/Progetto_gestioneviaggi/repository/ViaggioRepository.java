package alessandrovarchetta.Progetto_gestioneviaggi.repository;

import alessandrovarchetta.Progetto_gestioneviaggi.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}