package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    public static final int VALID_ID = 0;
    public static final double VALID_AMOUNT = 0.0;
    public static final Client VALID_CLIENT = new Client(VALID_ID, "A Client");


    @Test
    public void shouldCreateSavingAccountWhenArgumentsAreValid() {
        SavingAccount sut = new SavingAccount(VALID_ID, VALID_CLIENT, VALID_AMOUNT);

        assertEquals(VALID_ID, sut.getId());
        assertEquals(VALID_CLIENT, sut.getClient());
        assertEquals(VALID_AMOUNT, sut.getAmount());
    }

    @Test
    public void shouldReturnProperValuesWhenArgumentsAreNonDefault() {
        int validNonDefaultId = 1;
        double validNonDefaultValue = 1.0;
        SavingAccount sut = new SavingAccount(validNonDefaultId, VALID_CLIENT, validNonDefaultValue);

        assertEquals(validNonDefaultId, sut.getId());
        assertEquals(VALID_CLIENT, sut.getClient());
        assertEquals(validNonDefaultValue, sut.getAmount());
    }

    @Test
    public void shouldNotCreateClientWhenIdIsNegative() {
        int negativeId = -1;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(negativeId, VALID_CLIENT, VALID_AMOUNT));
    }

    @Test
    public void shouldNotCreateClientWhenClientIsAbsent() {
        Client client = null;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID, client, VALID_AMOUNT));
    }

    @Test
    public void shouldNotCreateClientWhenAmountIsNegative() {
        double negativeAmount = -1.0;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(VALID_ID, VALID_CLIENT, negativeAmount));
    }
}
