package com.example.demo.javaproject.controller;

import com.example.demo.javaproject.entity.Invoice;
import com.example.demo.javaproject.entity.User;
import com.example.demo.javaproject.service.InvoiceService;
import com.example.demo.javaproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final UserService userService;

    public InvoiceController(InvoiceService invoiceService, UserService userService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.findAll());
        return "invoices";
    }

    @GetMapping("/new")
    public String createInvoiceForm(Model model) {
        Invoice invoice = new Invoice();
        invoice.setUser(new User());
        model.addAttribute("invoice", invoice);
        return "editInvoice";
    }

    @PostMapping
    public String saveInvoice(@ModelAttribute Invoice invoice) {
        User user = userService.findByUsername(invoice.getUser().getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + invoice.getUser().getUsername());
        }
        invoice.setUser(user);
        invoice.setDate(LocalDate.now());

        invoiceService.save(invoice);
        return "redirect:/invoices";
    }

    @GetMapping("/edit/{id}")
    public String editInvoiceForm(@PathVariable Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findById(id));
        return "editInvoice";
    }

    @PostMapping("/update/{id}")
    public String updateInvoice(@PathVariable Long id, @ModelAttribute("invoice") Invoice invoice) {
        invoice.setId(id);
        invoice.setDate(LocalDate.now());
        invoice.setUser(userService.findByUsername(invoice.getUser().getUsername()));
        invoiceService.save(invoice);
        return "redirect:/invoices";
    }

    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteById(id);
        return "redirect:/invoices";
    }
}
