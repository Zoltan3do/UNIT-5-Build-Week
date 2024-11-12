package team_3.BW_CRM.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team_3.BW_CRM.entities.Comune;
import team_3.BW_CRM.entities.Provincia;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.payloads.ComuneDTO;
import team_3.BW_CRM.payloads.ProvinciaDTO;
import team_3.BW_CRM.repositories.ComuneRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ComuneService {

    @Autowired
    private ComuneRepository cr;

    @Autowired
    private ProvinciaService ps;

    @Autowired
    private Validator validator;

    public Comune save(ComuneDTO body) {
        Optional<Provincia> p = ps.findByNome(body.provincia());
        if (p.isEmpty()) {
            throw new NotFoundException("Provincia non trovata");
        }
        return this.cr.save(new Comune(body.codProvincia(), body.codComune(), body.nome(), p.get()));
    }

    public void estrazioneComuniCsv(String path) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linea;
            br.readLine();
            linea = br.readLine();
            while (linea != null) {
                String[] colonne = linea.split(";");
                if (colonne.length < 4) {
                    log.warn("Riga non valida: "+ linea);
                    linea = br.readLine();
                    continue;
                }

                String codProvincia = colonne[0].trim();
                String codComune = colonne[1].trim();
                String nomeComune = colonne[2].trim();
                String nomeProvincia = colonne[3].trim();

                ComuneDTO comuneDTO = new ComuneDTO(codProvincia, codComune, nomeComune, nomeProvincia);

                Set<ConstraintViolation<ComuneDTO>> violations = validator.validate(comuneDTO);
                if (!violations.isEmpty()) {
                    for (ConstraintViolation<ComuneDTO> violation : violations) {
                        log.warn("Errore di validazione per il comune "+ nomeComune+ " "+ violation.getMessage());
                    }
                    linea = br.readLine();
                    continue;
                }

                Optional<Provincia> provincia = ps.findByNome(nomeProvincia);
                if (provincia.isPresent()) {
                    this.save(comuneDTO);
                    log.info("Comune "+ nomeComune+" salvato con successo.");
                }
                linea = br.readLine();
            }
        } catch (Exception e) {
            log.error("Errore durante l'estrazione dei comuni dal CSV", e);
        }
    }



}