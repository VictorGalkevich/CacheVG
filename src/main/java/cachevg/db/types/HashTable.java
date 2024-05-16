package cachevg.db.types;

import java.time.OffsetDateTime;
import java.util.HashMap;

public class HashTable extends AbstractValue<HashMap<String, String>> {

    public HashTable(HashMap<String, String> value, OffsetDateTime ttl, OffsetDateTime createdAt) {
        super(value, ttl, createdAt);
    }


}
