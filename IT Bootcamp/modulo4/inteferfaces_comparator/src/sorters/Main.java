import java.util.*;

public class Main {


    public static void main(String[] args) {
        Integer arr[] = getMyBigFatArray(5);

        System.out.println(Arrays.toString(arr));

        QuickSortImple<Integer> sorter = new QuickSortImple<>();
        sorter.sort(arr,(a, b) -> a - b);
        System.out.println(Arrays.toString(arr));

        BubbleSortImple<Integer> sorter2 = new BubbleSortImple();
        sorter2.sort(arr, (a, b) -> a - b);
        System.out.println(Arrays.toString(arr));

        HeapSortSorterImple<Integer> sorter3 = new HeapSortSorterImple();
        sorter3.sort(arr, (a, b) -> a -b);
        System.out.println(Arrays.toString(arr));
    }

    private static Integer[] getMyBigFatArray(int numberOfItems){
        Random randomGen = new Random();
        Integer[] arr = new Integer[numberOfItems];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomGen.nextInt(1000000);
        }
        return arr;
    }

}
