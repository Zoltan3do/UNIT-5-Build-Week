package team_3.BW_CRM.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team_3.BW_CRM.entities.Cliente;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.exceptions.NotFoundException;
import team_3.BW_CRM.payloads.ClienteDTO;
import team_3.BW_CRM.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente findById(long id) {
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun utente trovato!"));
    }

    public Cliente findByEmail(String email) {
        return this.clienteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Nessuna mail trovata"));
    }

    public Cliente findByPartitaIva(String partitaIva) {
        return this.clienteRepository.findByPartitaIva(partitaIva).orElseThrow(() -> new NotFoundException("Nessuna mail trovata"));
    }

    public Cliente findByRagioneSociale(String ragioneSociale) {
        return this.clienteRepository.findByRagioneSociale(ragioneSociale).orElseThrow(() -> new NotFoundException("Nessuna mail trovata"));
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
