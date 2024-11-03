package alessandrovarchetta.Progetto_gestioneviaggi.Controllers;

import alessandrovarchetta.Progetto_gestioneviaggi.PayloadDTO.ViaggioDto;
import alessandrovarchetta.Progetto_gestioneviaggi.Services.ViaggioService;
import alessandrovarchetta.Progetto_gestioneviaggi.entities.Viaggio;
import alessandrovarchetta.Progetto_gestioneviaggi.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> getAllViaggi(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "destinazione") String sortBy) {
        return this.viaggioService.findAll(page, size, sortBy);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio getViaggioById(@PathVariable long viaggioId) {
        return this.viaggioService.findViaggioById(viaggioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDto body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.viaggioService.saveViaggio(body);
    }

    @PutMapping("/{viaggioid}")
    public Viaggio updateViaggio(@PathVariable long id, @RequestBody ViaggioDto body) {
        return this.viaggioService.updateViaggio(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable long id) {
        this.viaggioService.deleteViaggio(id);
    }
}
