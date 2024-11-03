package alessandrovarchetta.Progetto_gestioneviaggi.Controllers;

import alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO.PrenotazioneDto;
import alessandrovarchetta.Progetto_gestioneviaggi.Services.PrenotazioneService;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Prenotazione;
import alessandrovarchetta.Progetto_gestioneviaggi.exceptions.BadRequestException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    PrenotazioneService prenotazioneService;

    @GetMapping
    public Page<Prenotazione> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "data_Prenotazione") String sortBy) {
        return this.prenotazioneService.findPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable long id) {
        return this.prenotazioneService.findPrenotazioniById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Validated PrenotazioneDto body, @NotNull BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);

        return this.prenotazioneService.savePrenotazione(body);
    }

    }
}