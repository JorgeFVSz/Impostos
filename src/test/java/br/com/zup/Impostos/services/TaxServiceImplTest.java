package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.TaxCalculationRequestDTO;
import br.com.zup.Impostos.dtos.TaxCalculationResponseDTO;
import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.enums.TaxType;
import br.com.zup.Impostos.exceptions.DuplicateEntryException;
import br.com.zup.Impostos.exceptions.TaxNotFoundException;
import br.com.zup.Impostos.models.Tax;
import br.com.zup.Impostos.repositories.TaxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaxServiceImplTest {

    private TaxRepository taxRepository;
    private TaxServiceImpl taxService;

    @BeforeEach
    void setUp() {
        taxRepository = mock(TaxRepository.class);
        taxService = new TaxServiceImpl(taxRepository);
    }

    @Test
    void shouldGetAllTaxesSuccessfully() {

        Tax tax1 = new Tax("ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0, TaxType.ICMS);
        Tax tax2 = new Tax("ISS", "Imposto sobre Serviços", 5.0, TaxType.ISS);
        when(taxRepository.findAll()).thenReturn(Arrays.asList(tax1, tax2));

        List<TaxDTO> taxes = taxService.getAllTaxes();

        assertEquals(2, taxes.size());
        assertEquals("ICMS", taxes.get(0).getNome());
        assertEquals(18.0, taxes.get(0).getAliquota());
        assertEquals("ISS", taxes.get(1).getNome());
        assertEquals(5.0, taxes.get(1).getAliquota());
    }

    @Test
    void shouldCreateTaxSuccessfully() {

        TaxDTO taxDTO = new TaxDTO(null, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        Tax tax = new Tax("ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0, TaxType.ICMS);
        when(taxRepository.existsByName("ICMS")).thenReturn(false);
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);

        TaxDTO createdTax = taxService.createTax(taxDTO);

        assertEquals("ICMS", createdTax.getNome());
        assertEquals(18.0, createdTax.getAliquota());
    }

    @Test
    void shouldThrowExceptionWhenCreatingDuplicateTax() {

        TaxDTO taxDTO = new TaxDTO(null, "ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0);
        when(taxRepository.existsByName("ICMS")).thenReturn(true);

        assertThrows(DuplicateEntryException.class, () -> taxService.createTax(taxDTO));
    }

    @Test
    void shouldCalculateTaxSuccessfully() {

        Tax tax = new Tax("ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0, TaxType.ICMS);
        TaxCalculationRequestDTO requestDTO = new TaxCalculationRequestDTO("1", 1000.0);
        when(taxRepository.findById("1")).thenReturn(Optional.of(tax));

        TaxCalculationResponseDTO responseDTO = taxService.calculateTax(requestDTO);

        assertEquals("ICMS", responseDTO.getTipoImposto());
        assertEquals(1000.0, responseDTO.getValorBase());
        assertEquals(18.0, responseDTO.getAliquota());
        assertEquals(180.0, responseDTO.getValorImposto());
    }

    @Test
    void shouldThrowExceptionWhenCalculatingTaxForNonExistentId() {

        TaxCalculationRequestDTO requestDTO = new TaxCalculationRequestDTO("1", 1000.0);
        when(taxRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(TaxNotFoundException.class, () -> taxService.calculateTax(requestDTO));
    }

    @Test
    void shouldGetTaxByIdSuccessfully() {

        Tax tax = new Tax("ICMS", "Imposto sobre Circulação de Mercadorias e Serviços", 18.0, TaxType.ICMS);
        when(taxRepository.findById("1")).thenReturn(Optional.of(tax));

        TaxDTO taxDTO = taxService.getTaxById("1");

        assertEquals("ICMS", taxDTO.getNome());
        assertEquals("Imposto sobre Circulação de Mercadorias e Serviços", taxDTO.getDescricao());
        assertEquals(18.0, taxDTO.getAliquota());
    }

    @Test
    void shouldDeleteTaxByIdSuccessfully() {

        when(taxRepository.existsById("1")).thenReturn(true);

        assertDoesNotThrow(() -> taxService.deleteTaxById("1"));
        verify(taxRepository, times(1)).deleteById("1");
    }

}