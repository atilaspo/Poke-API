import java.util.Comparator;

public class BubbleSortImple<T> implements Sorter<T>{

    @Override
    public void sort(T[] arr, Comparator<T> c) {
            bubbleSort(arr, c);
    }

    private static<T> void bubbleSort(T[] arr, Comparator<T> c){
        for(int i = arr.length -1; i> 0; i--){
            for(int j = 0; j < i; j++){
                if(c.compare(arr[j], arr[j+1]) > 0){
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    }
                }
            }
        }
}

