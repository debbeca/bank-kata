package kata.bankaccount.domain;

import kata.bankaccount.domain.service.PrinterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Statement {

    private final List<Transaction> transactionList;

    public Statement(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public static Statement emptyStatement() {
        return new Statement(new ArrayList<>());
    }

    public Statement pushNewTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
        return this;
    }

    public Optional<Transaction> getLastTransaction() {
        return  this.transactionList.stream().reduce((first,second) -> second);
    }

    public void print(PrinterService printer) {
        printer.printHeader();
        for (Transaction transaction: this.transactionList) {
            printer.printLine(transaction.toPrint());
        }
    }
}
