import java.util.Comparator;

public class QuickSortImple<T> implements Sorter<T>{

    @Override
    public void sort(T[] arr, Comparator<T> c) {
        quickSort(arr, c, 0, arr.length - 1 );
    }

    private void quickSort(T arr[], Comparator<T> c, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, c, begin, end);

            quickSort(arr, c, begin, partitionIndex-1);
            quickSort(arr, c, partitionIndex+1, end);
        }
    }

    private int partition(T arr[], Comparator<T> c, int begin, int end) {
        T pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (c.compare(arr[j], pivot) <= 0) {
                i++;

                T swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        T swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }
}
