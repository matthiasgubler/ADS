package ch.zhaw.ads.prakt10;

import ch.zhaw.ads.CommandExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class SortServer implements CommandExecutor {
    private static final int RANDOM_BOUND = 1000000;
    private static final int RANDOM_SEED = 5000;

    private Map<String, SortStrategy> sortStrategies;
    private Random random;

    public SortServer() {
        sortStrategies = new HashMap<>();
        sortStrategies.put("BUBBLE", new BubblesortStrategy());
        sortStrategies.put("INSERTION", new InsertionSortStrategy());
        sortStrategies.put("SELECTION", new SelectionSortStrategy());
        random = new Random();
        random.setSeed(RANDOM_SEED);
    }

    @Override
    public String execute(String command) throws Exception {
        command = "INSERTION 50000";
        if (command == null || !command.contains(" ")) {
            throw new IllegalArgumentException("input invalid");
        }
        SortStrategy strategy = sortStrategies.get(command.split(" ")[0]);
        int[] ints = createArray(command);
        startTiming(ints, strategy);
        strategy.sort(ints);
        return Boolean.valueOf(checkSorted(ints)).toString();
    }

    private void startTiming(final int[] ints, SortStrategy strategy) {
        long end, start = System.currentTimeMillis();
        int count = 0;
        int[] arrayCopy;
        do {
            arrayCopy = new int[ints.length];
            System.arraycopy(ints, 0, arrayCopy, 0, arrayCopy.length);
            strategy.sort(arrayCopy);
            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        System.out.println("time="+(double)(end-start)/count);
    }

    private int[] createArray(String command) {
        return IntStream.range(0, Integer.parseInt(command.split(" ")[1])).map(index -> random.nextInt(RANDOM_BOUND)).toArray();
    }

    private boolean checkSorted(int[] ints) {
        for (int x=0; x<ints.length-1; x++) {
            if (ints[x] > ints[x+1]) {
                return false;
            }
        }
        return true;
    }
}