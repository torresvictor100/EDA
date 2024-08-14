package com.architecture.eda.internal.usecase.create_account;

import com.architecture.eda.internal.gateway.AccountGateway;
import com.architecture.eda.internal.gateway.ClientGateway;
import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountInputDTO;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.architecture.eda.internal.usecase.create_account.factory.CreateAccountDTOs.createAccountOutputFactory;

@Service
public class CreateAccountUseCase {

    @Autowired
    private AccountGateway accountGateway;
    @Autowired
    private ClientGateway clientGateway;

    public CreateAccountUseCase(AccountGateway accountGateway, ClientGateway clientGateway) {
        this.accountGateway = accountGateway;
        this.clientGateway = clientGateway;
    }

    public CreateAccountOutputDTO executed(CreateAccountInputDTO accountInput) {
        Client client = clientGateway.get(accountInput.clientUuid());
        Account account = new Account().newAccount(client);
        account = accountGateway.save(account);
        return createAccountOutputFactory(account);
    }
}
