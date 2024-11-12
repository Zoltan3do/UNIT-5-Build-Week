package team_3.BW_CRM.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ProvinciaDTO (
    @NotEmpty
    @Min(3)
    String nome,
    @NotEmpty
    @Size(min=2,max = 2,message="La sigla deve essere di 2 caratteri")
    String sigla,
    @NotEmpty
    @Size(min=3)
    String regione
){

}
