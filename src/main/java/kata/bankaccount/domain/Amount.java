package kata.bankaccount.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {

    private final BigDecimal value;

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public Amount add(Amount toAdd){
      return  amountOf(this.value.add(toAdd.value));
    }


    public Amount minus(Amount valueToRetreive){
      return   amountOf(this.value.subtract( valueToRetreive.value));
    }

    public boolean isGreaterThan(Amount amount){
        return value.compareTo(amount.value) > 0;
   }

    public static Amount amountOf(BigDecimal i) {
        return new Amount(i);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value.equals(amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
