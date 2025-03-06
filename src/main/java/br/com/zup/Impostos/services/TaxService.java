package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.TaxDTO;

import java.util.List;

public interface TaxService {
    List<TaxDTO> getAllTaxes();
    TaxDTO createTax(TaxDTO taxDTO);
}
