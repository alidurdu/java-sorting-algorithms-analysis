import java.io.*;
import java.util.*;

// Problem 2 - Insertion Sort
// a)

public class InsertionSortMain {

    public static void main(String[] args) throws Exception {

        // unique alcohol values
        Set<Double> alcoholSet = new HashSet<>();

        // read datasets
        readFile("src/winequality-red.csv", alcoholSet);
        readFile("src/winequality-white.csv", alcoholSet);

        // convert to list
        ArrayList<Double> alcoholList = new ArrayList<>(alcoholSet);

        // apply insertion sort
        insertionSort(alcoholList);

        System.out.println("Insertion Sort:");
        System.out.println(alcoholList);
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

    // insertion sort
    public static void insertionSort(ArrayList<Double> list) {

        int n = list.size();

        for (int i = 1; i < n; i++) {

            double key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }
}

/*
Problem 2 (a)
In Problem 2(a) I combined the red wine and white wine datasets and focused on the alcohol content values.

Since the task required unique values I used a HashSet to remove duplicate entries automatically.

Then I converted the HashSet into an ArrayList to apply sorting.

After preparing the data I implemented the Insertion Sort algorithm to sort all alcohol values in ascending order.

The result shows that the algorithm correctly sorts the dataset from the smallest to the largest value starting from 8.0 and ending at 14.9.

This confirms that the implementation of Insertion Sort is correct.

*/


// Problem 2
// b)

class InsertionTest {

    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList<>();

        // create random data
        for (int i = 0; i < 1000; i++) {
            list.add(Math.random() * 100);
        }

        // shuffle
        Collections.shuffle(list);

        long start = System.nanoTime();

        // use main class for insertionSort
        InsertionSortMain.insertionSort(list);

        long end = System.nanoTime();

        System.out.println("Time taken: " + (end - start) + " ns");
    }
}

/*
Problem 2 (b)
In Problem 2(b) I analyzed the time complexity of the Insertion Sort algorithm.

Insertion Sort has a time complexity of O(n) in the best case when the list is already sorted, because it only requires one comparison per element.

In the average case, the time complexity is O(n^2) since elements need to be shifted multiple times.

In the worst case, when the list is in reverse order the time complexity becomes O(n^2) because each element must be compared and shifted multiple times.

To support this analysis I measured the execution time of the algorithm. The result was approximately 10 milliseconds for the dataset used.

If the dataset is randomly shuffled before sorting, the overall time complexity does not change.

It remains O(n^2) because the algorithm still performs a quadratic number of operations in the average and worst cases.

However the actual running time may increase because a shuffled list requires more shifting operations compared to a nearly sorted list.

*/

