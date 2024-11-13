package team_3.BW_CRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team_3.BW_CRM.entities.Cliente;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.payloads.ClienteDTO;
import team_3.BW_CRM.services.ClienteService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<Cliente> getCliente(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return clienteService.getAllClienteList(page, size, sortBy);
    }

    @PostMapping("/{clienteId}/invia-email")
    public void sendEmailToCliente(@PathVariable Long clienteId,
                                   @RequestParam String subject,
                                   @RequestParam String message) {
        clienteService.sendEmailToCliente(clienteId, subject, message);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Cliente save(@RequestBody @Validated ClienteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.clienteService.save(body);
    }
}
