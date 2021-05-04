package ee.taltech.iti0202.generics;

import java.util.*;
import java.util.stream.Collectors;

public class Hash2Map<K, M, V> {

    HashMap<K, HashMap<M, V>> hashMapHashMap = new HashMap<>();

    void put(K x, M y, V z) {
        hashMapHashMap.put(x, hashMapHashMap.getOrDefault(x, new HashMap<M, V>()));
        hashMapHashMap.get(x).put(y, z);
    }

    Set<K> getKeys() {
        return hashMapHashMap.keySet();
    }

    Set<M> getKeys(K key) {
        if (!hashMapHashMap.containsKey(key)) {
            return new HashSet<>();
        }
        return hashMapHashMap.get(key).keySet();
    }

    V get(K x, M y) {
        return hashMapHashMap.get(x).get(y);
    }

    HashMap<M, V> getMap(K x) {
        return hashMapHashMap.get(x);
    }

    List<V> getAllValues() {
        List<V> vList = new LinkedList<>();
        for (K k: hashMapHashMap.keySet().stream().collect(Collectors.toList())) {
            for (V v : hashMapHashMap.get(k).values().stream().collect(Collectors.toList())) {
                vList.add(v);
            }
        }
        return vList;
    }

    public static void main(String[] args) {
        Hash2Map<String, String, Integer> nameCountByCountry = new Hash2Map<>();
        System.out.println(nameCountByCountry.getKeys());  // []
        nameCountByCountry.put("Estonia", "Paul", 12);
        nameCountByCountry.put("Estonia", "Mari", 17);
        nameCountByCountry.put("Finland", "Matti", 16);
        nameCountByCountry.put("Finland", "Itti", 13);
        nameCountByCountry.put("Latvia", "Reins", 10);
        nameCountByCountry.put("Latvia", "Kaira", 17);

        System.out.println(nameCountByCountry.getKeys()); // [Latvia, Finland, Estonia]
        System.out.println(nameCountByCountry.getKeys("Estonia")); // [Paul, Mari]
        System.out.println(nameCountByCountry.get("Finland", "Matti"));  //16
        System.out.println(nameCountByCountry.get("Finland", "Reins"));  //null
        System.out.println(nameCountByCountry.getKeys("Sweden"));  // []
        System.out.println(nameCountByCountry.getAllValues());  // [10, 17, 13, 16, 12, 17]
    }
}
