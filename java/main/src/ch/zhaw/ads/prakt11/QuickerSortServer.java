package ch.zhaw.ads.prakt11;

import ch.zhaw.ads.CommandExecutor;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

public class QuickerSortServer implements CommandExecutor {
    private static final int RANDOM_SEED = 5000;
    private static final int RANDOM_BOUND = 1000000;

    private final Random random;
    private final ForkJoinPool pool;


    public QuickerSortServer() {
        random = new Random();
        random.setSeed(RANDOM_SEED);
        this.pool = new ForkJoinPool(java.lang.Runtime.getRuntime().availableProcessors()*2);
    }

    @Override
    public String execute(String command) throws Exception {
        int[] array = createArray(command);
        startTiming(array);
        quickSortParallel(array);
        pool.shutdown();
        return "";
    }


    void quickSort(int arr[], int left, int right, int schwelle) {
        if (right - left < schwelle) {
            insertionSort(arr, left, right);
        } else {
            int l = partition(arr, left, right);
            quickSort(arr, left, l - 1, schwelle);
            quickSort(arr, l, right, schwelle);
        }
    }

    int partition ( int arr[], int left, int right){
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    void insertionSort(int[] a, int l, int r) {
        for (int k = l + 1; k < r + 1; k++) {
            if (a[k] < a[k - 1]) {
                int x = a[k];
                int i;
                for (i = k; ((i > 0) && (a[i - 1] > x)); i--) {
                    a[i] = a[i - 1];
                }
                a[i] = x;
            }
        }
    }

    void quickSort(int arr[], int schwelle) {
        quickSort(arr, 0, arr.length - 1, schwelle);
    }

    void quickSortParallel(int arr[]) {
        QuickSortForkJoin quick = new QuickSortForkJoin(arr, 0, arr.length-1);
        pool.invoke(quick);
    }

    private void startTiming(final int[] ints) {
        long end, start = System.currentTimeMillis();
        int count = 0;
        int[] arrayCopy;
        do {
            arrayCopy = new int[ints.length];
            System.arraycopy(ints, 0, arrayCopy, 0, arrayCopy.length);
            //quickSort(arrayCopy, 50);
            quickSortParallel(arrayCopy);
            count++;
            end = System.currentTimeMillis();
        } while (end - start < 1000);
        System.out.println("time="+(double)(end-start)/count);
    }

    private void swap(int[] k, int i, int j){
        int h = k[i];k[i] = k[j];k[j] = h;
    }

    private int[] createArray(String command) {
        return IntStream.range(0, Integer.parseInt(command)).map(index -> random.nextInt(RANDOM_BOUND)).toArray();
    }

    private boolean checkSorted(int[] ints) {
        for (int x=0; x<ints.length-1; x++) {
            if (ints[x] > ints[x+1]) {
                return false;
            }
        }
        return true;
    }

    private class QuickSortForkJoin extends RecursiveAction {
        private int[] data;
        private int left;
        private int right;

        QuickSortForkJoin(int[] data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left < 50) {
                insertionSort(data, left, right);
            } else {
                int l = partition(data, left, right);
                invokeAll(new QuickSortForkJoin(data, left, l-1),
                        new QuickSortForkJoin(data, l, right));
            }
        }
    }
}
