package com.architecture.eda.internal.usacase;

import com.architecture.eda.internal.gateway.AccountGateway;
import com.architecture.eda.internal.gateway.ClientGateway;
import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.usecase.create_account.CreateAccountUseCase;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountInputDTO;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountOutputDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CreateAccountUseCaseTest {
    @Mock
    private ClientGateway clientGateway;
    @Mock
    private AccountGateway accountGateway;

    @Test
    public void testCreateAccountUsaCase(){
        String name = "name";
        String email = "email@email.com";

        final var client =  new Client().newCLient(name, email);
        when(clientGateway.get(any())).thenReturn(client);

        final var account = new Account().newAccount(client);
        when(accountGateway.save(any())).thenReturn(account);

        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(
                accountGateway ,clientGateway);

        CreateAccountOutputDTO createAccount = createAccountUseCase.executed(new CreateAccountInputDTO(account.getClient().getUuid()));

        Assertions.assertNotNull(createAccount);
        Assertions.assertEquals(account.getUuid(), createAccount.accountUuid());

        verify(clientGateway, times(1)).get(any());
        verify(accountGateway, times(1)).save(any());
    }
}
