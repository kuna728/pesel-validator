package pl.unak7.peselvalidator;

import java.time.LocalDate;
import java.util.Date;

public class PeselValidatorImpl implements PeselValidator {

    private String pesel;
    private String datePart;
    private String genderPart;
    private String checkSumPart;

    @Override
    public boolean validate(String pesel) {
        if(!preValidation(pesel))
            return false;
        this.pesel = pesel;
        return true;
    }

    @Override
    public boolean validate(String pesel, LocalDate dateOfBirth) {
        return false;
    }

    @Override
    public boolean validate(String pesel, Date dateOfBirth) {
        return false;
    }

    @Override
    public boolean validate(String pesel, GenderEnum gender) {
        return false;
    }

    @Override
    public boolean validate(String pesel, LocalDate dateOfBirth, GenderEnum gender) {
        return false;
    }

    @Override
    public boolean validate(String pesel, Date dateOfBirth, GenderEnum gender) {
        return false;
    }

    private boolean preValidation(String pesel) {
        return pesel != null && pesel.matches("^\\d{11}$");
    }

    private void initializeParts() {
    }
}
