package team_3.BW_CRM.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String nome;
    private String sigla;
    private String regione;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni = new ArrayList<>();

    public Provincia(String nome, String sigla, String regione) {
        this.nome = nome;
        this.sigla = sigla;
        this.regione =regione;
    }
}
