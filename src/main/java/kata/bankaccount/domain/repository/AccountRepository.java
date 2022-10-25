package kata.bankaccount.domain.repository;

import kata.bankaccount.domain.CheckingAccount;

public interface AccountRepository {

    CheckingAccount findCheckingAccountByIban(String iban);

    CheckingAccount save(CheckingAccount account);
}
