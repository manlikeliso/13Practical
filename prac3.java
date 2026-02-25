import java.lang.Math.*;   import java.io.*;   import java.text.*;

import java.text.DecimalFormat;



public class timeMethods {



    public static int N = 32654; // number of records



    // ------------------ Node Class ------------------

    static class Node {

        int key;

        String data;



        Node(int key, String data) {

            this.key = key;

            this.data = data;

        }

    }



    // ------------------ Linear Search ------------------

    static Node linearSearch(Node[] array, int key) {

        for (int i = 0; i < array.length; i++) {

            if (array[i].key == key) {

                return array[i];

            }

        }

        return null;

    }



    // ------------------ Binary Search ------------------

    static Node binarySearch(Node[] array, int key) {

        int low = 0;

        int high = array.length - 1;



        while (low <= high) {

            int mid = (low + high) / 2;



            if (array[mid].key == key)

                return array[mid];

            else if (array[mid].key < key)

                low = mid + 1;

            else

                high = mid - 1;

        }

        return null;

    }



    // ------------------ Main Method ------------------

    public static void main(String[] args) {



        DecimalFormat fiveD = new DecimalFormat("0.00000");



        Node[] array = new Node[N];



        // ----------- Read File -----------

        try {

            BufferedReader br = new BufferedReader(

                    new FileReader("ulysses-1.numbered"));



            String line;

            int index = 0;



            while ((line = br.readLine()) != null && index < N) {

                int key = Integer.parseInt(line.substring(0, 5));

                String data = line.substring(6);

                array[index] = new Node(key, data);

                index++;

            }



            br.close();

        } catch (IOException e) {

            System.out.println("Error reading file.");

            return;

        }



        int repetitions = 30;



        double linearTotal = 0;

        double linearTotalSq = 0;



        double binaryTotal = 0;

        double binaryTotalSq = 0;



        // ----------- Experiment -----------

        for (int i = 0; i < repetitions; i++) {



            int randomKey = (int)(Math.random() * 32654) + 1;



            // ---- Linear Timing ----

            long start = System.nanoTime();

            linearSearch(array, randomKey);

            long finish = System.nanoTime();



            double linearTime = finish - start;



            linearTotal += linearTime;

            linearTotalSq += linearTime * linearTime;



            // ---- Binary Timing ----

            start = System.nanoTime();

            binarySearch(array, randomKey);

            finish = System.nanoTime();



            double binaryTime = finish - start;



            binaryTotal += binaryTime;

            binaryTotalSq += binaryTime * binaryTime;

        }



        // ----------- Statistics -----------



        double linearAvg = linearTotal / repetitions;

        double linearStd = Math.sqrt(

                (linearTotalSq - repetitions * linearAvg * linearAvg)

                        / (repetitions - 1)

        );



        double binaryAvg = binaryTotal / repetitions;

        double binaryStd = Math.sqrt(

                (binaryTotalSq - repetitions * binaryAvg * binaryAvg)

                        / (repetitions - 1)

        );



        // Convert nanoseconds to milliseconds

        linearAvg /= 1_000_000.0;

        linearStd /= 1_000_000.0;

        binaryAvg /= 1_000_000.0;

        binaryStd /= 1_000_000.0;



        // ----------- Output -----------



        System.out.println("\nRESULTS");

        System.out.println("-----------------------------------");



        System.out.println("Linear Search:");

        System.out.println("Average Time = " + fiveD.format(linearAvg) + " ms");

        System.out.println("Std Deviation = " + fiveD.format(linearStd) + " ms");



        System.out.println();



        System.out.println("Binary Search:");

        System.out.println("Average Time = " + fiveD.format(binaryAvg) + " ms");

        System.out.println("Std Deviation = " + fiveD.format(binaryStd) + " ms");



        System.out.println("-----------------------------------");

    }

}