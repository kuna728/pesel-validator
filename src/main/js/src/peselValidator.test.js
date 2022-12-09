const peselValidator = require("./peselValidator");
const moment = require("moment");

const EXAMPLE_VALID_PESEL = "83120611127";

test('wrong type params', () => {
    expect(() => peselValidator(12)).toThrow(TypeError);
    expect(() => peselValidator(new Date())).toThrow(TypeError);
    expect(() => peselValidator(EXAMPLE_VALID_PESEL, {dateOfBirth: 12.22})).toThrow(TypeError);
    expect(() => peselValidator(EXAMPLE_VALID_PESEL, {gender: 12.22})).toThrow(TypeError);
})

test('empty pesel', () => {
    expect(peselValidator("")).toBeFalsy();
})

test('not digit pesel', () => {
    expect(peselValidator("abcdefghijk")).toBeFalsy();
    expect(peselValidator("1234567890a")).toBeFalsy();
})

test('wrong pesel length', () => {
    expect(peselValidator("1234567890")).toBeFalsy();
    expect(peselValidator("123456789012")).toBeFalsy();
})

test('wrong date part', () => {
    expect(peselValidator("76132012356")).toBeFalsy();
    expect(peselValidator("22222912345")).toBeFalsy();
    expect(peselValidator("70931012359")).toBeFalsy();
})

test('wrong checksum', () => {
    expect(peselValidator("80052012346")).toBeFalsy();
})

test('valid pesel', () => {
    expect(peselValidator(EXAMPLE_VALID_PESEL)).toBeTruthy();
    expect(peselValidator("80052012345")).toBeTruthy();
    expect(peselValidator("64120781070")).toBeTruthy();
    expect(peselValidator("22222222222")).toBeTruthy();
    expect(peselValidator("99811273013")).toBeTruthy();
    expect(peselValidator("11710395742")).toBeTruthy();
})

test('valid pesel not matching params', () => {
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {dateOfBirth: moment("1983-12-07")}))
        .toBeFalsy();
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {gender: 'm'}))
        .toBeFalsy();
})

test('valid pesel matching params', () => {
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {dateOfBirth: moment("1983-12-06")}))
        .toBeTruthy();
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {gender: 'f'}))
        .toBeTruthy();
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {dateOfBirth: moment("1983-12-06"), gender: 'f'}))
        .toBeTruthy();
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {dateOfBirth: new Date(1983, 11, 6), gender: 'w'}))
        .toBeTruthy();
    expect(peselValidator("22222222222",
        {dateOfBirth: moment("2022-02-22"), gender: 'female'}))
        .toBeTruthy();
})

test('other gender', () => {
    expect(peselValidator(EXAMPLE_VALID_PESEL,
        {gender: 'o'}))
        .toBeTruthy();
    expect(peselValidator("99811273013",
        {gender: 'male'}))
        .toBeTruthy();
    expect(peselValidator("99811273013",
        {gender: 'female'}))
        .toBeFalsy();
    expect(peselValidator("99811273013",
        {gender: 'other'}))
        .toBeTruthy();
})
