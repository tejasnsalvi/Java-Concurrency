package ScatterGatherUsingCompletableFuture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyCompletableFut {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> integers = IntStream.rangeClosed(1, 500).boxed().collect(Collectors.toList());
        List<MyPojo> pojoList = Collections.synchronizedList(new ArrayList<>());

        //CompletableFuture logic
        List<CompletableFuture<Void>> completableFutureList = Collections.synchronizedList(new ArrayList<>());
        for (Integer i : integers) {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(new Task(i, pojoList));
            completableFutureList.add(cf);
        }
        CompletableFuture[] arrayFutures = completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]);
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(arrayFutures);
        allTasks.get();

        //print pojo List
        System.out.println("List size:: "+pojoList.size());
        pojoList.forEach(x -> System.out.println(x));
        ;

    }
}

class Task implements Runnable {

    private Integer i;
    private List<MyPojo> pojoList;

    public Task(Integer i, List<MyPojo> pojoList) {
        this.i = i;
        this.pojoList = pojoList;
    }

    @Override
    public void run() {
        MyPojo myPojo = new MyPojo();
        myPojo.setI(i);
        myPojo.setSqrt(i * i);
        myPojo.setCubrt(i * i * i);
        pojoList.add(myPojo);
    }
}

class MyPojo {
    private Integer i;
    private Integer sqrt;
    private Integer cubrt;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getSqrt() {
        return sqrt;
    }

    public void setSqrt(Integer sqrt) {
        this.sqrt = sqrt;
    }

    public Integer getCubrt() {
        return cubrt;
    }

    public void setCubrt(Integer cubrt) {
        this.cubrt = cubrt;
    }

    @Override
    public String toString() {
        return "MyPojo{" +
                "i=" + i +
                ", sqrt=" + sqrt +
                ", cubrt=" + cubrt +
                '}';
    }
}
