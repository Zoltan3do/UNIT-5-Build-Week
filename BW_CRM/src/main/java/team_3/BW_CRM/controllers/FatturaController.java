package team_3.BW_CRM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team_3.BW_CRM.entities.Fattura;
import team_3.BW_CRM.exceptions.BadRequestException;
import team_3.BW_CRM.payloads.NewFatturaDTO;
import team_3.BW_CRM.services.FatturaService;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fatture")

public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public Page<Fattura> getFatture(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return this.fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/data")
    public Page<Fattura> getFattureByData(
            @RequestParam LocalDate data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.fatturaService.findByData(data, pageable);
    }

    @GetMapping("/anno")
    public Page<Fattura> getFattureByAnno(
            @RequestParam Integer anno,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.fatturaService.findByYear(anno, pageable);
    }

    @GetMapping("/{fatturaNumero}")
    public Page<Fattura> getFattureByNumero(
            @RequestParam Integer numero,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.fatturaService.findByNumero(numero, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Fattura saveFattura(@RequestBody @Validated NewFatturaDTO body, BindingResult validationResult){
        if(validationResult.hasErrors()){
            String message = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Payload error: " + message);
        }
        return this.fatturaService.createFattura(body);
    }

    @GetMapping("/{fatturaId}")
    public Fattura findByIdFattura(@PathVariable Long id) {
        return this.fatturaService.findById(id);
    }

    @PutMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    public Fattura findByIdAndUpdate(
            @PathVariable Long eventoId,
            @RequestBody @Validated NewFatturaDTO body,
            BindingResult validationResult) {
        if (validationResult.hasErrors()) {String message = validationResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(". "));
            throw new BadRequestException("Errori nel payload: " + message);
        }
        return this.fatturaService.findByIdAndUpdate(eventoId, body);
    }

    @DeleteMapping("/{fatturaId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE_EVENTI')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long eventoId) {
        this.fatturaService.findByIdAndDelete(eventoId);
    }



}