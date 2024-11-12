package team_3.BW_CRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team_3.BW_CRM.entities.Cliente;
import team_3.BW_CRM.entities.Fattura;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.payloads.NewFatturaDTO;
import team_3.BW_CRM.repositories.ClienteRepository;
import team_3.BW_CRM.repositories.FatturaRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FatturaService {

    @Autowired
    FatturaRepository fatturaRepository;

    @Autowired
    ClienteRepository clienteRepository;



    public Fattura createFattura(NewFatturaDTO body) {

     if(fatturaRepository.existsByNumero(body.numero())) {
         throw new BadRequestException("Numero fattura " + body.numero() + " già in uso!");
     }

     Optional<Cliente> clienteFound = clienteRepository.findById(body.clienteId().getId());

     if (clienteFound.isEmpty()) {
         throw new NotFoundException("Cliente non trovato!");
     }

     Fattura fattura = new Fattura(
             body.data(),
             body.numero(),
             body.importo(),
             clienteFound.get()
     );

     return fatturaRepository.save(fattura);

    }

    public Fattura findById(Long id) {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessuna fattura trovata con questo ID: " + id));
    }

    public Fattura findByIdAndUpdate(Long id, NewFatturaDTO body) {
        Fattura fattura = this.findById(id);
        if(body.data().isBefore(LocalDate.now())){
            throw new BadRequestException("Non puoi mettere una data passata a quella odierna!");
        }

        if(!body.data().equals(fattura.getData())) {
            fattura.setData(body.data());
        }

        if(!body.clienteId().equals(fattura.getCliente().getId())) {
            fattura.setCliente(body.clienteId());
        }

        if(!body.importo().equals(fattura.getImporto())) {
            fattura.setImporto(body.importo());
        }

        if (fatturaRepository.existsByNumero(body.numero()) && !body.numero().equals(fattura.getNumero())) {
            throw new BadRequestException("Esiste già una fattura con questo numero!");
        }

        if (!body.numero().equals(fattura.getNumero())) {
            fattura.setNumero(body.numero());
        }

        if(!body.statoFattura().equals(fattura.getStatoFattura())) {
            fattura.setStatoFattura(body.statoFattura());
        }

        return fatturaRepository.save(fattura);
    }

    public Fattura findByNumeroAndUpdate(Integer numero, NewFatturaDTO body) {
        Fattura fattura = fatturaRepository.findByNumero(numero).orElseThrow(() -> new NotFoundException("Nessuna fattura trovata con questo numero: " + numero));
        if(body.data().isBefore(LocalDate.now())){
            throw new BadRequestException("Non puoi mettere una data passata a quella odierna!");
        }

        if(!body.data().equals(fattura.getData())) {
            fattura.setData(body.data());
        }

        if (!body.clienteId().equals(fattura.getCliente().getId())) {
            Cliente cliente = clienteRepository.findById(body.clienteId().getId())
                    .orElseThrow(() -> new NotFoundException("Cliente non trovato con id " + body.clienteId()));
            fattura.setCliente(cliente);
        }

        if(!body.importo().equals(fattura.getImporto())) {
            fattura.setImporto(body.importo());
        }

        if (fatturaRepository.existsByNumero(body.numero()) && !body.numero().equals(fattura.getNumero())) {
            throw new BadRequestException("Esiste già una fattura con questo numero!");
        }

        if (!body.numero().equals(fattura.getNumero())) {
            fattura.setNumero(body.numero());
        }

        if(!body.statoFattura().equals(fattura.getStatoFattura())) {
            fattura.setStatoFattura(body.statoFattura());
        }

        return fatturaRepository.save(fattura);
    }

    public Fattura findByNumero(Integer numero) {
        return fatturaRepository.findByNumero(numero).orElseThrow(() -> new NotFoundException("Nessuna fattura con questo numero: " + numero));
    }

    public Page<Fattura> findByYear(Integer anno, Pageable pageable) {
        Page<Fattura> fatture = fatturaRepository.findByAnno(anno, pageable);

        if (fatture.isEmpty()) {
            throw new NotFoundException("Nessuna fattura trovata per l'anno " + anno + ".");
        }

        return fatture;
    }

    public Page<Fattura> findByData(LocalDate data, Pageable pageable) {
        Page<Fattura> fatture = fatturaRepository.findByData(data, pageable);

        if (fatture.isEmpty()) {
            throw new NotFoundException("Nessuna fattura trovata per la data " + data + ".");
        }

        return fatture;
    }

    public Page<Fattura> findByRange(Double minImporto, Double maxImport, Pageable pageable) {
        Page<Fattura> fatture = fatturaRepository.findByImportoBetween(minImporto, maxImport, pageable);

        if (fatture.isEmpty()) {
            throw new NotFoundException("Nessuna fattura trovata con l'importo compresto tra " + minImporto + " e " + maxImport + ".");
        }
        return fatture;
    }

    public Page<Fattura> findByIdCliente(Long id, Pageable pageable) {
        Page<Fattura> fatture = fatturaRepository.findByClienteId(id, pageable);
        if (fatture.isEmpty()) {
            throw new NotFoundException("Nessuna fattura trovata per l'utente con questo ID: " + id);
        }
        return fatture;
    }

    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findAll(pageable);
    }

    public void findByIdAndDelete(Long id) {
        Fattura found = this.findById(id);
        fatturaRepository.delete(found);
    }

    public void findByNumeroAndDelete(Integer numero) {
        Fattura found = this.findByNumero(numero);
        fatturaRepository.delete(found);
    }

}
