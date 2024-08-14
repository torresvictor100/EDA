package com.architecture.eda.internal.entity.factory;

import com.architecture.eda.internal.database.transaction.model.TransactionModel;
import com.architecture.eda.internal.entity.Transaction;

import static com.architecture.eda.internal.entity.factory.AccountFactory.createAccount;
import static com.architecture.eda.internal.entity.factory.AccountFactory.createAccountModel;

public class TransactionFactory {
    public static TransactionModel createTransactionModel(Transaction transaction){
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setUuid(transaction.getUuid());
        transactionModel.setAmount(transaction.getAmount());
        transactionModel.setAccountTo(createAccountModel(transaction.getAccountTo()));
        transactionModel.setAccountFrom(createAccountModel(transaction.getAccountFrom()));
        transactionModel.setCreateAt(transaction.getCreateAt());
        return transactionModel;
    }

    public static Transaction createTransaction(TransactionModel transactionModel){
        Transaction transaction = new Transaction();
        transaction.setId(transactionModel.getId());
        transaction.setUuid(transactionModel.getUuid());
        transaction.setAmount(transactionModel.getAmount());
        transaction.setAccountTo(createAccount(transactionModel.getAccountTo()));
        transaction.setAccountFrom(createAccount(transactionModel.getAccountFrom()));
        transaction.setCreateAt(transactionModel.getCreateAt());
        return transaction;
    }
}
