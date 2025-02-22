package com.osiris.banking.controller;

import com.osiris.banking.dto.AdressDto;
import com.osiris.banking.service.impl.AdressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adress")
@RequiredArgsConstructor
public class AdressController {

    private final AdressServiceImpl adressService;

    @PostMapping("/")
    public ResponseEntity<Long> save(@RequestBody AdressDto adressDto) {
        return ResponseEntity.ok(adressService.save(adressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AdressDto>> findAll() {
        return ResponseEntity.ok(adressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdressDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adressService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adressService.delete(id);
        return ResponseEntity.accepted().build();
    }

}
