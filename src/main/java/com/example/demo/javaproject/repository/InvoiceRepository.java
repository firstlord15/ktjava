package com.example.demo.javaproject.repository;

import com.example.demo.javaproject.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {

}