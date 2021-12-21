package com.series;

import java.util.ArrayList;

public class NumberSeries {

    // Loops around the max required number series and calls the recursive function which is less intensive on the resources.
    public static ArrayList<Integer> getFibonacci(int maxNumber){
        ArrayList list = new ArrayList<>();

        for (int i = 0; i < maxNumber;i++){
            list.add(RecursiveFibonacci(i));
        }

        return list;
    }

    // Recursive method for working out the value of the term based on the number term provided.
    public static int RecursiveFibonacci(int n)  {
        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else
            return RecursiveFibonacci(n - 1) + RecursiveFibonacci(n - 2);
    }

    // More resource intensive.
    private static ArrayList getFibonacciLoop(int maxNumber){
        ArrayList fibonacciList = new ArrayList();
        int prevNumber = -1;
        int nxtNumber  = 0;

        for (int i = 0; i <= maxNumber; ++i)
        {
            int sum = prevNumber + nxtNumber;
            prevNumber = nxtNumber;
            nxtNumber = sum;

            fibonacciList.add(Math.abs(prevNumber));
        }

        return fibonacciList;
    }

    // Works out the prime number list.
    public static ArrayList getPrimeList(int size){
        ArrayList<Integer> primeList = new ArrayList<>();

        int start = 1;
        int current = 0;

        for (int i = 0;i < size;i++){
            if (i == 0){
                current = start;
            }
            else{
                current += 2;
            }

            primeList.add(current);
        }

        return primeList;
    }
}
