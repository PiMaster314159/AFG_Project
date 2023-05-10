package com.example.afgproject;

import java.util.ArrayList;

public class ObjectMap {
    ArrayList<String> keys = new ArrayList<String>();
    ArrayList<Object> values = new ArrayList<Object>();
    public ObjectMap(String[] keys, Object[] values) {
        for(int i = 0; i<keys.length; i++){
            this.keys.add(keys[i]);
            this.values.add(values[i]);
        }
    }

    public Object getValue(String key){
        return values.get(keys.indexOf(key));
    }

    public void addPair(String key, Object value){
        keys.add(key);
        values.add(value);
    }

    public void changePair(String key, Object value){
        values.set(keys.indexOf(key), value);
    }

    public void removePair(String key){
        values.remove(keys.remove(key));
    }
}
