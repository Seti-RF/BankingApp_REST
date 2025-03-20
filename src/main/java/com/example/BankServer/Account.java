package com.example.BankServer;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int id;
    private String owner;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock(); // Voeg een lock toe


    // Constructor
    public Account(int id, String owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;

    }

    // Getters en Setters
    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }


    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock(); // Lock vrijgeven
        }
    }


    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (amount > balance) {
                return false; // Niet genoeg saldo
            }
            balance -= amount;
            return true;
        } finally {
            lock.unlock();
        }
    
    }
}
