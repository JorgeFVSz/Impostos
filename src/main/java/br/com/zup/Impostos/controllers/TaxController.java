package br.com.zup.Impostos.controllers;

import br.com.zup.Impostos.dtos.TaxCalculationRequestDTO;
import br.com.zup.Impostos.dtos.TaxCalculationResponseDTO;
import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.services.TaxService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaxController {
    @Autowired
    private TaxService taxService;

    @GetMapping("/tipos")
    public ResponseEntity<List<TaxDTO>> getAllTaxes() {
        List<TaxDTO> taxes = taxService.getAllTaxes();
        return ResponseEntity.ok(taxes);
    }

    @PostMapping("/tipos")
    public ResponseEntity<TaxDTO> createTax(@Valid @RequestBody TaxDTO taxDTO) {
        TaxDTO taxResonseDTO = taxService.createTax(taxDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taxResonseDTO);
    }

    @PostMapping("/calculo")
    public ResponseEntity<TaxCalculationResponseDTO> calculateTax(@Valid @RequestBody TaxCalculationRequestDTO taxCalculationRequestDTO) {
        TaxCalculationResponseDTO  taxCalculationResponseDTO = taxService.calculateTax(taxCalculationRequestDTO);
        return ResponseEntity.ok(taxCalculationResponseDTO);
    }

    @GetMapping("/tipos/{id}")
    public ResponseEntity<TaxDTO> getTaxById(@PathVariable String id) {
        TaxDTO taxResponseDTO = taxService.getTaxById(id);
        return ResponseEntity.ok(taxResponseDTO);
    }

    @DeleteMapping("/tipos/{id}")
    public ResponseEntity<Void> deleteTaxById(@PathVariable String id) {
        taxService.deleteTaxById(id);
        return ResponseEntity.noContent().build();
    }
}
