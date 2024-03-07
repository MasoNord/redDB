package org.masonord.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class InMemoryDB implements Iterable<String>{
    private InMemoryDB() {}

    public static final InMemoryDB INSTANCE = new InMemoryDB();
    private Map<String, Object> data = new HashMap<>();

    public Set<String> getKeySet() {
        return data.keySet();
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public <T> void put(String key, T value) {
        data.put(key, value);
    }

    public <T> T get (String key, Class<T> type) {
        return type.cast(data.get(key));
    }

    public void remove(String key) {
        data.remove(key);
    }

    public void clear() {
        data = new HashMap<>();
    }


    @Override
    public Iterator<String> iterator() {
        return data.keySet().iterator();
    }
}
