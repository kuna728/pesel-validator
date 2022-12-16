# PESEL (Polish personal ID number) validator
This library provides PESEL number validation according to this [article](https://en.wikipedia.org/wiki/PESEL). Library lets you check if given PESEL number is valid (may exist) and also if given PESEL number match given gender and date of birth values.

## Installation

Library was published on [central repository](https://central.sonatype.dev/artifact/pl.unak7.pesel-validator/pesel-validator/1.0) and can be easily installed by adding as dependency:

    <dependency>
        <groupId>pl.unak7.pesel-validator</groupId>
        <artifactId>pesel-validator</artifactId>
        <version>1.0</version>
    </dependency>

Then to use validator you need to create *PeselValidatorImpl* object. It can be simply done in following way:

    PeselValidator peselValidator = new PeselValidatorImpl();

If you use CDI framework like *Spring* the recommended way is to create one *PeselValidator* object and mark it as a *bean*:

    @Bean
    public PeselValidator peselValidator() {
        return new PeselValidatorImpl();
    }

Then you can inject *PeselValidator* wherever you want:

    @Autowired
    private PeselValidator peselValidator;
    
## Usage

Library provides 6 methods which implements 2 major functionalities. 

First one is validating standalone PESEL i.e. checking if given PESEL may exist. One method implements this functionality:
    
    boolean validate(String pesel);

Second one is validating PESEL with given params i.e checking if given PESEL is valid and matches given params (date of birth, gender). Remaining 5 methods implements this functionality:

    boolean validate(String pesel, LocalDate dateOfBirth);
    boolean validate(String pesel, Date dateOfBirth);
    boolean validate(String pesel, GenderEnum gender);
    boolean validate(String pesel, LocalDate dateOfBirth, GenderEnum gender);
    boolean validate(String pesel, Date dateOfBirth, GenderEnum gender);

Date of birth may be provided as both *Date* and *LocalDate* object. Gender uses *GenderEnum* with constants like *MALE, FEMALE, OTHER*. 

## JS

Library has been implemented in JS and java. JS implementation may be found in [src/main/js](https://github.com/kuna728/pesel-validator/tree/master/src/main/js) directory.

#

# Walidator numerów pesel [PL]

Biblioteka umożliwia walidację numerów PESEL zgodnie z tym [artykułem](https://pl.wikipedia.org/wiki/PESEL#Numer_PESEL).  Dostępna jest walidacja samego numeru PESEL tzn. czy dany numer PESEL może istnieć i być prawidłowym numerem PESEL. Możliwe jest również sprawdzenie czy dla osoby o danej dacie urodzenia i danej płci podany numer PESEL jest poprawny.

## Instalacja

Biblioteka jest dostępna w [maven central](https://central.sonatype.dev/artifact/pl.unak7.pesel-validator/pesel-validator/1.0), więc może być w prosty sposób dodana do istniejącego projektu jako zależność:

    <dependency>
        <groupId>pl.unak7.pesel-validator</groupId>
        <artifactId>pesel-validator</artifactId>
        <version>1.0</version>
    </dependency>

Następnie należy utworzyć obiekt *PeselValidator*:

    PeselValidator peselValidator = new PeselValidatorImpl();

Jeżeli używasz frameworka CDI (np. *Spring*) najlepszym rozwiązaniem będzie utworzenie *beana*:


    @Bean
    public PeselValidator peselValidator() {
        return new PeselValidatorImpl();
    }

Następnie można wstrzyknąć walidator:

    @Autowired
    private PeselValidator peselValidator;

## Użycie

Biblioteka udostępnia 6 metod, które można podzielić na 2 grupy na podstawie ich funkcjonalności.

Pierwsza grupa (funkcjonalność) to walidacja numeru pesel tzn. sprawdzenie czy podany numer pesel może istnieć. Tą funkcjonalność implementuję 1 metoda:

    boolean validate(String pesel);

Druga grupa (funkcjonalność) to walidacja numeru PESEL na podstawie podanej daty urodzenia i (lub) płci tzn. sprawdzenie czy podany numer PESEL może istnieć i czy odpowiada podanej dacie urodzenia i (lub) płci. Pozostałe 5 metod implementuję tą funkcjonalność:

    boolean validate(String pesel, LocalDate dateOfBirth);
    boolean validate(String pesel, Date dateOfBirth);
    boolean validate(String pesel, GenderEnum gender);
    boolean validate(String pesel, LocalDate dateOfBirth, GenderEnum gender);
    boolean validate(String pesel, Date dateOfBirth, GenderEnum gender);

Data urodzenia może być typu *Date* lub *LocalDate*. Płeć musi być typu *GenderEnum*, który udostępnia stałe *MALE, FEMALE, OTHER*. 

## JS

Biblioteka została zaimplementowana w JS i javie. Wersje dla javascript można znaleźć w katalogu [src/main/js](https://github.com/kuna728/pesel-validator/tree/master/src/main/js).

