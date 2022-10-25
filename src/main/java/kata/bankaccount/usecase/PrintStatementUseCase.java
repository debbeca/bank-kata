package kata.bankaccount.usecase;

import kata.bankaccount.domain.repository.AccountRepository;
import kata.bankaccount.domain.service.PrinterService;

public class PrintStatementUseCase {

    private final AccountRepository accountRepository;
    private final PrinterService printerService;


    public PrintStatementUseCase(AccountRepository accountRepository, PrinterService printerService) {
        this.accountRepository = accountRepository;
        this.printerService = printerService;
    }

    public void printStatement(String iban){
        var account =   accountRepository.findCheckingAccountByIban(iban);
        account.getStatement().print(printerService);
    }
}
