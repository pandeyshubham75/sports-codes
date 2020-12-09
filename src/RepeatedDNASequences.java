import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequences {
    public static void main(String[] args) {
        RepeatedDNASequences r = new RepeatedDNASequences();
        System.out.println(r.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(r.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> requiredList = new ArrayList<>();
        Map<String, Boolean> occurenceRepeatMap = new HashMap<>();
        if (s.length() <= 10) return  requiredList;
        for (int i = 10; i <= s.length(); i++) {
            String thisSequence = s.substring(i-10, i);
            if (occurenceRepeatMap.get(thisSequence) == null) {
                occurenceRepeatMap.put(thisSequence, false);
            } else if (!occurenceRepeatMap.get(thisSequence)) {
                occurenceRepeatMap.put(thisSequence, true);
                requiredList.add(thisSequence);
            }
        }
        return requiredList;
    }
}