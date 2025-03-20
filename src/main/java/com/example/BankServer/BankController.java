package com.example.BankServer;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    private double balance = 1000.00; // Simpele balans simulatie

    // GET: Vraag saldo op
    @GetMapping("/balance")
    public String getBalance() {
        return "Current balance: €" + balance;
    }

    // POST: Voeg geld toe
    @PostMapping("/deposit/{amount}")
    public String deposit(@PathVariable double amount) {
        balance += amount;
        return "€" + amount + " deposited. New balance: €" + balance;
    }

    // POST: Haal geld af
    @PostMapping("/withdraw/{amount}")
    public String withdraw(@PathVariable double amount) {
        if (amount > balance) {
            return "Insufficient funds! Current balance: €" + balance;
        }
        balance -= amount;
        return "€" + amount + " withdrawn. New balance: €" + balance;
    }

}
