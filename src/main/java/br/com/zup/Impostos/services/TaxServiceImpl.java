package br.com.zup.Impostos.services;

import br.com.zup.Impostos.repositories.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxServiceImpl implements TaxService{
    @Autowired
    private TaxRepository taxRepository;
}
