package team_3.BW_CRM.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ruoli")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String tipo;

    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti;

    public Ruolo(String tipo) {
        this.tipo = tipo;
    }
}
