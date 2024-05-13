package cachevg.types;

import java.time.OffsetDateTime;

public class FloatingPoint extends IntegerValue {
    private Double float64tValue;

    public FloatingPoint(String value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        super(value, ttl, createdAt);
        this.float64tValue = Double.parseDouble(value);
    }

    @Override
    public Number incrementByValue(Number increment) {
        this.float64tValue += (Double) increment;
        this.value = String.valueOf(float64tValue);
        return float64tValue;
    }

    @Override
    public Number decrementByValue(Number decrement) {
        this.float64tValue -= (Double) decrement;
        this.value = String.valueOf(float64tValue);
        return float64tValue;
    }
}
