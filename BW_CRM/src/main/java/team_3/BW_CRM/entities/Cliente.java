package team_3.BW_CRM.entities;

import jakarta.persistence.*;
import team_3.BW_CRM.enums.TipoCliente;

import java.time.LocalDate;

@Entity
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
}
