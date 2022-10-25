package kata.bankaccount.domain;

import kata.bankaccount.domain.exception.UnsufficientFundsException;

import java.util.Date;
import java.util.Objects;

public class CheckingAccount {

    private final BankAccountNumber accountId;
    private final Amount balance ;
    private final Statement statement;

    public CheckingAccount(BankAccountNumber accountId, Amount amount, Statement statement) {
        this.accountId = accountId;
        this.balance = amount;
        this.statement = statement;
    }

    public CheckingAccount deposit( Amount amount, Date date) {
        var balanceAfterDeposit =balance.add(amount);
        var newStatement = statement.pushNewTransaction(new Transaction(date, amount, TransactionType.DEPOSIT, balanceAfterDeposit));
       return new CheckingAccount(accountId, balanceAfterDeposit, newStatement);
    }

    public CheckingAccount withdraw( Amount amount, Date date) {
        if(amount.isGreaterThan(balance)) {
            throw  new UnsufficientFundsException("You cannot withdraw this amount, Unsufficent funds.");
        }
        var balanceAfterWithdrawal =balance.minus(amount);
        var newStatement = statement.pushNewTransaction(new Transaction(date, amount , TransactionType.WITHDRAWAL,balanceAfterWithdrawal));
        return new CheckingAccount(accountId, balanceAfterWithdrawal, newStatement);
    }


    public BankAccountNumber getAccountId() {
        return accountId;
    }

    public Amount getBalance() {
        return balance;
    }

    public Statement getStatement() {
        return statement;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckingAccount that = (CheckingAccount) o;
        return accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
