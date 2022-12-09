package pl.unak7.peselvalidator;

import java.time.LocalDate;
import java.util.Date;

public interface PeselValidator {
    boolean validate(String pesel);
    boolean validate(String pesel, LocalDate dateOfBirth);
    boolean validate(String pesel, Date dateOfBirth);
    boolean validate(String pesel, GenderEnum gender);
    boolean validate(String pesel, LocalDate dateOfBirth, GenderEnum gender);
    boolean validate(String pesel, Date dateOfBirth, GenderEnum gender);
}
