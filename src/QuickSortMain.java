import java.io.*;
import java.util.*;

// Problem 4 - Quick Sort
// a)

public class QuickSortMain {

    static int comparisons = 0;

    public static void main(String[] args) throws Exception {

        Set<Double> alcoholSet = new HashSet<>();

        readFile("src/winequality-red.csv", alcoholSet);
        readFile("src/winequality-white.csv", alcoholSet);

        ArrayList<Double> baseList = new ArrayList<>(alcoholSet);

        // FIRST PIVOT
        ArrayList<Double> list1 = new ArrayList<>(baseList);
        comparisons = 0;
        quickSortFirst(list1, 0, list1.size() - 1);
        System.out.println("First Pivot Comparisons: " + comparisons);

        // LAST PIVOT
        ArrayList<Double> list2 = new ArrayList<>(baseList);
        comparisons = 0;
        quickSortLast(list2, 0, list2.size() - 1);
        System.out.println("Last Pivot Comparisons: " + comparisons);

        // RANDOM PIVOT
        ArrayList<Double> list3 = new ArrayList<>(baseList);
        comparisons = 0;
        quickSortRandom(list3, 0, list3.size() - 1);
        System.out.println("Random Pivot Comparisons: " + comparisons);

        // MEDIAN OF THREE
        ArrayList<Double> list4 = new ArrayList<>(baseList);
        comparisons = 0;
        quickSortMedian(list4, 0, list4.size() - 1);
        System.out.println("Median Pivot Comparisons: " + comparisons);
    }

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

    // FIRST
    public static void quickSortFirst(ArrayList<Double> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high, list.get(low));
            quickSortFirst(list, low, pi - 1);
            quickSortFirst(list, pi + 1, high);
        }
    }

    // LAST
    public static void quickSortLast(ArrayList<Double> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high, list.get(high));
            quickSortLast(list, low, pi - 1);
            quickSortLast(list, pi + 1, high);
        }
    }

    // RANDOM
    public static void quickSortRandom(ArrayList<Double> list, int low, int high) {
        if (low < high) {
            int rand = new Random().nextInt(high - low + 1) + low;
            int pi = partition(list, low, high, list.get(rand));
            quickSortRandom(list, low, pi - 1);
            quickSortRandom(list, pi + 1, high);
        }
    }

    // MEDIAN
    public static void quickSortMedian(ArrayList<Double> list, int low, int high) {
        if (low < high) {

            int mid = (low + high) / 2;
            double a = list.get(low);
            double b = list.get(mid);
            double c = list.get(high);

            double pivot = median(a, b, c);

            int pi = partition(list, low, high, pivot);

            quickSortMedian(list, low, pi - 1);
            quickSortMedian(list, pi + 1, high);
        }
    }

    // PARTITION
    public static int partition(ArrayList<Double> list, int low, int high, double pivot) {

        int i = low;

        for (int j = low; j <= high; j++) {

            comparisons++;

            if (list.get(j) < pivot) {
                Collections.swap(list, i, j);
                i++;
            }
        }

        return i;
    }

    public static double median(double a, double b, double c) {
        if ((a > b && a < c) || (a < b && a > c)) return a;
        if ((b > a && b < c) || (b < a && b > c)) return b;
        return c;
    }
}

/*
Problem 4 (a)
In this part, I implemented Quick Sort with four different pivot choices: first element, last element, random element and median-of-three.

All of them sorted the alcohol values correctly in ascending order.

When I checked the number of comparisons, I got these results:
First pivot: 792
Last pivot: 4857
Random pivot: 892
Median-of-three pivot: 719

From these values, it is clear that pivot selection really affects performance.

Using the last element as pivot gave much higher comparisons so it is not a good choice here. It probably created unbalanced partitions.

First and random pivot worked okay, not the best but still reasonable.

The best result came from median-of-three It had the lowest number of comparisons. This makes sense because it usually picks a value closer to the middle.

So partitions become more balanced and the algorithm works faster.
*/


// Problem 4
// b)

class QuickSortTest {

    public static void main(String[] args) {

        ArrayList<Double> baseList = new ArrayList<>();

        // sadece veri üret
        for (int i = 0; i < 1000; i++) {
            baseList.add(Math.random() * 100);
        }

        // FIRST
        ArrayList<Double> list1 = new ArrayList<>(baseList);
        QuickSortMain.comparisons = 0;
        QuickSortMain.quickSortFirst(list1, 0, list1.size() - 1);
        System.out.println("First Pivot Comparisons: " + QuickSortMain.comparisons);

        // LAST
        ArrayList<Double> list2 = new ArrayList<>(baseList);
        QuickSortMain.comparisons = 0;
        QuickSortMain.quickSortLast(list2, 0, list2.size() - 1);
        System.out.println("Last Pivot Comparisons: " + QuickSortMain.comparisons);

        // RANDOM
        ArrayList<Double> list3 = new ArrayList<>(baseList);
        QuickSortMain.comparisons = 0;
        QuickSortMain.quickSortRandom(list3, 0, list3.size() - 1);
        System.out.println("Random Pivot Comparisons: " + QuickSortMain.comparisons);

        // MEDIAN
        ArrayList<Double> list4 = new ArrayList<>(baseList);
        QuickSortMain.comparisons = 0;
        QuickSortMain.quickSortMedian(list4, 0, list4.size() - 1);
        System.out.println("Median Pivot Comparisons: " + QuickSortMain.comparisons);
    }
}

/*
Problem 4 (b)
In this part, I focused on how the number of comparisons changes with different pivot strategies.

From the dataset used in part (a), the comparison counts were:
First pivot: 792
Last pivot: 4857
Random pivot: 892
Median-of-three pivot: 719

Then I tested the same algorithms with randomly generated data. The results were:
First pivot: 11380
Last pivot: 139895
Random pivot: 11372
Median-of-three pivot: 10055

As seen, the number of comparisons changes a lot depending on the pivot.

The last pivot performed the worst especially with random data. It created very unbalanced splits.

Median-of-three again gave the best result It seems more stable and gives better partitions.

Random pivot also worked fine since it avoids always picking bad values.

So yes, pivot strategy directly affects the number of comparisons. Better pivot means better performance.

For this dataset median-of-three looks like the best option.
*/