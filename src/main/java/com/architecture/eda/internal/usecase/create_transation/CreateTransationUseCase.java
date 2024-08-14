package com.architecture.eda.internal.usecase.create_transation;

import com.architecture.eda.internal.gateway.AccountGateway;
import com.architecture.eda.internal.gateway.TransactionGateway;
import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.entity.Transaction;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransactionInputDTO;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransationOutputDTO;
import com.architecture.eda.internal.usecase.create_transation.factory.CreateTransationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTransationUseCase {
    @Autowired
    private AccountGateway accountGateway;
    @Autowired
    private TransactionGateway transactionGateway;

    public CreateTransationUseCase(AccountGateway accountGateway, TransactionGateway transactionGateway) {
        this.accountGateway = accountGateway;
        this.transactionGateway = transactionGateway;
    }

    public CreateTransationOutputDTO executed(CreateTransactionInputDTO transactionInput){
        Account accountIDFrom = accountGateway.get(transactionInput.accountUuidFrom());
        Account accountIDTo =accountGateway.get(transactionInput.accountUuidFrom());
        Transaction transaction = new Transaction().newTransaction(accountIDFrom
                , accountIDTo, transactionInput.amouth());

        transaction =  transactionGateway.create(transaction);
        return CreateTransationFactory.createTransationOutput(transaction);
    }
}