package com.example.demo.javaproject.service;

import com.example.demo.javaproject.entity.Invoice;
import com.example.demo.javaproject.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice findById(Long id) {
        log.info("Searching for invoice with id: {}", id);
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Invoice with id {} not found", id);
                    return new RuntimeException("Invoice not found");
                });
        log.info("Invoice found: {}", invoice);
        return invoice;
    }

    public List<Invoice> findAll() {
        log.info("Retrieving all invoices");
        List<Invoice> invoices = invoiceRepository.findAll();
        log.info("Retrieved {} invoices", invoices.size());
        return invoices;
    }

    public Invoice save(Invoice invoice) {
        log.info("Saving invoice: {}", invoice);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        log.info("Invoice saved successfully: {}", savedInvoice);
        return savedInvoice;
    }

    public void deleteById(Long id) {
        log.info("Deleting invoice with id: {}", id);
        invoiceRepository.deleteById(id);
        log.info("Invoice with id {} deleted successfully", id);
    }
}