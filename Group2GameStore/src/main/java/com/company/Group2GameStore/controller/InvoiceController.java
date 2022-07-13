package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Invoice;
import com.company.Group2GameStore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;

    //Create invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) { return invoiceRepository.save(invoice);}

    //Find all invoice, filter by name?
    @RequestMapping(value="/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoice(@RequestParam(required = false) String name) {
        if (name != null) {
            return invoiceRepository.findByName(name);
        }
        return invoiceRepository.findAll();
    }

    //Find by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isPresent() == false) {
            throw new IllegalArgumentException("Invalid Id");
        }

        return invoice.get();
    }

//    Update by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceById(@PathVariable int id, @RequestBody Invoice invoice) {
        if (invoice.getInvoiceId() == null) {
            invoice.setInvoiceId(id);
        } else if (invoice.getInvoiceId() != id) {
            throw new IllegalArgumentException("Id does not match");
        }

        invoiceRepository.save(invoice);
    }

    //Delete by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) { invoiceRepository.deleteById(id);}

}
