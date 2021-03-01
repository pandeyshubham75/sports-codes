package SportsCodes;

import java.util.HashMap;
import java.util.Map;

public class FreqStack {
    public static void main(String[] args) {
        FreqStack st = new FreqStack();
        st.push(5);
        st.push(7);
        st.push(5);
        st.push(7);
        st.push(4);
        st.push(5);
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.pop());
    }

    /*
     *
     */

    Map<Integer, Integer> frequencyMap;
    Map<Integer, FreqStackNode> dataMap;
    int maxFrequency;

    public FreqStack() {
        frequencyMap = new HashMap<>();
        dataMap = new HashMap<>();
        maxFrequency = 0;
    }

    public void push(int x) {
        int frequency = frequencyMap.containsKey(x) ? frequencyMap.get(x) : 0;
        frequencyMap.put(x, ++frequency);
        FreqStackNode currentHead = dataMap.containsKey(frequency) ? dataMap.get(frequency) : null;
        dataMap.put(frequency, new FreqStackNode(x, currentHead));
        if (frequency > maxFrequency) maxFrequency = frequency;
    }

    public int pop() {
        FreqStackNode head = dataMap.get(maxFrequency);
        if (head.next == null) {
            dataMap.put(maxFrequency--, null);
        } else {
            dataMap.put(maxFrequency, head.next);
        }
        frequencyMap.put(head.val, frequencyMap.get(head.val)-1);
        return head.val;
    }
}

class FreqStackNode {
    int val;
    FreqStackNode next;

    FreqStackNode(int val, FreqStackNode next) {
        this.val = val;
        this.next = next;
    }
}