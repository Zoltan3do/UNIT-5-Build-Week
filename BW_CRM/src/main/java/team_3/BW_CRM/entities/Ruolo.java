package team_3.BW_CRM.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@ToString
@Entity
@Table(name = "ruoli")
public class Ruolo {
    private final String tipo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti;

    public Ruolo(String tipo) {
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }


    public String getTipo() {
        return tipo;
    }


    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }
}
