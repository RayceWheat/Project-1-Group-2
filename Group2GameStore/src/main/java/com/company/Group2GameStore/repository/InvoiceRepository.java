package com.company.Group2GameStore.repository;

import com.company.Group2GameStore.model.Invoice;
import com.company.Group2GameStore.model.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByName(String name);

}
