package alessandrovarchetta.Progetto_gestioneviaggi.Controllers;

import alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO.DipendenteDto;
import alessandrovarchetta.Progetto_gestioneviaggi.Services.DipendenteService;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Dipendente;
import alessandrovarchetta.Progetto_gestioneviaggi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;

   
    @GetMapping
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "nome") String sortBy) {
        return this.dipendenteService(size,page,sortBy);
    }
   

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable long id) {
        return this.dipendenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente creaDipendente(@RequestBody @Validated DipendenteDto body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.dipendenteService.dipendenteSalvataggio(body);
    }

    @PutMapping("/{id}")
    public Dipendente aggiornaDipendente(@PathVariable long id, @RequestBody DipendenteDto body) {
        return this.dipendenteService.aggiornaDipendente(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaDipendente(@PathVariable long id) {
        this.dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/{id}/immagine")
    public String caricaImmagine(@RequestParam("immagine") MultipartFile immagine) {
        return this.dipendenteService.caricaImmagineProfilo(immagine);
    }
}
