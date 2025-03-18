package br.com.zup.Impostos.controllers;

import br.com.zup.Impostos.dtos.TaxCalculationRequestDTO;
import br.com.zup.Impostos.dtos.TaxCalculationResponseDTO;
import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.services.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxControllerTest {

    private TaxService taxService;
    private TaxController taxController;

    @BeforeEach
    void setUp() {
        taxService = mock(TaxService.class);
        taxController = new TaxController(taxService);
    }

    @Test
    void shouldGetAllTaxesSuccessfully() {

        List<TaxDTO> mockTaxes = Arrays.asList(new TaxDTO(), new TaxDTO());
        when(taxService.getAllTaxes()).thenReturn(mockTaxes);

        ResponseEntity<List<TaxDTO>> response = taxController.getAllTaxes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockTaxes, response.getBody());
        verify(taxService, times(1)).getAllTaxes();
    }

    @Test
    void shouldCreateTaxSuccessfully() {

        TaxDTO requestDTO = new TaxDTO();
        TaxDTO responseDTO = new TaxDTO();
        when(taxService.createTax(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TaxDTO> response = taxController.createTax(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(taxService, times(1)).createTax(requestDTO);
    }

    @Test
    void shouldCalculateTaxSuccessfully() {

        TaxCalculationRequestDTO requestDTO = new TaxCalculationRequestDTO();
        TaxCalculationResponseDTO responseDTO = new TaxCalculationResponseDTO();
        when(taxService.calculateTax(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TaxCalculationResponseDTO> response = taxController.calculateTax(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(taxService, times(1)).calculateTax(requestDTO);
    }

    @Test
    void shouldGetTaxByIdSuccessfully() {
        String uuid = UUID.randomUUID().toString();
        
        TaxDTO responseDTO = new TaxDTO();
        when(taxService.getTaxById(uuid)).thenReturn(responseDTO);

        ResponseEntity<TaxDTO> response = taxController.getTaxById(uuid);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(taxService, times(1)).getTaxById(uuid);
    }

    @Test
    void shouldDeleteTaxByIdSuccessfully() {
        String uuid = UUID.randomUUID().toString();
        
        doNothing().when(taxService).deleteTaxById(uuid);

        ResponseEntity<Void> response = taxController.deleteTaxById(uuid);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taxService, times(1)).deleteTaxById(uuid);
    }
}