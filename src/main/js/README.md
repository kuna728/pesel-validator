# PESEL (Polish personal ID number) validator
This library provides PESEL number validation according to this [article](https://en.wikipedia.org/wiki/PESEL). Library lets you check if given PESEL number is valid (may exist) and also if given PESEL number match given gender and date of birth values.

## Installation

Library may be installed via npm:

    npm install pesel-validator

Library provides one function which can be imported in following way:

    import peselValidator from 'pesel-validator';

## Usage

To check if given PESEL number is valid you need to call imported function with one *string* type argument - PESEL number. Returned value is *true* or *false*:

    const isPeselValid = peselValidator('12345678901');

To check if given PESEL number is valid and match given date you need to call imported function with two arguments - PESEL number and object with key *dateOfBirth* and value of given date. Given date has to be *Date* or *moment* object.

    const isPeselValidMoment = peselValidator('12345678901', {dateOfBirth: moment("1983-12-06")});
	const isPeselValidDate = peselValidator('12345678901', {dateOfBirth: new Date(1983, 12, 06)});

To check if given PESEL number is valid and match given gender you need to call imported function with two arguments - PESEL number and object with key *gender* and value of given gender. Allowed values for gender argument are listed below:

- *male, man, m*,
- *female, woman, f, w*,
- *other, o*.


	const isPeselValidShortMale = peselValidator('12345678901', {gender: 'm'});
	const isPeselValidLongFemale = peselValidator('12345678901', {gender: 'woman'});

Examples above may be combined:

	const isPeselValid = peselValidator('12345678901', {dateOfBirth: moment("1983-12-06"), gender: 'o'});

## Java
Library has been implemented in JS and java. Java implementation may be found in a [repository root directory](https://github.com/kuna728/pesel-validator).

#

# Walidator numerów pesel [PL]

Biblioteka umożliwia walidację numerów PESEL zgodnie z tym [artykułem](https://pl.wikipedia.org/wiki/PESEL#Numer_PESEL).  Dostępna jest walidacja samego numeru PESEL tzn. czy dany numer PESEL może istnieć i być prawidłowym numerem PESEL. Możliwe jest również sprawdzenie czy dla osoby o danej dacie urodzenia i danej płci podany numer PESEL jest poprawny.

## Instalacja

Bibliotekę można zainstalować za pomocą npm:

	npm install pesel-validator

Biblioteka udostępnia jedną funkcję, którą można zaimportować w następujący sposób:

	import peselValidator from 'pesel-validator';

## Użycie

Aby sprawdzić czy PESEL jest prawidłowy należy wywołać funkcję z 1 argumentem typu *string* - numerem PESEL. Wartością zwróconą będzie *true* albo *false*:

	const isPeselValid = peselValidator('12345678901'); 

Aby sprawdzić czy numer PESEL jest prawidłowy i odpowiada podanej dacie należy wywołać funkcję z 2 argumentami - numerem PESEL i obiektem z  kluczem *dateOfBirth* i wartością podanej daty. Dozwolone jest podanie daty jako obiektu klasy *Date* lub obiektu klasy *moment*.

	const isPeselValidMoment = peselValidator('12345678901', {dateOfBirth: moment("1983-12-06")});
	const isPeselValidDate = peselValidator('12345678901', {dateOfBirth: new Date(1983, 12, 06)});

Aby sprawdzić czy numer PESEL jest prawidłowy i odpowiada podanej płci należy wywołać funkcję z 2 argumentami - numerem PESEL i obiektem z  kluczem *gender* i wartością podanej płci. Dozwolone wartości dla płci to:
- *male, man, m* dla płci męskiej,
- *female, woman, f w* dla płci żeńskiej,
- *other, o* dla płci innej.


	const isPeselValidShortMale = peselValidator('12345678901', {gender: 'm'});
	const isPeselValidLongFemale = peselValidator('12345678901', {gender: 'woman'});

Powyższe przykłady można połączyć tzn. sprawdzić PESEL dla daty i płci:

	const isPeselValid = peselValidator('12345678901', {dateOfBirth: moment("1983-12-06"), gender: 'o'});

## Java
Biblioteka została zaimplementowana w JS i javie. Wersję dla javy można znaleźć w głównym katalogu repozytorium pod tym [linkiem](https://github.com/kuna728/pesel-validator).

 
