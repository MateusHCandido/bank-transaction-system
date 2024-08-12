package com.github.io.MateusHCandido.account_service.domain;

import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.domain.exception.AccountTypeException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidCnpjException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidCpfException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


class AccountTest {

    private  static final String validCpf = "12345678910";
    private  static final String invalidCpf = "1098765432";

    private  static final String validCnpj = "12345678910001";

    private  static final String invalidCnpj = "1234567891012355";



    private AccountType legalPerson = AccountType.LEGAL_PERSON;
    private AccountType naturalPerson =  AccountType.NATURAL_PERSON;



    @Test
    public void shouldCreateAnAccountWithSuccess() {
        Account account = new Account(
                123456L,
                new BigDecimal("1000.00"),
                AccountType.NATURAL_PERSON,
                "John Doe",
                "12345678901",
                "john.doe@example.com",
                1L
        );

        assertEquals(123456L, account.getAccountNumber());
        assertEquals(new BigDecimal("1000.00"), account.getAccountBalance());
        assertEquals(AccountType.NATURAL_PERSON, account.getAccountType());
        assertEquals("John Doe", account.getAccountName());
        assertEquals("12345678901", account.getAccountCpfCnpj());
        assertEquals("john.doe@example.com", account.getAccountEmail());
        assertEquals(1L, account.getBankCode());
    }

    @Test
    @DisplayName("The test will not allow entry of a null value within the AccountType")
    void shouldThrownAccountTypeException(){
        Exception exception = assertThrows(AccountTypeException.class, () -> {
            new Account(
                    123456L,
                    new BigDecimal("1000.00"),
                    null,
                    "John Doe",
                    "12345678901",
                    "john.doe@example.com",
                    1L
            );
        });
        assertEquals("AccountType cannot be null", exception.getMessage());
    }



    @Test
    @DisplayName("Test that validates CPF according to the account type")
    void shouldValidateAccountCpfAndCnpjWithSuccess(){
        String resultCpf = isValidCpfCnpj(validCpf, naturalPerson);
        String resultCnpj = isValidCpfCnpj(validCnpj, legalPerson);

        assertNotNull(resultCpf);
        assertEquals(11, resultCpf.length());
        assertEquals("12345678910", resultCpf);

        assertNotNull(resultCnpj);
        assertEquals(14, resultCnpj.length());
        assertEquals("12345678910001", resultCnpj);
    }

    @Test
    @DisplayName("The test must return an exception because the cpf does not contain 11 digits and cnpj not contains 14 digits")
    void shouldThrowExceptionWhenTryValidatesCpfCnpj(){
        assertThrows(InvalidCpfException.class, () -> {
            isValidCpfCnpj(invalidCpf, naturalPerson);
        }, "Expected InvalidCpfException for CPF with less than 11 digits");

        assertThrows(InvalidCnpjException.class, () -> {
            isValidCpfCnpj(invalidCnpj, legalPerson);
        }, "Expected InvalidCnpjException for CNPJ with less than 14 digits");

    }

    @Test
    @DisplayName("The test's validate the email with success")
    void shouldValidateAccountEmailWithSuccess(){
        // Valid emails
        assertEquals("test@example.com", isValidEmail("test@example.com"));
        assertEquals("user.name+tag+sorting@example.com", isValidEmail("user.name+tag+sorting@example.com"));
        assertEquals("user_name@example.co.uk", isValidEmail("user_name@example.co.uk"));

        // Invalid emails
        assertThrows(InvalidEmailException.class, () -> isValidEmail("plainaddress"), "Expected invalid email format without @");
        assertThrows(InvalidEmailException.class, () -> isValidEmail("@missingusername.com"), "Expected invalid email format missing username");
        assertThrows(InvalidEmailException.class, () -> isValidEmail("username@.com"), "Expected invalid email format missing domain");
        assertThrows(InvalidEmailException.class, () -> isValidEmail("username@domain..com"), "Expected invalid email format with double dots in domain");
        assertThrows(InvalidEmailException.class, () -> isValidEmail("username@domain.c"), "Expected invalid email format with short top-level domain");
        assertThrows(InvalidEmailException.class, () -> isValidEmail("username@domain.superlongtld"), "Expected invalid email format with long top-level domain");
        assertThrows(InvalidEmailException.class, () -> isValidEmail(""), "Expected invalid email format for empty value");
        assertThrows(InvalidEmailException.class, () -> isValidEmail(null), "Expected invalid email format for null value");
    }

    @Test
    void testIsValidAccontBalanceWithNonNullBalance() {
        BigDecimal balance = new BigDecimal("100.00");
        BigDecimal result = isValidAccontBalance(balance);
        assertEquals(balance, result, "The method should return the same balance when it's non-null");
    }

    @Test
    void testIsValidAccontBalanceWithNullBalance() {
        BigDecimal result = isValidAccontBalance(null);
        assertEquals(BigDecimal.ZERO, result, "The method should return BigDecimal.ZERO when the balance is null");
    }

    protected String isValidCpfCnpj(String cpfCnpj, AccountType accountType){
        switch (accountType) {
            case NATURAL_PERSON:
                if (cpfCnpj.matches("\\d{11}")) break;
                throw new InvalidCpfException("the CPF not contains 11 digits");
            case LEGAL_PERSON:
                if (cpfCnpj.matches("\\d{14}")) break;
                throw new InvalidCnpjException("the CNPJ not contains 14 digits");
        }

        return cpfCnpj;
    }

    protected static BigDecimal isValidAccontBalance(BigDecimal accountBalance) {
        try{
            return  accountBalance != null ? accountBalance : BigDecimal.ZERO;
        }catch (NullPointerException e){
            return BigDecimal.ZERO;
        }
    }

    protected static String isValidEmail(String email) {
        try{
            String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
            String domain = email.substring(email.indexOf('@') + 1);

            boolean emailIsEmpty = email.equals("");
            boolean emailPatternIsInvalid = !EMAIL_PATTERN.matcher(email).matches();
            boolean emailContainsDoubleDots = domain.contains("..");

            if (emailIsEmpty || emailPatternIsInvalid || emailContainsDoubleDots){
                throw new InvalidEmailException("The formatting of the email is not correct, please check");
            }
        }catch (NullPointerException exception){
            throw new InvalidEmailException("The email is null");
        }

        return email;
    }

}