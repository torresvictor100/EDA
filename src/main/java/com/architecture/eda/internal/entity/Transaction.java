package com.architecture.eda.internal.entity;

import com.architecture.eda.internal.exceptions.InsufficienteFoundsError;
import com.architecture.eda.internal.exceptions.InvalidAmount;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private Long id;
    private String uuid;
    private Account accountFrom;
    private Account accountTo;
    private Integer amount;
    private LocalDateTime createAt;

    public Transaction() {
    }

    private Transaction(Account accountFrom, Account accountTo, Integer amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.uuid = UUID.randomUUID().toString();;
        this.createAt = LocalDateTime.now();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Transaction newTransaction(Account accountFrom, Account accountTo, Integer amount){
        validate(accountFrom, amount);
        commit(accountFrom, accountTo, amount);
        return new Transaction(accountFrom, accountTo, amount);
    }

    private void validate(Account accountFrom, Integer amount) {
        if(amount <= 0){
            throw new InvalidAmount("amount must be greater the zero");
        }
        if (accountFrom.getBalance() < amount){
            throw new InsufficienteFoundsError("Insuffient founds");
        }
    }

    private void commit(Account accountFrom, Account accountTo, Integer amount){
        accountFrom.debit(amount);
        accountTo.credit(amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
