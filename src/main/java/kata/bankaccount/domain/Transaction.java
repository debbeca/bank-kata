package kata.bankaccount.domain;

import java.util.Date;

import static kata.bankaccount.utils.DateProvider.printDate;


public class Transaction {

    private final Date date;

    private final Amount newBalance;

    private final Amount amount;

    private final TransactionType type;


    public Date getDate() {
        return date;
    }

    public Amount getNewBalance() {
        return newBalance;
    }

    public Amount getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Transaction(Date date, Amount amount, TransactionType type, Amount newBalance) {
        this.date = date;
        this.newBalance = newBalance;
        this.amount = amount;
        this.type = type;
    }

    public String toPrint() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(printDate(date))
                .append("                 |  ");
        if (this.type == TransactionType.WITHDRAWAL) {
            stringBuilder.append("       |    ")
                    .append(amount)
                    .append("     |       ")
                    .append(newBalance);
            return  stringBuilder.toString();
        }
        stringBuilder.append(amount)
                .append("    |            |      ")
                .append(newBalance)
                .append("   ");

        return stringBuilder.toString();
    }


}
