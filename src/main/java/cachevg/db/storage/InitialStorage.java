package cachevg.db.storage;

import java.util.HashMap;
import java.util.List;

public class InitialStorage {
    private static final HashMap<String, String> STORAGE = new HashMap<>();
    private static final InitialStorage INSTANCE = new InitialStorage();

    public String put(String key, String value) {
        STORAGE.put(key, value);
        return STORAGE.get(key);
    }

    public String remove(String key) {
        return STORAGE.remove(key);
    }

    public String get(String key) {
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
