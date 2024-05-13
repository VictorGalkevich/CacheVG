package cachevg.types;

import java.time.OffsetDateTime;

public class IntegerValue extends DefaultString implements NumericOperations {
    private Long int64tValue;

    public IntegerValue(String value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        super(value, ttl, createdAt);
        this.int64tValue = Long.parseLong(value);
    }

    @Override
    public Number incrementByValue(Number increment) {
        this.int64tValue += (Long) increment;
        this.value = String.valueOf(int64tValue);
        return int64tValue;
    }

    @Override
    public Number decrementByValue(Number decrement) {
        this.int64tValue -= (Long) decrement;
        this.value = String.valueOf(int64tValue);
        return int64tValue;
    }

    public boolean isPowerOfTwo() {
        return (this.int64tValue & (this.int64tValue - 1)) == 0;
    }

    public boolean isEven() {
        return this.int64tValue % 2 == 0;
    }

    public boolean isOdd() {
        return this.int64tValue % 2 != 0;
    }
}
