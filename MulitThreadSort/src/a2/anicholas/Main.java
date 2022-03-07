package a2.anicholas;

import a1.anicholas.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // write your code here
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();

        System.out.println("Do you want a [S]ingle sort, [D]ual sort, or a [Q]uad sort? ");
        char selection = scan.next().charAt(0);

        System.out.println("How many items do you want to sort? ");
        int count = scan.nextInt();

        Item[] items = new Item[count];

        for (int i = 0; i < count; i++) {
            int t = ran.nextInt(4);
            System.out.println(t);

            switch (t) {
                case 0:
                    items[i] = a1.anicholas.Main.genFood();
                    break;
                case 1:
                    items[i] = a1.anicholas.Main.genTool();
                    break;
                case 2:
                    items[i] = a1.anicholas.Main.genMaterials();
                    break;
                case 3:
                    items[i] = a1.anicholas.Main.genMunitions();
                    break;
            }
        }

        switch (selection) {
            case 's':
            case 'S':
                SingleSort(items);
                break;
            case 'd':
            case 'D':
                DualSort(items);
                break;
            case 'q':
            case 'Q':
                QuadSort(items);
                break;
        }
    }

    private static void QuadSort(Item[] items) throws InterruptedException {
        int midp = Math.round(items.length / 2f);
        int q1_q2 = Math.round(midp / 2f);
        int q3_q4 = Math.round(midp + q1_q2);
        ThreadSort th1 = new ThreadSort(items, 0, q1_q2);
        ThreadSort th2 = new ThreadSort(items, q1_q2, midp);
        ThreadSort th3 = new ThreadSort(items, midp, q3_q4);
        ThreadSort th4 = new ThreadSort(items, q3_q4, items.length);

        long startTime = System.nanoTime();
        th1.start();
        th2.start();
        th3.start();
        th4.start();

        th1.join();
        th2.join();
        th3.join();
        th4.join();

        MergeSort m1 = new MergeSort(th1.gettItems(), th2.gettItems());
        MergeSort m2 = new MergeSort(th3.gettItems(), th4.gettItems());

        m1.start();
        m2.start();
        m1.join();
        m2.join();

        MergeSort mF = new MergeSort(m1.getSortedItems(), m2.getSortedItems());
        mF.start();
        mF.join();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        for (Item i : mF.getSortedItems()) {
            System.out.println(i);
        }
        System.out.println("Quad Sort took: " + duration);
    }

    private static void DualSort(Item[] items) throws InterruptedException {
        int mid = Math.round(items.length / 2f);
        ThreadSort t1 = new ThreadSort(items, 0, mid);
        ThreadSort t2 = new ThreadSort(items, mid, items.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m1 = new MergeSort(t1.gettItems(), t2.gettItems());
        m1.start();
        m1.join();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        for (Item i : m1.getSortedItems()) {
            System.out.println(i);
        }
        System.out.println("Dual Sort took: " + duration);
    }

    private static void SingleSort(Item[] items) {
        // Sort b4 print
        ThreadSort single = new ThreadSort(items, 0, items.length);
        long startTime = System.nanoTime();
        single.start();
        try {
            single.join();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            Item[] sortedItems = single.gettItems();
            for (Item i : sortedItems) {
                System.out.println(i);
            }
            System.out.println("Was sorted in: " + duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
