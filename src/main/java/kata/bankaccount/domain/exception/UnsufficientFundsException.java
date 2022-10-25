package kata.bankaccount.domain.exception;

public class UnsufficientFundsException extends IllegalStateException{
    public UnsufficientFundsException(String message){
        super(message);
    }
}
