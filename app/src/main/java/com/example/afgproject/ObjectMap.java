package com.example.afgproject;

import java.util.ArrayList;

/**
 * Custom map in which given object values are mapped out to string key values
 * Operates on a similar basis to a Hashmap, but has more customization
 */
public class ObjectMap {
    ArrayList<String> keys;
    ArrayList<Object> values;

    /**
     * Creates ObjectMap class and assigns key and value data
     * @param keys - Array of String values that serve as the ObjectMap keys
     * @param values - Array of objects whose index corresponds to a key value
     */
    public ObjectMap(String[] keys, Object[] values) {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        for(int i = 0; i<keys.length; i++){
            this.keys.add(keys[i]);
            this.values.add(values[i]);
        }
    }

    /**
     * Get value for given key
     * @param key - Key for which the corresponding value is return
     * @return value object that corresponds to the given key
     */
    public Object getValue(String key){
        return values.get(keys.indexOf(key));
    }

    public int getIndex(String key){
        return keys.indexOf(key);
    }



    public void changePair(String key, Object value){
        values.set(keys.indexOf(key), value);
    }
    /*Unused functionality
    public void removePair(String key){
        values.remove(keys.remove(key));
    }
    public void addPair(String key, Object value){
        keys.add(key);
        values.add(value);
    }*/
}
