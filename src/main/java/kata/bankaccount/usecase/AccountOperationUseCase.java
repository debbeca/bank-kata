package kata.bankaccount.usecase;

import kata.bankaccount.domain.Amount;
import kata.bankaccount.domain.CheckingAccount;
import kata.bankaccount.domain.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Date;

public class AccountOperationUseCase {

    private final AccountRepository accountRepository;


    public AccountOperationUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public CheckingAccount deposit(String iban, BigDecimal amount){
        var account = accountRepository.findCheckingAccountByIban(iban);
        return  accountRepository.save(account.deposit(Amount.amountOf(amount), new Date()));
    }


    public CheckingAccount withdraw(String iban, BigDecimal amount){
        var account = accountRepository.findCheckingAccountByIban(iban);
        return  accountRepository.save(account.withdraw(Amount.amountOf(amount), new Date()));
    }
}
