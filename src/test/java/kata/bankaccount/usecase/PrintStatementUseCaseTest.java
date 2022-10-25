package kata.bankaccount.usecase;

import kata.bankaccount.domain.BankAccountNumber;
import kata.bankaccount.domain.CheckingAccount;
import kata.bankaccount.domain.repository.AccountRepository;
import kata.bankaccount.infrastructure.adapter.ConsolePrinterAdapter;
import kata.bankaccount.infrastructure.adapter.InMemoryAccountRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static java.math.BigDecimal.valueOf;
import static kata.bankaccount.domain.Amount.amountOf;
import static kata.bankaccount.domain.Statement.emptyStatement;
import static kata.bankaccount.utils.DateProvider.dateFrom;
import static org.junit.jupiter.api.Assertions.*;

class PrintStatementUseCaseTest {

    public static final String IBAN = "FR1234567891011213";
    public static final int INITIAL_AMOUNT = 100;
    private PrintStatementUseCase printStatementUseCase;
    private CheckingAccount account ;

    private AccountRepository accountRepository;

    @BeforeEach
    public void initUseCase(){
        accountRepository= new InMemoryAccountRepositoryAdapter();
        account =new CheckingAccount(new BankAccountNumber(IBAN), amountOf(valueOf(INITIAL_AMOUNT)), emptyStatement() );
        accountRepository.save(account);
        printStatementUseCase = new PrintStatementUseCase(accountRepository, new ConsolePrinterAdapter());
    }

    @Test
    void printStatement() throws ParseException {
       var printableAccount = account.deposit(amountOf(valueOf(100)), dateFrom("01/01/2022"))
                .withdraw(amountOf(valueOf(200)),dateFrom("02/02/2022"))
                .deposit(amountOf(valueOf(300)),dateFrom("03/03/2022"))
                .withdraw(amountOf(valueOf(100)),dateFrom("04/04/2022"))
                .deposit(amountOf(valueOf(100)),dateFrom("05/05/2022"));

        accountRepository.save(printableAccount);

        printStatementUseCase.printStatement(IBAN);
    }
}
