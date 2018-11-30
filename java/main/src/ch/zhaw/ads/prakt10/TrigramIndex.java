package ch.zhaw.ads.prakt10;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class TrigramIndex {

    private Map<String, List> trigramIndex = new Hashtable();

    private List<String> nameList;

    public TrigramIndex() {

    }

    public void initialize(List<String> strings) {
        trigramIndex.clear();
        nameList = strings;
        for (int i = 0; i < nameList.size(); i++) {
            String string = nameList.get(i);
            createTrigram(string, i);
        }
    }

    private void createTrigram(String string, int index) {
        for (String trigramElement : createTrigram(string)) {
            if (trigramIndex.containsKey(trigramElement)) {
                trigramIndex.get(trigramElement).add(index);
            } else {
                List<Integer> counterList = new ArrayList();
                counterList.add(index);
                trigramIndex.put(trigramElement, counterList);
            }
        }
    }

    private List<String> createTrigram(String string) {
        List<String> trigramList = new ArrayList();
        for (int i = 0; i + 3 < string.length(); i++) {
            trigramList.add(string.substring(i, i + 3));
        }
        return trigramList;
    }

    public String fuzzySearch(String searchString) {
        List<String> trigramList = createTrigram(searchString);
        int[] possibleFoundCountList = new int[nameList.size()];

        for (String trigram : trigramList) {
            List<Integer> trigramIndices = trigramIndex.get(trigram);
            if (trigramIndices != null) {
                for (Integer index : trigramIndices) {
                    possibleFoundCountList[index]++;
                }
            }
        }

        int index = getPossibleMatchIndex(possibleFoundCountList);
        return nameList.get(index);
    }

    private int getPossibleMatchIndex(int[] possibleFoundCountList) {
        int currentIndex = -1;
        int max = -1;
        for (int counter = 1; counter < possibleFoundCountList.length; counter++) {
            if (possibleFoundCountList[counter] > max) {
                currentIndex = counter;
                max = possibleFoundCountList[counter];
            }
        }
        return currentIndex;
    }
}
