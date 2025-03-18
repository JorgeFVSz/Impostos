package br.com.zup.Impostos.utils;

import br.com.zup.Impostos.dtos.TaxDTO;
import br.com.zup.Impostos.models.Tax;

import java.util.List;
import java.util.stream.Collectors;

public class TaxMapperUtil {

    private TaxMapperUtil() {
    }

    public static TaxDTO toDTO(Tax tax) {
        TaxDTO dto = new TaxDTO();
        dto.setId(tax.getUuid());
        dto.setNome(tax.getName());
        dto.setDescricao(tax.getDescription());
        dto.setAliquota(tax.getRate());
        return dto;
    }

    public static List<TaxDTO> toDTOList(List<Tax> taxes) {
        return taxes.stream()
                .map(TaxMapperUtil::toDTO)
                .collect(Collectors.toList());
    }
}
