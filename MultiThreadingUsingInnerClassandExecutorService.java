package com.my;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingUsingInnerClassandExecutorService {

    /**
     * This will explain how to use Executor Service and do multithreading using inner class
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * No of threads depends on no of cores CPU has
         * Formula = No of cores * (1 + IO time/CPU time)
         * i.e if IO time is higher like api/db/file calls then increase threads
         *     if CPU time is higher then no of cores will be your threads
         */
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        //conside that this list has taken employee names from database 
        List<String> empList = Arrays.asList("A", "B", "C", "D", "E");

        for (String emp : empList) {
            //multithreading is basically emulating a work flow which is done for object and since u have N no of objects
            // you want to achieve it parallelly
            Runnable r = () -> {
                double id = serviceGetId(emp);
                saveToDb(emp, id);

                /**
                 * here make sure you dont use any shared variable 
                 * if having then synchronize it or else output will be wrong
                 * see my other examples "How not to use multithreading
                 */
            };
        }

    }

    public static double serviceGetId(String name) {
        return Math.random();
    }

    public static void saveToDb(String name, double id) {
        //here basically you will wrtie a code to save your object in db.
    }
}
