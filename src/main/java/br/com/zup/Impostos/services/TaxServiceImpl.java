package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.TaxCalculationRequestDTO;
import br.com.zup.Impostos.dtos.TaxCalculationResponseDTO;
import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.enums.TaxType;
import br.com.zup.Impostos.exceptions.DuplicateEntryException;
import br.com.zup.Impostos.exceptions.InvalidTaxTypeException;
import br.com.zup.Impostos.exceptions.TaxNotFoundException;
import br.com.zup.Impostos.models.Tax;
import br.com.zup.Impostos.repositories.TaxRepository;
import br.com.zup.Impostos.utils.TaxMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaxServiceImpl implements TaxService{
    @Autowired
    private TaxRepository taxRepository;

    @Override
    public List<TaxDTO> getAllTaxes() {
        List<Tax> taxesEntity = taxRepository.findAll();
        return TaxMapperUtil.toDTOList(taxesEntity);
    }

    @Override
    public TaxDTO createTax(TaxDTO taxDTO) {
        TaxType taxType = Arrays.stream(TaxType.values())
                .filter(type -> type.name().equalsIgnoreCase(taxDTO.getNome()))
                .findFirst()
                .orElseThrow(() -> new InvalidTaxTypeException("O tipo de imposto " + taxDTO.getNome() + " não é válido. Deve ser um dos seguintes: "
                        + Arrays.toString(TaxType.values())));

        if(taxRepository.existsByName(taxDTO.getNome())) {
            throw new DuplicateEntryException("Imposto com nome " + taxDTO.getNome() + " já está cadastrado");
        }

        Tax tax = new Tax(taxDTO.getNome(), taxDTO.getDescricao(), taxDTO.getAliquota(), taxType);
        Tax saveTax = taxRepository.save(tax);
        return new TaxDTO(saveTax.getUuid(),saveTax.getName(),saveTax.getDescription(),saveTax.getRate());
    }

    @Override
    public TaxCalculationResponseDTO calculateTax(TaxCalculationRequestDTO taxCalculationRequestDTO) {
        Tax tax = taxRepository.findById(taxCalculationRequestDTO.getTipoImpostoId())
                .orElseThrow(() -> new TaxNotFoundException("Imposto com id" + taxCalculationRequestDTO.getTipoImpostoId() + "não foi encontrado"));

        double taxValue = tax.getTaxType().calculateTax(taxCalculationRequestDTO.getValorBase(), tax.getRate());

        return new TaxCalculationResponseDTO(
                tax.getName(),
                taxCalculationRequestDTO.getValorBase(),
                tax.getRate(),
                taxValue
        );
    }
}
