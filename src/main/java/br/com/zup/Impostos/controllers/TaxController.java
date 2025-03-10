package br.com.zup.Impostos.controllers;

import br.com.zup.Impostos.dtos.TaxCalculationRequestDTO;
import br.com.zup.Impostos.dtos.TaxCalculationResponseDTO;
import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<TaxDTO> createTax(@RequestBody TaxDTO taxDTO) {
        TaxDTO taxResonseDTO = taxService.createTax(taxDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taxResonseDTO);
    }

    @PostMapping("/calculo")
    public ResponseEntity<TaxCalculationResponseDTO> calculateTax(@RequestBody TaxCalculationRequestDTO taxCalculationRequestDTO) {
        TaxCalculationResponseDTO  taxCalculationResponseDTO = taxService.calculateTax(taxCalculationRequestDTO);
        return ResponseEntity.ok(taxCalculationResponseDTO);
    }
}
