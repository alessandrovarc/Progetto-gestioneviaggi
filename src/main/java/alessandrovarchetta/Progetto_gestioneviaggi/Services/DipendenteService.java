package alessandrovarchetta.Progetto_gestioneviaggi.Services;

import alessandrovarchetta.Progetto_gestioneviaggi.Models.Dipendente;
import alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO.DipendenteDto;
import alessandrovarchetta.Progetto_gestioneviaggi.Repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Page<Dipendente> trovaDipendenti(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageRequest);
    }

    public Dipendente dipendenteSalvataggio(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato con id: " + id));
    }

    public void deleteDipendente(Long id) {
        dipendenteRepository.deleteById(id);
    }

    public Dipendente aggiornaDipendente(Long id, DipendenteDto dipendente) {
        Dipendente esistente = findById(id);
        esistente.setNome(dipendente.getNome());
        esistente.setCognome(dipendente.getCognome());

        return dipendenteRepository.save(esistente);
    }
}