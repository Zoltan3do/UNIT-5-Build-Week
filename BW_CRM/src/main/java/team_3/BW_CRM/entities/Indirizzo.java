package team_3.BW_CRM.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String via;
    private String civico;
    private String localita;
    private String cap;

    @OneToOne(mappedBy = "sedeLegale")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    public Indirizzo(String via, String civico, String localita, String cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
    }
}
