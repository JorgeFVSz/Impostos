package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.TaxCalculationRequestDTO;
import br.com.zup.Impostos.dtos.TaxCalculationResponseDTO;
import br.com.zup.Impostos.dtos.TaxDTO;

import java.util.List;

public interface TaxService {
    List<TaxDTO> getAllTaxes();
    TaxDTO createTax(TaxDTO taxDTO);
    TaxCalculationResponseDTO calculateTax(TaxCalculationRequestDTO taxCalculationRequestDTO);
    TaxDTO getTaxById(String id);
    void deleteTaxById(String id);
}
