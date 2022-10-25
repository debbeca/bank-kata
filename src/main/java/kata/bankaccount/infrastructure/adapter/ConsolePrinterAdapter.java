package kata.bankaccount.infrastructure.adapter;

import kata.bankaccount.domain.Statement;
import kata.bankaccount.domain.Transaction;
import kata.bankaccount.domain.service.PrinterService;

public class ConsolePrinterAdapter implements PrinterService {

    private static final String STATEMENT_HEADER = "Date                       | Credit  |   Debit    |  balance ";

    @Override
    public void printHeader() {
        System.out.println(STATEMENT_HEADER);
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }
}
