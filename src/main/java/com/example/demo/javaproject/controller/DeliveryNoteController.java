package com.example.demo.javaproject.controller;

import com.example.demo.javaproject.entity.DeliveryNote;
import com.example.demo.javaproject.entity.Invoice;
import com.example.demo.javaproject.entity.Payment;
import com.example.demo.javaproject.service.DeliveryNoteService;
import com.example.demo.javaproject.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/deliveryNotes")
class DeliveryNoteController {
    private final DeliveryNoteService deliveryNoteService;
    private final InvoiceService invoiceService;

    public DeliveryNoteController(DeliveryNoteService deliveryNoteService, InvoiceService invoiceService) {
        this.deliveryNoteService = deliveryNoteService;
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String getAllDeliveryNote(Model model) {
        model.addAttribute("deliveryNotes", deliveryNoteService.findAll());
        return "deliveryNotes";
    }

    @GetMapping("/new")
    public String createDeliveryNoteForm(Model model) {
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setInvoice(new Invoice());
        model.addAttribute("deliveryNote", deliveryNote);
        return "editDeliveryNote";
    }

    @PostMapping
    public String saveDeliveryNote(@ModelAttribute DeliveryNote deliveryNote) {
        Invoice invoice = invoiceService.findById(deliveryNote.getInvoice().getId());
        if (invoice == null) {
            throw new IllegalArgumentException("DeliveryNote not found: " + deliveryNote.getInvoice().getId());
        }
        deliveryNote.setInvoice(invoice);
        deliveryNote.setDeliveryDate(LocalDate.now());

        deliveryNoteService.save(deliveryNote);
        return "redirect:/deliveryNotes";
    }

    @GetMapping("/edit/{id}")
    public String editDeliveryNoteForm(@PathVariable Long id, Model model) {
        model.addAttribute("deliveryNote", deliveryNoteService.findById(id));
        return "editDeliveryNote";
    }

    @PostMapping("/update/{id}")
    public String updateDeliveryNote(@PathVariable Long id, @ModelAttribute("deliveryNote") DeliveryNote deliveryNote) {
        deliveryNote.setId(id);
        deliveryNote.setInvoice(invoiceService.findById(deliveryNote.getInvoice().getId()));
        deliveryNote.setDeliveryDate(LocalDate.now());
        deliveryNoteService.save(deliveryNote);
        return "redirect:/deliveryNotes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeliveryNote(@PathVariable Long id) {
        deliveryNoteService.deleteById(id);
        return "redirect:/deliveryNotes";
    }
}
