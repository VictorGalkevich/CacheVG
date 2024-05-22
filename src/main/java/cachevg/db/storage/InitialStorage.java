package cachevg.db.storage;

import cachevg.db.types.AbstractValue;
import cachevg.db.types.ConnectedList;
import cachevg.db.types.DefaultString;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InitialStorage {
    private static final HashMap<String, AbstractValue<?>> STORAGE = new HashMap<>();
    private static final InitialStorage INSTANCE = new InitialStorage();

    public String put(String key, String value) {
        STORAGE.put(key, new DefaultString(value, OffsetDateTime.MAX, OffsetDateTime.now()));
        return STORAGE.get(key).getValue().toString();
    }

    public String putList(String key, String initial) {
        LinkedList<String> objects = new LinkedList<>();
        objects.add(initial);
        STORAGE.put(key, new ConnectedList(objects, OffsetDateTime.MAX, OffsetDateTime.now()));
        return "1";
    }

    public String remove(String key) {
        return STORAGE.remove(key).getValue().toString();
    }

    public String get(String key) {
        return STORAGE.get(key).getValue().toString();
    }

    public AbstractValue<?> getObj(String key) {
        return STORAGE.get(key);
    }

    public void clear() {
        STORAGE.clear();
    }

    public List<String> keys() {
        return STORAGE.keySet().stream().toList();
    }

    public static InitialStorage instance() {
        return INSTANCE;
    }
}
