package com.architecture.eda.internal.gateway.impl;

import com.architecture.eda.internal.database.transaction.db.TransctionDB;
import com.architecture.eda.internal.database.transaction.model.TransactionModel;
import com.architecture.eda.internal.entity.Transaction;
import com.architecture.eda.internal.gateway.TransactionGateway;
import org.springframework.stereotype.Service;

import static com.architecture.eda.internal.entity.factory.TransactionFactory.createTransaction;
import static com.architecture.eda.internal.entity.factory.TransactionFactory.createTransactionModel;

@Service
public class TransactionGatewayImpl implements TransactionGateway {
    private TransctionDB transctionDB;
    @Override
    public Transaction create(Transaction transaction) {
        TransactionModel transactionModel = transctionDB.save(createTransactionModel(transaction));
        return createTransaction(transactionModel);
    }
}
