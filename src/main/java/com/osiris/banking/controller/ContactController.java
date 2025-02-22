package com.osiris.banking.controller;

import com.osiris.banking.dto.AccountDto;
import com.osiris.banking.dto.ContactDto;
import com.osiris.banking.service.impl.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactServiceImpl contactService;

    @PostMapping("/")
    public ResponseEntity<Long> save(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(contactService.findAllByUserId(userId));
    }

}
