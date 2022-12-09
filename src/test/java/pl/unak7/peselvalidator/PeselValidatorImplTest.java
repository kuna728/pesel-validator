package pl.unak7.peselvalidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeselValidatorImplTest {

    private PeselValidator peselValidator;

    @BeforeEach
    void setup() {
        this.peselValidator = new PeselValidatorImpl();
    }

    @Test
    void basicTest() {
        Assertions.assertTrue(peselValidator.validate("99123005353"));
    }










































































}
