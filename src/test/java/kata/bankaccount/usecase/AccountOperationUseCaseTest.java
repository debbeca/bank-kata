package kata.bankaccount.usecase;

import kata.bankaccount.domain.BankAccountNumber;
import kata.bankaccount.domain.CheckingAccount;
import kata.bankaccount.domain.Statement;
import kata.bankaccount.domain.exception.UnknownAccountException;
import kata.bankaccount.domain.exception.UnsufficientFundsException;
import kata.bankaccount.infrastructure.adapter.InMemoryAccountRepositoryAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.valueOf;
import static kata.bankaccount.domain.Amount.amountOf;
import static kata.bankaccount.domain.Statement.emptyStatement;
import static org.junit.jupiter.api.Assertions.*;

class AccountOperationUseCaseTest {

    public static final String IBAN = "FR1234567891011213";
    public static final String UNKNOWN_IBAN = "FR66666666666";
    public static final int INITIAL_AMOUNT = 100;
    private AccountOperationUseCase accountOperationUseCase;
    private CheckingAccount account ;

    @BeforeEach
    public void initUseCase(){
        var inMemoryRepository = new InMemoryAccountRepositoryAdapter();
        account =new CheckingAccount(new BankAccountNumber(IBAN), amountOf(valueOf(INITIAL_AMOUNT)), emptyStatement() );
        inMemoryRepository.save(account);
        accountOperationUseCase = new AccountOperationUseCase(inMemoryRepository);
    }

    @Test
    void shouldDepositAHundredOnAccountUsingItsIban() {
       var accountAfterDeposit = accountOperationUseCase.deposit(IBAN, valueOf(100));
        Assertions.assertEquals( valueOf(200), accountAfterDeposit.getBalance().getValue());
    }

    @Test
    void shouldWithdrawAHundredFromAccountUsingItsIban() {
        var accountAfterWithdrawal = accountOperationUseCase.withdraw(IBAN, valueOf(100));
        Assertions.assertEquals( valueOf(0), accountAfterWithdrawal.getBalance().getValue());
    }

    @Test
    public void shouldThrowUnknownAccountExceptionWhenWhenDepositOnUnkownIban(){
        Assertions.assertThrows(UnknownAccountException.class, ()->accountOperationUseCase.deposit(UNKNOWN_IBAN, valueOf(100)));
    }
}
