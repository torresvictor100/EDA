package com.architecture.eda.internal.database.transaction.model;

import com.architecture.eda.internal.database.account.model.AccountModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @ManyToOne
    @JoinColumn(name = "account_from_id", nullable = false)
    private AccountModel accountFrom;
    @ManyToOne
    @JoinColumn(name = "account_to_id", nullable = false)
    private AccountModel accountTo;
    @Column(nullable = false)
    private Integer amount;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createAt;

    public TransactionModel() {
    }

    public TransactionModel(String uuid, AccountModel accountFrom, AccountModel accountTo
            , Integer amount, LocalDateTime createAt) {
        this.uuid = uuid;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.createAt = createAt;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public AccountModel getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(AccountModel accountTo) {
        this.accountTo = accountTo;
    }

    public AccountModel getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(AccountModel accountFrom) {
        this.accountFrom = accountFrom;
    }
}
