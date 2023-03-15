package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test suite")
public class ClientTest {

    public static final int VALID_ID = 0;
    public static final String VALID_CLIENT_NAME = "dummy client name";


    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        //region given
        final int clientId = VALID_ID;
        final String clientName = VALID_CLIENT_NAME;
        //endregion

        //region when
        Client sut = new Client(clientId, clientName);
        //endregion

        //region then
        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

        //Hamcrest:
        assertThat(sut,
            allOf(
                hasProperty("id", notNullValue()),
                hasProperty("id", equalTo(clientId)),
                hasProperty("name", is(clientName))
        ));

        //AssertJ:
        org.assertj.core.api.Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("id", clientId)
                .hasFieldOrPropertyWithValue("name", clientName);
        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
        //endregion
    }

    @Test
    public void shouldReturnProperValuesWhenArgumentsAreNonDefault() {
        int validNonDefaultId = 1;

        Client sut = new Client(validNonDefaultId, VALID_CLIENT_NAME);

        assertEquals(validNonDefaultId, sut.getId());
        assertEquals(VALID_CLIENT_NAME, sut.getName());
    }

    @Test
    public void shouldThrowExceptionWhenClientIdNegative() {
        final int clientId = -1;

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, VALID_CLIENT_NAME));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        final String clientName = null;

        assertThrows(IllegalArgumentException.class, () -> new Client(VALID_ID, clientName));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        final String clientName = "";

        assertThrows(IllegalArgumentException.class, () -> new Client(VALID_ID, clientName));
    }
}
