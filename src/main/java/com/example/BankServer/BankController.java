package com.example.BankServer;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
public class BankController {
    private Map<Integer, Account> accounts = new HashMap<>();

    //POST:maak een nieuw account aan
    @PostMapping("/create/{id}/{owner}/{balance}")
    public String createAccount(@PathVariable int id, @PathVariable String owner, @PathVariable double balance) {
        if (accounts.containsKey(id)) {
            return "Account with ID " + id + " already exists!";
        }
        accounts.put(id, new Account(id, owner, balance));
        return "Account created: " + owner + " (ID: " + id + "), Balance: €" + balance;
    }

    //GET:Vraag saldo op van een specifiek account
    @GetMapping("/balance/{id}")
    public String getBalance(@PathVariable int id) {
        if (!accounts.containsKey(id)) {
            return "Account not found!";
        }
        return "Current balance: €" + accounts.get(id).getBalance();
    }

    //PUT:voeg geld toe aan een account
    @PutMapping("/deposit/{id}/{amount}")
    public String deposit(@PathVariable int id, @PathVariable double amount) {
        if (!accounts.containsKey(id)) {
            return "Account not found!";
        }
        accounts.get(id).deposit(amount);
        return "€" + amount + " deposited to account " + id + ". New balance: €" + accounts.get(id).getBalance();
    }

    //PUT:haal geld af van een account
    @PutMapping("/withdraw/{id}/{amount}")
    public String withdraw(@PathVariable int id, @PathVariable double amount) {
        if (!accounts.containsKey(id)) {
            return "Account not found!";
        }
        if (!accounts.get(id).withdraw(amount)) {
            return "Insufficient funds!";
        }
        return "€" + amount + " withdrawn from account " + id + ". New balance: €" + accounts.get(id).getBalance();
    }

    //DELETE:verwijder een account
    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id) {
        if (!accounts.containsKey(id)) {
            return "Account not found!";
        }
        accounts.remove(id);
        return "Account with ID " + id + " has been deleted.";
    }



    @GetMapping("/accounts")//om de gemaakte accounts weer te geven in die lijst
    public Map<Integer, Account> getAllAccounts() {
        return accounts;
    }

}
