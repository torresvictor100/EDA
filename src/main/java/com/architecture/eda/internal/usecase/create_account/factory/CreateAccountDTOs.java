package com.architecture.eda.internal.usecase.create_account.factory;

import com.architecture.eda.internal.entity.Account;
import com.architecture.eda.internal.usecase.create_account.dtos.CreateAccountOutputDTO;

public class CreateAccountDTOs {
    public static CreateAccountOutputDTO createAccountOutputFactory(Account account){
        return new CreateAccountOutputDTO(account.getUuid());
    }
}
