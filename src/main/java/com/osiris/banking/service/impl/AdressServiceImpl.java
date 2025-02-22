package com.osiris.banking.service.impl;

import com.osiris.banking.dto.AdressDto;
import com.osiris.banking.entity.Adress;
import com.osiris.banking.repository.AdressRepository;
import com.osiris.banking.service.AdressService;
import com.osiris.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    private final AdressRepository adressRepository;
    private final ObjectsValidator<AdressDto> validator;

    @Override
    public Long save(AdressDto dto) {
        validator.validate(dto);
        Adress adress = AdressDto.toEntity(dto);
        return adressRepository.save(adress).getId();
    }

    @Override
    public List<AdressDto> findAll() {
        return adressRepository.findAll()
                .stream()
                .map(AdressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AdressDto findById(Long id) {
        return adressRepository.findById(id)
                .map(AdressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune adresse trouvée à partir de l'ID fournit "+id));
    }

    @Override
    public void delete(Long id) {
        adressRepository.deleteById(id);
    }
}
