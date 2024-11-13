package team_3.BW_CRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team_3.BW_CRM.entities.Cliente;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.payloads.ClienteDTO;
import team_3.BW_CRM.repositories.ClienteRepository;

import java.time.LocalDate;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> getAllClienteList(int page, int size, String sortBy) {
        if (size > 10) size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clienteRepository.findAll(pageable);
    }


    public Cliente findById(long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun utente trovato!"));
    }

    public Page<Cliente> filtraClienti(Double fatturatoMinimo, LocalDate dataInserimento, LocalDate dataUltimoContatto, String parteDelNome, String nomeContatto, String cognomeContatto, Pageable pageable) {
        return clienteRepository.findClienti(fatturatoMinimo, dataInserimento, dataUltimoContatto, parteDelNome, nomeContatto, cognomeContatto, pageable);
    }


    public Cliente save(ClienteDTO body) {
        this.clienteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );
        this.clienteRepository.findByRagioneSociale(body.ragioneSociale()).ifPresent(
                user -> {
                    throw new BadRequestException("Ragione Sociale " + body.ragioneSociale() + " già in uso!");
                }
        );
        this.clienteRepository.findByPartitaIva(body.partitaIva()).ifPresent(
                user -> {
                    throw new BadRequestException("Partita IVA " + body.partitaIva() + " già in uso!");
                }
        );

        Cliente newCliente = new Cliente(body.ragioneSociale(),
                body.partitaIva(),
                body.email(),
                body.pec(),
                body.telefono(),
                body.emailContatto(),
                body.nomeContatto(),
                body.cognomeContatto(),
                body.telefonoContatto(),
                body.tipoCliente()
        );
        Cliente savedCliente = this.clienteRepository.save(newCliente);

        return savedCliente;
    }
}
