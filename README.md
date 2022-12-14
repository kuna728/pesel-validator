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

# Walidator numer??w pesel [PL]

Biblioteka umo??liwia walidacj?? numer??w PESEL zgodnie z tym [artyku??em](https://pl.wikipedia.org/wiki/PESEL#Numer_PESEL).  Dost??pna jest walidacja samego numeru PESEL tzn. czy dany numer PESEL mo??e istnie?? i by?? prawid??owym numerem PESEL. Mo??liwe jest r??wnie?? sprawdzenie czy dla osoby o danej dacie urodzenia i danej p??ci podany numer PESEL jest poprawny.

## Instalacja

Biblioteka jest dost??pna w [maven central](https://central.sonatype.dev/artifact/pl.unak7.pesel-validator/pesel-validator/1.0), wi??c mo??e by?? w prosty spos??b dodana do istniej??cego projektu jako zale??no????:

    <dependency>
        <groupId>pl.unak7.pesel-validator</groupId>
        <artifactId>pesel-validator</artifactId>
        <version>1.0</version>
    </dependency>

Nast??pnie nale??y utworzy?? obiekt *PeselValidator*:

    PeselValidator peselValidator = new PeselValidatorImpl();

Je??eli u??ywasz frameworka CDI (np. *Spring*) najlepszym rozwi??zaniem b??dzie utworzenie *beana*:


    @Bean
    public PeselValidator peselValidator() {
        return new PeselValidatorImpl();
    }

Nast??pnie mo??na wstrzykn???? walidator:

    @Autowired
    private PeselValidator peselValidator;

## U??ycie

Biblioteka udost??pnia 6 metod, kt??re mo??na podzieli?? na 2 grupy na podstawie ich funkcjonalno??ci.

Pierwsza grupa (funkcjonalno????) to walidacja numeru pesel tzn. sprawdzenie czy podany numer pesel mo??e istnie??. T?? funkcjonalno???? implementuj?? 1 metoda:

    boolean validate(String pesel);

Druga grupa (funkcjonalno????) to walidacja numeru PESEL na podstawie podanej daty urodzenia i (lub) p??ci tzn. sprawdzenie czy podany numer PESEL mo??e istnie?? i czy odpowiada podanej dacie urodzenia i (lub) p??ci. Pozosta??e 5 metod implementuj?? t?? funkcjonalno????:

    boolean validate(String pesel, LocalDate dateOfBirth);
    boolean validate(String pesel, Date dateOfBirth);
    boolean validate(String pesel, GenderEnum gender);
    boolean validate(String pesel, LocalDate dateOfBirth, GenderEnum gender);
    boolean validate(String pesel, Date dateOfBirth, GenderEnum gender);

Data urodzenia mo??e by?? typu *Date* lub *LocalDate*. P??e?? musi by?? typu *GenderEnum*, kt??ry udost??pnia sta??e *MALE, FEMALE, OTHER*. 

## JS

Biblioteka zosta??a zaimplementowana w JS i javie. Wersje dla javascript mo??na znale???? w katalogu [src/main/js](https://github.com/kuna728/pesel-validator/tree/master/src/main/js).

