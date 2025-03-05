package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.models.Tax;
import br.com.zup.Impostos.repositories.TaxRepository;
import br.com.zup.Impostos.utils.TaxMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
