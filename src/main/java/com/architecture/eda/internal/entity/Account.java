package com.architecture.eda.internal.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private Long id;
    private String uuid;
    private Client client;
    private Integer balance;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Account() {
    }

    public Account(Client client) {
        this.client = client;
        this.uuid = UUID.randomUUID().toString();
        this.balance = 0;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Account newAccount(Client client)  {
        Account account = new Account(client);
        return account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getBalance() {
        return balance;
    }

    public void credit(Integer amount) {
        this.balance = balance + amount;
        this.updateAt = LocalDateTime.now();
    }

    public void debit(Integer amount) {
        this.balance = balance - amount;
        this.updateAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
