package com.architecture.eda.internal.entity;

import com.architecture.eda.internal.exceptions.AccountAlready;
import com.architecture.eda.internal.exceptions.InvalidClientEmail;
import com.architecture.eda.internal.exceptions.InvalidClientName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private Long id;
    private String uuid;
    private String name;
    private String email;
    private List<Account> listAccount;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Client() {
    }

    public Client newCLient(String name, String email)  {
        validate(name, email);
        Client client = new Client(name, email);
        return client;
    }

    public void updateCLient(String name, String email)  {
        validate(name, email);
        this.setName(name);
        this.setEmail(email);
        this.setUpdateAt(LocalDateTime.now());

    }

    private Client(String name, String email) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.listAccount = new ArrayList<>();
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public  void validate(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidClientName("Client name cannot be empty.");
        }

        if (email == null || !email.contains("@")
                || email.trim().isEmpty()) {
            throw new InvalidClientEmail("Email must contain '@com'.");
        }
    }

    public void addAccount(Account Account) {
        if(listAccount.contains(Account)){
            throw new AccountAlready("account already belongs");
        }
        this.listAccount.add(Account);
    }

    public List<Account> getAccounts() {
        return listAccount;
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

    public List<Account> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
