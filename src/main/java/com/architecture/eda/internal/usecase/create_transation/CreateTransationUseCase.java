package com.architecture.eda.internal.usecase.create_transation;

import com.architecture.eda.internal.gateway.AccountGateway;
import com.architecture.eda.internal.gateway.TransactionGateway;
import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.entity.Transaction;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransactionInputDTO;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransationOutputDTO;
import com.architecture.eda.internal.usecase.create_transation.factory.CreateTransationFactory;
import com.architecture.eda.share.events.EventDispatcherInterface;
import com.architecture.eda.share.events.impl.EventTransactionCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTransationUseCase {
    @Autowired
    private AccountGateway accountGateway;
    @Autowired
    private TransactionGateway transactionGateway;
    @Autowired
    private EventDispatcherInterface eventDispatcher;

    public CreateTransationUseCase(AccountGateway accountGateway, TransactionGateway transactionGateway
            , EventDispatcherInterface eventDispatcher) {
        this.accountGateway = accountGateway;
        this.transactionGateway = transactionGateway;
        this.eventDispatcher = eventDispatcher;
    }

    public CreateTransationOutputDTO executed(CreateTransactionInputDTO transactionInput) throws Exception {
        Account accountIDFrom = accountGateway.get(transactionInput.accountUuidFrom());
        Account accountIDTo =accountGateway.get(transactionInput.accountUuidTo());
        Transaction transaction = new Transaction().newTransaction(accountIDFrom
                , accountIDTo, transactionInput.amouth());

        transaction =  transactionGateway.create(transaction);

        CreateTransationOutputDTO createTransationOutput = CreateTransationFactory.createTransationOutput(transaction);

        EventTransactionCreated eventTransactionCreated = new EventTransactionCreated();
        eventTransactionCreated.setPayload(createTransationOutput);
        eventDispatcher.dispatch(eventTransactionCreated);

        return createTransationOutput;
    }
}
