package com.example.demo.javaproject.controller;

import com.example.demo.javaproject.entity.Invoice;
import com.example.demo.javaproject.entity.Payment;
import com.example.demo.javaproject.service.InvoiceService;
import com.example.demo.javaproject.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/payments")
class PaymentController {
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;

    public PaymentController(PaymentService paymentService, InvoiceService invoiceService) {
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String getAllPayments(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        return "payments";
    }

    @GetMapping("/new")
    public String createPaymentForm(Model model) {
        Payment payment = new Payment();
        payment.setInvoice(new Invoice());
        model.addAttribute("payment", payment);
        return "editPayment";
    }

    @PostMapping
    public String savePayment(@ModelAttribute Payment payment) {
        Invoice invoice = invoiceService.findById(payment.getInvoice().getId());
        if (invoice == null) {
            throw new IllegalArgumentException("Payment not found: " + payment.getInvoice().getId());
        }
        payment.setInvoice(invoice);
        payment.setPaymentDate(LocalDate.now());

        paymentService.save(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        model.addAttribute("payment", paymentService.findById(id));
        return "editPayment";
    }

    @PostMapping("/update/{id}")
    public String updatePayment(@PathVariable Long id, @ModelAttribute("Payment") Payment payment) {
        payment.setId(id);
        payment.setInvoice(invoiceService.findById(payment.getInvoice().getId()));
        payment.setPaymentDate(LocalDate.now());
        paymentService.save(payment);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
        return "redirect:/payments";
    }
}
