import moment from "moment";

const GENDER_DICT = {
    male: 'm',
    man: 'm',
    m: 'm',
    female: 'f',
    woman: 'f',
    f: 'f',
    w: 'w',
    other: 'o',
    o: 'o'
}

export default function peselValidator(pesel, optionalArgs) {
    const {dateOfBirth = null, gender = null} = optionalArgs || {};

    checkTypes(pesel, dateOfBirth, gender);
    if(!(/^\d+$/.test(pesel) && pesel.length===11))
        return false;
    const datePart = pesel.substring(0, 6);
    const genderPart = pesel.charAt(9);

    // console.log("validateDatePart: " + validateDatePart(datePart));
    // console.log("datesEqual: " + datesEqual(datePart, dateOfBirth));
    // console.log("genderEqual: " + genderEqual(genderPart, gender));
    // console.log("checkSumEqual: " + checkSumEqual(pesel));

    return validateDatePart(datePart) && datesEqual(datePart, dateOfBirth)
        && genderEqual(genderPart, gender) && validateCheckSum(pesel);
}

function validateDatePart(datePart) {
    // stulecia kodowane są w części dla miesięcy
    // https://pl.wikipedia.org/wiki/PESEL#Data_urodzenia
    const yearPart = datePart.substring(0, 2);
    const monthPart = datePart.substring(2, 4);
    const dayPart = datePart.substring(4, 6);
    if(!/^[0-9][0-9]([02468][1-9]|[13579][0-2])$/.test(yearPart + monthPart))
        return false;
    const dayPartInt = parseInt(dayPart);
    // console.log(moment(`${getYearBeginning(yearPart, monthPart)}${yearPart}-${monthPart}`).daysInMonth())
    return dayPartInt > 0 && dayPartInt <=
        moment(`${getYearBeginning(yearPart, monthPart)}${yearPart}-${getDecodedMonth(monthPart)}`, "YYYY-MM").daysInMonth();

}

function datesEqual(datePart, dateOfBirth) {
    if(!dateOfBirth)
        return true;
    const yearPart = datePart.substring(0, 2);
    const monthPart = datePart.substring(2, 4);
    const dayPart = datePart.substring(4, 6);
    // console.log(datePart)
    const dateString = getYearBeginning(yearPart, monthPart) + yearPart + "-" + getDecodedMonth(monthPart) + "-" + dayPart;
    // console.log("dateString " + dateString)
    return moment(dateOfBirth).isSame(dateString, "day");
}

function genderEqual(genderPart, gender) {
    if(!gender)
        return true;
    const lowerCaseGender = gender.toLowerCase();
    let encodedGender;
    if( lowerCaseGender === 'f' || lowerCaseGender === 'female'
        || lowerCaseGender ==='w' || lowerCaseGender === 'woman')
        encodedGender = 0;
    else if (lowerCaseGender === 'm' || lowerCaseGender === 'male'
        || lowerCaseGender === 'man')
        encodedGender = 1;
    else if (lowerCaseGender === 'o' || lowerCaseGender ==='other')
        return true;
    else
        throw new Error("Gender parameter is invalid. " +
            "Allowed case insensitive values ('f', 'female', 'w', 'woman', 'm, 'man', 'male', 'o', 'other')");
    return parseInt(genderPart) % 2 === encodedGender;
}

function validateCheckSum(pesel) {
    // https://pl.wikipedia.org/wiki/PESEL#Cyfra_kontrolna_i_sprawdzanie_poprawno%C5%9Bci_numeru
    const indexWeights = [1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1];

    let sum = 0;
    for(let i = 0; i < 10; i++) {
        sum += parseInt(pesel.charAt(i)) * indexWeights[i];
    }
    const shouldEqual = sum % 10 ? 10 - sum % 10 : 0;
    return parseInt(pesel.charAt(10)) === shouldEqual;
}

function getYearBeginning(yearPart, monthPart) {
    let yearBeginning;
    if(monthPart.startsWith('8') || monthPart.startsWith('9'))
        yearBeginning = '18';
    else if(monthPart.startsWith('0') || monthPart.startsWith('1'))
        yearBeginning = '19';
    else if(monthPart.startsWith('2') || monthPart.startsWith('3'))
        yearBeginning = '20';
    else if(monthPart.startsWith('4') || monthPart.startsWith('5'))
        yearBeginning = '21';
    else
        yearBeginning = '22';
    return yearBeginning;
}

function getDecodedMonth(monthPart) {
    return parseInt(monthPart.charAt(0)) % 2 + monthPart.charAt(1);
}

function checkTypes(pesel, dateOfBirth, gender) {
    if(typeof pesel !== 'string')
        throw new TypeError("Pesel must be a string")
    if(dateOfBirth && !(dateOfBirth instanceof moment && dateOfBirth.isValid())
            && !(Object.prototype.toString.call(dateOfBirth) === "[object Date]" && !isNaN(dateOfBirth)))
        throw new TypeError("Date of birth must be a valid Date or Moment object")
    if(gender && (typeof gender !== 'string' || !Object.keys(GENDER_DICT).includes(gender.toLowerCase())))
        throw new TypeError("Gender must be a string with one of the following values " + Object.keys(GENDER_DICT).join(', '));

}