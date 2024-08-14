package com.architecture.eda.internal.database.account.model;

import com.architecture.eda.internal.database.client.model.ClientModel;
import com.architecture.eda.internal.database.transaction.model.TransactionModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(nullable = false)
    private Integer balance;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientModel client;
    @OneToMany(mappedBy = "accountFrom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionModel> listTransactionFrom;
    @OneToMany(mappedBy = "accountTo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionModel> listTransactionTo;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    public AccountModel() {
    }

    public AccountModel(String uuid, ClientModel client, Integer balance
            , LocalDateTime updateAt, LocalDateTime createAt) {
        this.uuid = uuid;
        this.client = client;
        this.balance = balance;
        this.updateAt = updateAt;
        this.createAt = createAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<TransactionModel> getListTransactionTo() {
        return listTransactionTo;
    }

    public void setListTransactionTo(List<TransactionModel> listTransactionTo) {
        this.listTransactionTo = listTransactionTo;
    }

    public List<TransactionModel> getListTransactionFrom() {
        return listTransactionFrom;
    }

    public void setListTransactionFrom(List<TransactionModel> listTransactionFrom) {
        this.listTransactionFrom = listTransactionFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
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
