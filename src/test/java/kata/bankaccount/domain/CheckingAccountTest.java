package kata.bankaccount.domain;

import kata.bankaccount.domain.exception.UnsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.util.Date;

import static java.math.BigDecimal.valueOf;
import static kata.bankaccount.domain.Amount.amountOf;
import static kata.bankaccount.utils.DateProvider.dateFrom;

class CheckingAccountTest {

    private CheckingAccount account;

    @BeforeEach
    public void initAccount(){
        account = new CheckingAccount(new BankAccountNumber("iban"), amountOf(valueOf(100)), Statement.emptyStatement());
    }


    @Test
    public void shouldReturnAHundredAfterItsDeposit(){
       var accountAfterDeposit = account.deposit(amountOf(valueOf(100)), new Date());
       Assertions.assertEquals( valueOf(200), accountAfterDeposit.getBalance().getValue());
    }

    @Test
    public void shouldReturnZeroAfterItsWithdrawalOfRemainingHundred(){
       var accountAfterWithDrawal = account.withdraw(amountOf(valueOf(100)), new Date());
       Assertions.assertEquals( valueOf(0), accountAfterWithDrawal.getBalance().getValue());
    }

    @Test
    public void shouldThrowUnsufficientFundsExceptionWhenWithrawalOfAThousand(){
        Assertions.assertThrows(UnsufficientFundsException.class, ()->account.withdraw(amountOf(valueOf(1000)), new Date()));
    }

    @Test
    public void shouldReturnAHundredAfterDepositAndWithrdrawalOfAHundred(){
        var accountAfterOperation = account.deposit(amountOf(valueOf(100)), new Date())
                                                            .withdraw(amountOf(valueOf(100)), new Date());
        Assertions.assertEquals( valueOf(100),accountAfterOperation.getBalance().getValue());
    }


    @Test
    public void shouldPushANewTransactionToStatementWhenWithDrawal() throws ParseException {
        var accountAfterWithDrawal = account.withdraw(amountOf(valueOf(100)), dateFrom("01/01/2022"));

        Assertions.assertTrue(accountAfterWithDrawal.getStatement().getLastTransaction().isPresent());
        Assertions.assertEquals(accountAfterWithDrawal.getBalance(),accountAfterWithDrawal.getStatement().getLastTransaction().map(Transaction::getNewBalance).get());
        Assertions.assertEquals(TransactionType.WITHDRAWAL,accountAfterWithDrawal.getStatement().getLastTransaction().map(Transaction::getType).get());
        Assertions.assertEquals(amountOf(valueOf(100)),accountAfterWithDrawal.getStatement().getLastTransaction().map(Transaction::getAmount).get());
        Assertions.assertEquals(dateFrom("01/01/2022"),accountAfterWithDrawal.getStatement().getLastTransaction().map(Transaction::getDate).get());
    }

    @Test
    public void shouldPushANewTransactionToStatementWhenDeposit(){
        var accountAfterDeposit = account.deposit(amountOf(valueOf(100)), new Date());
        Assertions.assertTrue(accountAfterDeposit.getStatement().getLastTransaction().isPresent());
        Assertions.assertEquals( accountAfterDeposit.getBalance(),accountAfterDeposit.getStatement().getLastTransaction().map(Transaction::getNewBalance).get());
        Assertions.assertEquals(TransactionType.DEPOSIT,accountAfterDeposit.getStatement().getLastTransaction().map(Transaction::getType).get() );
        Assertions.assertEquals(amountOf(valueOf(100)),accountAfterDeposit.getStatement().getLastTransaction().map(Transaction::getAmount).get());
    }



}
