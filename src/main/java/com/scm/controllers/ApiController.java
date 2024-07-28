package com.scm.controllers;

import com.scm.Entities.Contact;
import com.scm.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private ContactService contactService;

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable Long contactId) {
        return contactService.getById(contactId);
    }

}
