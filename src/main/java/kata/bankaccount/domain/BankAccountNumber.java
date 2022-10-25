package kata.bankaccount.domain;

import java.util.Objects;

public class BankAccountNumber {


    private final String iban;

    public BankAccountNumber(String iban) {
        Objects.requireNonNull(iban);
        this.iban = iban;
    }

    public static BankAccountNumber fromIban(String iban){
        return new BankAccountNumber(iban);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountNumber that = (BankAccountNumber) o;
        return iban.equals(that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}
