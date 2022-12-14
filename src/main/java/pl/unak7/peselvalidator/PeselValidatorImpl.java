package pl.unak7.peselvalidator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class PeselValidatorImpl implements PeselValidator {

    private String pesel;
    private String datePart;
    private char checkSumPart;
    private LocalDate decodedDate;
    private GenderEnum decodedGender;

    @Override
    public boolean validate(String pesel) {
        if(!preValidation(pesel))
            return false;
        initializeParts(pesel);

        return validateDatePart() && validateCheckSum();
    }

    @Override
    public boolean validate(String pesel, LocalDate dateOfBirth) {
        return validate(pesel) && dateOfBirth.isEqual(decodedDate);
    }

    @Override
    public boolean validate(String pesel, Date dateOfBirth) {
        return validate(pesel, dateToLocalDate(dateOfBirth));
    }

    @Override
    public boolean validate(String pesel, GenderEnum gender) {
        return validate(pesel) && (gender == decodedGender || gender == GenderEnum.OTHER);
    }

    @Override
    public boolean validate(String pesel, LocalDate dateOfBirth, GenderEnum gender) {
        return validate(pesel, dateOfBirth) && (gender == decodedGender || gender == GenderEnum.OTHER);
    }

    @Override
    public boolean validate(String pesel, Date dateOfBirth, GenderEnum gender) {
        return validate(pesel, dateOfBirth) && (gender == decodedGender || gender == GenderEnum.OTHER);
    }

    private boolean preValidation(String pesel) {
        return pesel != null && pesel.matches("^\\d{11}$");
    }

    private void initializeParts(String pesel) {
        this.pesel = pesel;
        this.datePart = pesel.substring(0, 6);
        char genderPart = pesel.charAt(9);
        this.checkSumPart = pesel.charAt(10);
        this.decodedGender = Character.getNumericValue(genderPart) % 2 == 0 ? GenderEnum.FEMALE : GenderEnum.MALE;
    }

    private boolean validateDatePart() {
        String yearPart = datePart.substring(0, 2);
        String monthPart = datePart.substring(2, 4);
        String dayPart = datePart.substring(4, 6);

        String decodedYearPart;
        if(monthPart.startsWith("8") || monthPart.startsWith("9"))
            decodedYearPart = "18";
        else if(monthPart.startsWith("0") || monthPart.startsWith("1"))
            decodedYearPart = "19";
        else if(monthPart.startsWith("2") || monthPart.startsWith("3"))
            decodedYearPart = "20";
        else if(monthPart.startsWith("4") || monthPart.startsWith("5"))
            decodedYearPart = "21";
        else
            decodedYearPart = "22";
        decodedYearPart += yearPart;

        int decodedMonthPartBeginning = Character.getNumericValue(monthPart.charAt(0)) % 2;
        String decodedMonthPart = Integer.toString(decodedMonthPartBeginning) + monthPart.charAt(1);
        String isoLocalDate = decodedYearPart + "-" + decodedMonthPart + "-" + dayPart;
        try {
            this.decodedDate = LocalDate.parse(isoLocalDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validateCheckSum() {
        int[] indexWeights = new int[] {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;
        for(int i = 0; i<10; i++) {
            sum += Character.getNumericValue(pesel.charAt(i)) * indexWeights[i];
        }
        int checkSum = sum % 10 == 0 ? 0 : 10 - sum % 10;
        return checkSum == Character.getNumericValue(checkSumPart);
    }

    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
