package cachevg.types;

import java.time.OffsetDateTime;

public abstract class AbstractValue<T> {
    T value;
    OffsetDateTime ttl;
    OffsetDateTime createdAt;

    public AbstractValue(T value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        this.value = value;
        this.ttl = ttl;
        this.createdAt = createdAt;
    }

    
}
