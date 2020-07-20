import java.util.*;

public class ArrayMap<K, V> extends AbstractMap<K, V> {
    private Collection<ArrayMapEntry<K, V>> entries;

    public ArrayMap() {
        entries = new ArrayList<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();

        entrySet.addAll(entries);

        return entrySet;
    }

    @Override
    public V put(K key, V value) {
        for (ArrayMapEntry<K, V> entry : entries) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return value;
            }
        }

        entries.add(new ArrayMapEntry<K, V>(key, value));
        return value;
    }

    @Override
    public boolean containsKey(Object key) {
        for (ArrayMapEntry<K, V> entry : entries) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        for (ArrayMapEntry<K, V> entry : entries) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public int size() {
        return entries.size();
    }

    private static class ArrayMapEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public ArrayMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }

        @Override
        public String toString() {
            return "[" + getKey() + ", " + getValue() + "]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null) {
                return false;
            }

            if (o.getClass() != getClass()) {
                return false;
            }

            ArrayMapEntry<K, V> otherObject = (ArrayMapEntry<K, V>) o;

            if (!key.equals(otherObject.key)) {
                return false;
            }

            if (!value.equals(otherObject.value)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = 31 + key.hashCode();
            return 31 * result + value.hashCode();
        }
    }
}
