import java.io.*;
import java.util.*;

// Problem 1 - Bubble Sort
// a)

public class BubleSortMain {

    public static void main(String[] args) throws Exception {

        // unique alcohol values
        Set<Double> alcoholSet = new HashSet<>();

        // read datasets
        readFile("src/winequality-red.csv", alcoholSet);
        readFile("src/winequality-white.csv", alcoholSet);

        // convert to list
        ArrayList<Double> alcoholList = new ArrayList<>(alcoholSet);

        // normal bubble sort
        ArrayList<Double> list1 = new ArrayList<>(alcoholList);
        bubbleSort(list1);
        System.out.println("Normal Bubble Sort:");
        System.out.println(list1);

        // optimized bubble sort
        ArrayList<Double> list2 = new ArrayList<>(alcoholList);
        optimizedBubbleSort(list2);
        System.out.println("\nOptimized Bubble Sort:");
        System.out.println(list2);
    }

    // read CSV
    public static void readFile(String file, Set<Double> set) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine(); // skip header

        while ((line = br.readLine()) != null) {

            String[] values = line.split(";");
            double alcohol = Double.parseDouble(values[10]);

            set.add(alcohol); // unique
        }

        br.close();
    }

    // normal bubble sort
    public static void bubbleSort(ArrayList<Double> list) {

        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                if (list.get(j) > list.get(j + 1)) {

                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // optimized bubble sort
    public static void optimizedBubbleSort(ArrayList<Double> list) {

        int n = list.size();

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {

                if (list.get(j) > list.get(j + 1)) {

                    double temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swapped = true;
                }
            }

            // early stop
            if (!swapped) {
                break;
            }
        }
    }
}

/*
Problem 1 (a)
In this part I combined the red and white wine datasets and worked with the alcohol values.

Since only unique values were needed I used a HashSet to remove duplicates automatically.

After that I converted the data into an ArrayList so I could apply sorting because ArrayList is easier to work with when accessing elements by index.

Then I implemented two versions of Bubble Sort one normal and one optimized.

Both of them sorted the values in ascending order without any problem.

The output shows that both versions give the same result starting from 8.0 up to 14.9, so the implementation seems correct.

The optimized version is a bit better because it can stop early if the list is already sorted but the final result is still the same.

*/

// Problem 1
// b)

class TestComplexity {

    public static void main(String[] args) {

        ArrayList<Double> list = new ArrayList<>();

        // create random data
        for (int i = 0; i < 1000; i++) {
            list.add(Math.random() * 100);
        }

        // shuffle
        Collections.shuffle(list);

        long start = System.nanoTime();

        // use main class for bubblesort
        BubleSortMain.bubbleSort(list);

        long end = System.nanoTime();

        System.out.println("Time taken: " + (end - start) + " ns");
    }
}
/*
Problem 1 (b)
In this part I looked at the time complexity of Bubble Sort.

The normal version has O(n^2) complexity in all cases because it always compares elements even if the list is already sorted.

The optimized version is slightly better in the best case. If the list is already sorted it stops early and works in O(n).

But in the worst case it is still O(n^2) same as the normal version.

I also measured the execution time and it was around 16 milliseconds for this dataset.

When the list is randomly shuffled the overall time complexity does not change. It is still O(n^2).

However the running time can be a bit higher because more swaps are needed compared to a nearly sorted list.

So basically shuffle does not change Big-O but it affects how long it actually takes.

*/

