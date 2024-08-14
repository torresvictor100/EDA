package com.architecture.eda.internal.database.client.model;

import com.architecture.eda.internal.database.account.model.AccountModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountModel> listAccount;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    public ClientModel() {
    }

    public ClientModel(String uuid, String name, String email,
                       LocalDateTime updateAt, LocalDateTime createAt) {
        this.uuid = uuid;
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.email = email;
        this.name = name;
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

    public List<AccountModel> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<AccountModel> listAccount) {
        this.listAccount = listAccount;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
