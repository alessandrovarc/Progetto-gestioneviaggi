package alessandrovarchetta.Progetto_gestioneviaggi.Services;

import alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO.ViaggioDto;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Viaggio;
import alessandrovarchetta.Progetto_gestioneviaggi.exceptions.NotFoundException;
import alessandrovarchetta.Progetto_gestioneviaggi.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Page<Viaggio> findAll(int page, int size, String sortBy) {
        if (page > 15) page = 15;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio findViaggioById(long viaggioId) {
        return this.viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException(viaggioId));
    }

    public Viaggio saveViaggio(ViaggioDto body) {
        Viaggio viaggio = new Viaggio(
                body.destinazione(),
                body.dataviaggio(),
                body.statoprenotazione());
        return this.viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(long viaggioId, ViaggioDto body) {
        Viaggio viaggio = this.findViaggioById(viaggioId);
        viaggio.setDestinazione(body.destinazione());
        viaggio.setDataviaggio(body.dataviaggio());
        viaggio.setStatoPrenotazione(body.statoprenotazione());
        return this.viaggioRepository.save(viaggio);
    }

    public void deleteViaggio(long id) {
        Viaggio viaggio = this.findViaggioById(id);
        this.viaggioRepository.delete(viaggio);
    }
}