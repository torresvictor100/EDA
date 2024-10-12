package com.architecture.eda.internal.usacase;

import com.architecture.eda.internal.gateway.AccountGateway;
import com.architecture.eda.internal.gateway.TransactionGateway;
import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.entity.Transaction;
import com.architecture.eda.internal.usecase.create_transation.CreateTransationUseCase;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransactionInputDTO;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransationOutputDTO;
import com.architecture.eda.share.events.EventDispatcherInterface;
import com.architecture.eda.share.events.impl.EventTransactionCreated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CreateTransactionUseCaseTest {

    @Mock
    private TransactionGateway transactionGateway;
    @Mock
    private AccountGateway accountGateway;
    @Mock
    private EventDispatcherInterface eventDispatcher;
    @InjectMocks
    private CreateTransationUseCase createTransactionUseCase;

    @Test
    public void newTransaction() throws Exception {
        MockitoAnnotations.initMocks(this);
        String nameAccountFrom = "nameAccountFrom";
        String emailAccountFrom  = "nameAccountFrom@email.com";

        final var clientAccountFrom = new Client().newCLient(nameAccountFrom, emailAccountFrom);
        final var accountFrom = new Account(clientAccountFrom);

        accountFrom.credit(101);
        String nameAccountTo = "nameAccountTo";
        String emailAccountTo  = "emailAccountTo@email.com";

        final var clientaAccountTo = new Client().newCLient(nameAccountTo, emailAccountTo);
        final var accountTo = new Account(clientaAccountTo);

        Transaction transaction = new Transaction();
        transaction.setCreateAt(LocalDateTime.now());
        transaction.setAccountFrom(accountFrom);
        transaction.setAccountTo(accountTo);
        transaction.setAmount(100);
        transaction.setUuid(UUID.randomUUID().toString());

        when(transactionGateway.create(any())).thenReturn(transaction);
        when(accountGateway.get(accountFrom.getUuid())).thenReturn(accountFrom);
        when(accountGateway.get(accountTo.getUuid())).thenReturn(accountTo);

        CreateTransationUseCase createCLientUseCase = new CreateTransationUseCase(accountGateway,
                transactionGateway, eventDispatcher);

        CreateTransationOutputDTO transactionCreate = createCLientUseCase.executed(
                new CreateTransactionInputDTO(accountFrom.getId()
                        ,accountFrom.getUuid(), accountTo.getUuid(), 100));

        Assertions.assertNotNull(transactionCreate);
        Assertions.assertEquals(transaction.getUuid(),transactionCreate.transationUuid());

        verify(transactionGateway, times(1)).create(any());
        verify(accountGateway, times(2)).get(any());

        verify(eventDispatcher, times(1)).dispatch(any(EventTransactionCreated.class));
    }
}
