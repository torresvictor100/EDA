package com.architecture.eda.internal.database.transaction.db;

import com.architecture.eda.internal.database.RepositoryInterface;
import com.architecture.eda.internal.database.transaction.model.TransactionModel;
import jakarta.persistence.Table;

import java.util.Optional;

@Table(name = "transction")
public interface TransctionDB extends RepositoryInterface<TransactionModel, Long> {
    Optional<TransactionModel> findByUuid(String uuid);
}
