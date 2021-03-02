package SportsCodes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

    public static void main(String[] args) {
/*
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));      // return 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // return -1 (not found)
        System.out.println(lfu.get(3));      // return 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));      // return 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
*/
        LFUCache lfu = new LFUCache(0);
        lfu.put(0,0);
        System.out.println(lfu.get(0));

    }

    /*
     *
     * ["LFUCache","put","put","put","put","get"]
        [[2],[3,1],[2,1],[2,2],[4,4],[2]]
     */
    Map<Integer, Integer> data;
    Map<Integer, Integer> frequencies;
    Map<Integer, LinkedHashSet<Integer>> reverseFrequencyQueue;
    int minFrequency;
    int capacity;
    int size;

    public LFUCache(int capacity) {
        data = new HashMap<>();
        frequencies = new HashMap<>();
        reverseFrequencyQueue = new HashMap<>();
        reverseFrequencyQueue.put(1, new LinkedHashSet<>());
        minFrequency = 0;
        size = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!data.containsKey(key)) return -1;
        int frequency = frequencies.get(key);
        LinkedHashSet<Integer> oldBucket = reverseFrequencyQueue.get(frequency);
        oldBucket.remove(key);
        if (frequency++ == minFrequency && oldBucket.size() == 0) minFrequency++;
        if (!reverseFrequencyQueue.containsKey(frequency))
            reverseFrequencyQueue.put(frequency, new LinkedHashSet<>());
        reverseFrequencyQueue.get(frequency).add(key);
        frequencies.put(key, frequency);
        return data.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (data.containsKey(key)) {
            get(key);
            data.put(key, value);
            return;
        }
        if (size == capacity) {
            evict();
        } else {
            size++;
        }
        minFrequency = 1;
        reverseFrequencyQueue.get(1).add(key);
        frequencies.put(key, 1);
        data.put(key, value);
    }

    private void evict(){
        LinkedHashSet<Integer> minFreqBucket = reverseFrequencyQueue.get(minFrequency);
        int evictedKey = minFreqBucket.iterator().next();
        minFreqBucket.remove(evictedKey);
        data.remove(evictedKey);
        frequencies.remove(evictedKey);
    }
}
