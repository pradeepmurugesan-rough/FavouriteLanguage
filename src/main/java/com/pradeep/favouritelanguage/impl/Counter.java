package com.pradeep.favouritelanguage.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Pmuruge on 12/10/2015.
 */
public class Counter  {
    private Integer max;
    private Integer min;
    private Hashtable<String, Integer> countTable;
    private List<String> items;
    private static final Logger logger = LoggerFactory.getLogger(Counter.class);

    public  Counter(List<String> items) throws Exception {
        this.max = Integer.MIN_VALUE;
        this.min = Integer.MAX_VALUE;
        this.countTable = new Hashtable<String, Integer>();
        if(items == null) {
            throw new Exception("Items cannot be null");
        }
        this.items = items;
        this.countItems();
    }

    private void add(String key, Integer value) {
        this.setMax(value);
        this.setMin(value);
        this.countTable.put(key, value);
    }

    private void setMax(Integer value) {
        this.max = Math.max(value, max);
    }

    private void setMin(Integer value) {
        this.min = Math.min(value, min);
    }

    private void countItems() {
        for(String item : this.items) {
            incrementCount(item);
        }
        logger.debug("Counting completed and count is " + this.countTable);
    }

    private void incrementCount(String item) {
        if(this.countTable.containsKey(item)) {
            this.add(item, countTable.get(item) + 1);
        } else {
            this.add(item, 1);
        }
    }
    public List<String> getMaxKeys() {
        List<String> maxKeys = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : this.countTable.entrySet()) {
            if(entry.getValue() == this.max) {
                maxKeys.add(entry.getKey());
            }
        }
        logger.info("returning the keys with max occurence" + maxKeys);
        return maxKeys;
    }
}
