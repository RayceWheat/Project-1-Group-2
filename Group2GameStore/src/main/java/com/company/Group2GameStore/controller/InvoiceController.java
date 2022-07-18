package com.company.Group2GameStore.controller;

import com.company.Group2GameStore.model.Invoice;
import com.company.Group2GameStore.repository.InvoiceRepository;
import com.company.Group2GameStore.service.ServiceLayer;
import com.company.Group2GameStore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    //Create invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoice) {
        return serviceLayer.createANewInvoice(invoice);
    }

    //Find all invoice, filter by name?
    @RequestMapping(value="/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoice() {
        return serviceLayer.getAllInvoices();
    }
//
//    //Find by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.getInvoiceById(id);
    }
//
////    Update by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceById(@PathVariable int id, @RequestBody InvoiceViewModel ivm) {
        serviceLayer.updateInvoiceById(ivm);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice ivm) {
        serviceLayer.updateInvoice(ivm);
    }


//
//    //Delete by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) { serviceLayer.deleteInvoiceById(id);}

}
