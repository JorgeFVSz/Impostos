package br.com.zup.Impostos.controllers;

import br.com.zup.Impostos.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {
    @Autowired
    private TaxService taxService;
}
