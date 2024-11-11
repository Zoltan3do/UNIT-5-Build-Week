package team_3.BW_CRM.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "indirizzi")
public class Indirizzo {
    private String via;
    private String civico;
    private String localita;
    private String cap;
    private String comune;
}
