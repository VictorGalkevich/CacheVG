package cachevg.db.types;

import java.time.OffsetDateTime;

public abstract class AbstractValue<T> {
    protected T value;
    protected OffsetDateTime ttl;
    protected OffsetDateTime createdAt;

    public AbstractValue(T value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        this.value = value;
        this.ttl = ttl;
        this.createdAt = createdAt;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public OffsetDateTime getTtl() {
        return ttl;
    }

    public void setTtl(OffsetDateTime ttl) {
        this.ttl = ttl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
