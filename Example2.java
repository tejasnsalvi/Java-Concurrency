package com.myProject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example2 {

    /**
     * This will explain how to use Executor Service and do multithreading by creating Runnable class
     *
     * @param args
     */
    public static void main(String[] args) {

        List<String> empList = Arrays.asList("A", "B", "C","D","E");
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (String emp : empList) {
            MyThread myThread = new MyThread(emp);
            executor.execute(myThread);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE,TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class MyThread implements Runnable {

    private String empName;

    public MyThread(String empName) {
        this.empName = empName;
    }

    @Override
    public void run() {
        setInDb(empName);
    }
    
    void setInDb(String emp){
        System.out.println("Employee Name is:"+emp);
        //logic to create connection and persist in db
    }
}
