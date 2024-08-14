package com.architecture.eda.internal.usecase.create_transation.factory;

import com.architecture.eda.internal.entity.Transaction;
import com.architecture.eda.internal.usecase.create_transation.dtos.CreateTransationOutputDTO;

public class CreateTransationFactory {

    public static CreateTransationOutputDTO createTransationOutput(Transaction transaction){
        return new CreateTransationOutputDTO(transaction.getUuid());
    }
}
