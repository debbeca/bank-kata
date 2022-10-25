package kata.bankaccount.domain.service;

import kata.bankaccount.domain.Statement;

public interface PrinterService {

    void printHeader();

    void printLine( String line);
}
