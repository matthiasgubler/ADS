package ch.zhaw.ads.prakt10;

public class BubblesortStrategy implements SortStrategy {
    @Override
    public void sort(int[] values) {
        for (int k = values.length-1; k > 0; k--){
            boolean noSwap = true;
            for (int i = 0; i < k; i++){
                if ( values[i] > values[i+1]) {
                    swap (values, i, i+1);
                    noSwap = false;
                }
            }
            if (noSwap) break;
        }
    }

    private void swap(int[] k, int i, int j){
        int h = k[i];k[i] = k[j];k[j] = h;
    }
}
