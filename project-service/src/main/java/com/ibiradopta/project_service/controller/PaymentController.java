package com.ibiradopta.project_service.controller;

import com.ibiradopta.project_service.models.Payment;
import com.ibiradopta.project_service.models.dto.PaymentDto;
import com.ibiradopta.project_service.services.impl.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Get all payments", description = "Obtain all existing payments, the respone is a list of payments")
    @GetMapping("/getall")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @Operation(summary = "Get payments by filters", description = "Obtain existing payments by filters, the respone is a list of payments")
    @GetMapping("/filters")
    @PreAuthorize("hasRole('ROLE_Administrador')")
    public ResponseEntity<List<PaymentDto>> getPaymentsByFilters(
            @RequestParam(required = false) Integer projectId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(paymentService.getPaymentsByFilters(projectId, userId, startDate, endDate));
    }
}
