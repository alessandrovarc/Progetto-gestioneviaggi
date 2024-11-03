package alessandrovarchetta.Progetto_gestioneviaggi.Services;

import alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO.PrenotazioneDto;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Dipendente;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Prenotazione;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Viaggio;
import alessandrovarchetta.Progetto_gestioneviaggi.exceptions.BadRequestException;
import alessandrovarchetta.Progetto_gestioneviaggi.exceptions.NotFoundException;
import alessandrovarchetta.Progetto_gestioneviaggi.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private DipendenteService dipendenteService;

    public Page<Prenotazione> findPrenotazioni(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findPrenotazioniById(long id) {
        return this.prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Prenotazione savePrenotazione(PrenotazioneDto body) {
        Dipendente dipendente = this.dipendenteService.findById(body.iddipendente());
        Viaggio viaggio = this.viaggioService.findViaggioById(body.idviaggio());

        prenotazioneRepository.findByDipendenteAndDataPrenotazione(dipendente, body.dataPrenotazione())
                .ifPresent(p -> {
                    throw new BadRequestException("Data non disponibile per questo dipendente!");
                });

        Prenotazione nuovaPrenotazione = new Prenotazione(
                body.dataPrenotazione(),
                body.preferenzeDipendente(),
                viaggio,
                dipendente);
        return this.prenotazioneRepository.save(nuovaPrenotazione);
    }

    public Prenotazione aggiornaPrenotazioni(long id, PrenotazioneDto body) {
        Prenotazione prenotazione = this.findPrenotazioniById(id);
        prenotazione.setPreferenzeDipendente(body.preferenzeDipendente());
        prenotazione.setDataPrenotazione(body.dataPrenotazione());
        return this.prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(long id) {
        Prenotazione prenotazione = this.findPrenotazioniById(id);
        this.prenotazioneRepository.delete(prenotazione);
    }
}