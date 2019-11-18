package com.demigod.tutorial.ThreadingExamples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Example3 {

    public static void main(String[] args) {

        List<String> empList = Arrays.asList("A", "B", "C"); // Names of employee

        /**
         * Note first refer Example 1 if not referred
         */

        ExecutorService ext = Executors.newFixedThreadPool(4);

        Map<String, Integer> map = new HashMap<>();

        for (String emp : empList) {

            Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() {
                    return getSalary(emp);
                }
            };
            
            /* This is same code just written using lambda
            Callable<Double> callable = () -> {
                return getSalary(emp);
            };
            */

            Future<Integer> future = ext.submit(callable);
            //By using future we are geting the value 
            try {

                Integer sal = future.get();
                map.put(emp, sal); 

            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }

        //Iterating map which contains employee name and sal
        map.entrySet().forEach(i -> {
            System.out.println("Name: " + i.getKey() + " Sal: " + i.getValue());
        });
        
        
        ext.shutdown();
        try {
            ext.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static Integer getSalary(String emp) {
        if (emp.equals("A"))
            return 100;
        if (emp.equals("B"))
            return 200;
        else return 300;
    }

}
