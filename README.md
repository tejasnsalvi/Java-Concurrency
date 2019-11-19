# Java-Concurrency
Java multithreading and concurrency control examples with real life application and use cases

## What is Multithreading?
   </br> 1.Writing non-blocking code by running a task on a separate thread than the main application thread.
         2.This way, your main thread does not block/wait for the completion of the task and it can execute other tasks in parallel.
   
Various ways to do multithreading

### 1.How to do multithreading using inner class and executor service

   </br>Refer :- **Example1.java** 
   </br>Note:- This technique is very helpful here you no need to create separate class instead just create a method and put your runnable logic in it
  
### 2.How to do multithreading using Runnable class and executor service

   </br>Refer :- **Example2.java** 
   </br>Note:- Here we create a separate Runnable class and pass our argument or variable via **Constructor** . This technique is helpful when you have a big logic.
   
### 3.How to do fetch multithreading result using Callable,Future,executor service

   </br>Refer :- **Example3.java** 
   </br>Note:- This technique is used when you want the result of thread.  
