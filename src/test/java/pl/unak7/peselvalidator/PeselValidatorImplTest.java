package pl.unak7.peselvalidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PeselValidatorImplTest {

    private PeselValidator peselValidator;

    private final String EXAMPLE_VALID_PESEL = "83120611127";

    @BeforeEach
    void setup() {
        this.peselValidator = new PeselValidatorImpl();
    }

    @Test
    void wrongPeselFormatTest() {
        Assertions.assertFalse(peselValidator.validate(null));
        Assertions.assertFalse(peselValidator.validate(""));
        Assertions.assertFalse(peselValidator.validate("12pesel3456"));
        Assertions.assertFalse(peselValidator.validate("12345"));
    }

    @Test
    void wrongDatePartTest() {
        Assertions.assertFalse(peselValidator.validate("76132012356"));
        Assertions.assertFalse(peselValidator.validate("22222912345"));
        Assertions.assertFalse(peselValidator.validate("70931012359"));
    }

    @Test
    void wrongCheckSumTest() {
        Assertions.assertFalse(peselValidator.validate("80052012346"));
    }

    @Test
    void validPeselTest() {
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL));
        Assertions.assertTrue(peselValidator.validate("80052012345"));
        Assertions.assertTrue(peselValidator.validate("64120781070"));
        Assertions.assertTrue(peselValidator.validate("22222222222"));
        Assertions.assertTrue(peselValidator.validate("99811273013"));
        Assertions.assertTrue(peselValidator.validate("11710395742"));
    }

    @Test
    void validPeselNotMatchingParamsTest() {
        Assertions.assertFalse(peselValidator.validate(EXAMPLE_VALID_PESEL, LocalDate.of(1983, 11, 6)));
        Assertions.assertFalse(peselValidator.validate(EXAMPLE_VALID_PESEL, createDateObject(1983, 11, 6)));
        Assertions.assertFalse(peselValidator.validate(EXAMPLE_VALID_PESEL, GenderEnum.MALE));
    }

    @Test
    void validPeselMatchingParamsTest() {
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL, LocalDate.of(1983, 12, 6)));
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL, createDateObject(1983, 12, 6)));
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL, GenderEnum.FEMALE));
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL, LocalDate.of(1983, 12, 6), GenderEnum.FEMALE));
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL, createDateObject(1983, 12, 6), GenderEnum.FEMALE));
        Assertions.assertTrue(peselValidator.validate("22222222222", LocalDate.of(2022, 2, 22), GenderEnum.FEMALE));
        Assertions.assertTrue(peselValidator.validate("22222222222", createDateObject(2022, 2, 22), GenderEnum.FEMALE));
    }

    @Test
    void otherGenderTest() {
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL, GenderEnum.OTHER));
        Assertions.assertTrue(peselValidator.validate("99811273013", GenderEnum.MALE));
        Assertions.assertFalse(peselValidator.validate("99811273013", GenderEnum.FEMALE));
        Assertions.assertTrue(peselValidator.validate("99811273013", GenderEnum.OTHER));
    }

    @Test
    void dateNotAtStartOfDayTest() {
        Date dateWithNotZeroHourPart = Date.from(Instant.parse("1983-12-06T05:12:35Z"));
        Assertions.assertTrue(peselValidator.validate(EXAMPLE_VALID_PESEL,dateWithNotZeroHourPart));
    }

    private Date createDateObject(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
