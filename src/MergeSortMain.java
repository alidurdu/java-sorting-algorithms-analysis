import java.io.*;
import java.util.*;

// Problem 3 - Merge Sort
// a)

public class MergeSortMain {

    static int mergeCount = 0; // save merge count

    public static void main(String[] args) throws Exception {

        // unique alcohol values
        Set<Double> alcoholSet = new HashSet<>();

        // read datasets
        readFile("src/winequality-red.csv", alcoholSet);
        readFile("src/winequality-white.csv", alcoholSet);

        // convert to list
        ArrayList<Double> alcoholList = new ArrayList<>(alcoholSet);

        // merge sort
        mergeSort(alcoholList);

        System.out.println("Merge Sort:");
        System.out.println(alcoholList);

        System.out.println("\nNumber of merge operations: " + mergeCount);
    }

    // read CSV
    public static void readFile(String file, Set<Double> set) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {

            String[] values = line.split(";");
            double alcohol = Double.parseDouble(values[10]);

            set.add(alcohol);
        }

        br.close();
    }

    // merge sort
    public static void mergeSort(ArrayList<Double> list) {

        if (list.size() <= 1) return;

        int mid = list.size() / 2;

        ArrayList<Double> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Double> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    // merge
    public static void merge(ArrayList<Double> list, ArrayList<Double> left, ArrayList<Double> right) {

        mergeCount++;

        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {

            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }
}

/*
Problem 3 (a)
In Problem 3(a), I combined the red wine and white wine datasets and focused on the alcohol content values.

Since the task required unique values, I used a HashSet to remove duplicate entries automatically.

Then, I converted the HashSet into an ArrayList to apply sorting.

After preparing the data, I implemented the Merge Sort algorithm to sort all alcohol values in ascending order.

The result shows that the dataset is correctly sorted from the smallest to the largest value, starting from 8.0 and ending at 14.9.

This confirms that the implementation of Merge Sort is correct.

Additionally, the number of merge operations required to sort the dataset was 110.

In this part, I used data structures such as Set and ArrayList, and applied the Merge Sort algorithm, which is an efficient divide-and-conquer sorting technique.
*/


// Problem 3
// b)

class MergeTest {

    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList<>();

        // create random data
        for (int i = 0; i < 1000; i++) {
            list.add(Math.random() * 100);
        }

        // first run
        MergeSortMain.mergeCount = 0;
        MergeSortMain.mergeSort(list);
        System.out.println("Merge operations (original): " + MergeSortMain.mergeCount);

        // shuffle
        Collections.shuffle(list);
        MergeSortMain.mergeCount = 0;

        MergeSortMain.mergeSort(list);
        System.out.println("Merge operations (after shuffle): " + MergeSortMain.mergeCount);
    }
}
/*
Problem 3 (b)
In Problem 3(b), I analyzed the number of merge operations required by the Merge Sort algorithm.

I counted the number of merge operations before and after shuffling the dataset.

The results show that the number of merge operations was 999 both before and after shuffling.

This indicates that the number of merge operations does not change when the dataset is randomly shuffled.

The reason is that Merge Sort always divides the dataset into smaller parts in the same way, regardless of the initial order of the elements.

Therefore, the number of merge operations remains constant.

In this part, I applied algorithm analysis concepts and demonstrated that Merge Sort is independent of input order in terms of merge operations.
*/