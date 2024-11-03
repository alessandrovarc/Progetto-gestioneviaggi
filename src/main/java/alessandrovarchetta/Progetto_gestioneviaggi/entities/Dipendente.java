package alessandrovarchetta.Progetto_gestioneviaggi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cognome;
    
    @Column(unique = true)
    private String email;
    
    private String telefono;
    
    @Column(name = "ruolo")
    private String ruolo;


    public Dipendente(String username, String nome, String cognome, String email, String imgProfilo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.imgProfilo = imgProfilo;
    }
}

