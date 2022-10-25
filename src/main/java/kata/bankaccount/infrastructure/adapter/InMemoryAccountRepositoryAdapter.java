package kata.bankaccount.infrastructure.adapter;

import kata.bankaccount.domain.BankAccountNumber;
import kata.bankaccount.domain.CheckingAccount;
import kata.bankaccount.domain.exception.UnknownAccountException;
import kata.bankaccount.domain.repository.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static kata.bankaccount.domain.BankAccountNumber.fromIban;

public class InMemoryAccountRepositoryAdapter implements AccountRepository {

    private final Map<BankAccountNumber, CheckingAccount> accountMap ;


    public InMemoryAccountRepositoryAdapter() {
        this.accountMap = new HashMap<>();
    }

    @Override
    public CheckingAccount findCheckingAccountByIban(String iban) {
        return Optional.ofNullable( accountMap.get( fromIban(iban)))
                .orElseThrow(() ->new UnknownAccountException("We don't have any account with this IBAN."));
    }

    @Override
    public CheckingAccount save(CheckingAccount account) {
         accountMap.put(account.getAccountId(), account);
         return  account;
    }
}
