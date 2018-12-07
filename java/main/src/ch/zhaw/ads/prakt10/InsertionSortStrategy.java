package ch.zhaw.ads.prakt10;

public class InsertionSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] values) {
        for (int k = 1; k < values.length; k++)
            if (values[k] < values[k-1]){
                int x = values[k];
                int i;
                for (i = k; ((i > 0) && (values[i-1] > x));i--)
                    values[i] = values[i-1];
                values[i] = x;
            }
    }

}
