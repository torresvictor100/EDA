package com.architecture.eda.internal.usacase;

import com.architecture.eda.internal.gateway.ClientGateway;
import com.architecture.eda.internal.entity.Client;
import com.architecture.eda.internal.usecase.create_cliente.CreateClientUseCase;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientInputDTO;
import com.architecture.eda.internal.usecase.create_cliente.dtos.CreateClientOutputDTO;
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
public class CreateClientUseCaseTest {

    @Mock
    private ClientGateway clientGateway;

    @Test
    public void testCreateClientUsaCase(){

        String name = "name";
        String email = "email@email.com";

        final var client =  new Client().newCLient(name, email);

        when(clientGateway.save(any())).thenReturn(client);

        CreateClientUseCase createCLientUseCase = new CreateClientUseCase(clientGateway);
        CreateClientInputDTO createClientInput = new CreateClientInputDTO(name, email);

        CreateClientOutputDTO clientCreate = createCLientUseCase.executed(createClientInput);

        Assertions.assertNotNull(createClientInput);
        Assertions.assertEquals(name, clientCreate.name());
        Assertions.assertEquals(email, clientCreate.email());

        verify(clientGateway, times(1)).save(any());
    }
}
