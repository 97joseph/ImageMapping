// READING TO DO
// http://tutorials.jenkov.com/java-concurrency/creating-and-starting-threads.html
// https://www.baeldung.com/java-wait-notify
// https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html
// https://www.baeldung.com/thread-pool-java-and-guava


//Question 1: "Could this be a method that takes an ImageProcessorMT as a param and adds it to a queue we can call waiting list?"
//Answer 1: yes, you have a public method that takes the ImageProcessorMT and then adds it to a queue that is an ArrayList, waiting to then start this

// Question 2 & 3: Does start here mean the Runnable run method? Would it only call the run method of the ImageProcessorMT?
// Answer 2: I need to look at the exact code you have been given to provide a proper answer here. In any case:
// If the ThreadPool implements Runnable, then this logic would go in the run() method.
// To start a new runnable you need to do the logic in getNextTask() first, to get the next available runnable
// then you need to add the runnbale to a new Thread() and then call thread.start() (see link above).
// initially if the limit is 5, you should start all the first 5 tasks.
// you will then need some logic to figure out when one of the threads is done, so you can check if your queue has another task, and start that one

// question 4: this wluld be a separate method that the main() function can call. this method should guarantee that it returns ONLY when the runanble is finished.


class ThreadPool {
  ArrayList<ImageProcessorMT> queue;
  ArrayList<Thread> activeThreads;
  int MAX_PARALLEL_TASKS = 5;

  // Q1:
  public void addTask(ImageProcessorMT task) {
    // CHECK: if task is running already / or finished, ignore
    // else, add to queu
    if(CAN_ADD_TO_QUEUE) {
      queue.push(task);
      // don't start
    }
  }


  // Q2: this function is called on a separate thread
  // so it doesn't block the main thread
  public void run() { 
    // while we are still running
    // try starting next task
    while(activeThreads.size()) {
      // check if any threads are done
      for each activeThread {
        // iterate in reverse order, so that when you call remove, the index is always avlid (ie if 5 threads, start at index = 4 and then go down to 0)
        // so that if thread at indx 3 is to be removed, then the next index will be 2 and it exists in the array
        if(activeThread.isAlive() == false) {
          // activethread finished, so remove from thread list
          activeThreads.remove(indexOfActiveThread);

        }
      }

      // after we have checked all the threads that are completed
      // let's start as many threads as we can
      
      Thread thread = startNextTask()
      while(thread != null) {
        // a new thread was started
        activeThreads.push(thread)
      }
    }
  }

  // called directly from the main thread?
  public void join() {
    while(true) {
      if(activeThreads.size() == 0) return
    }
  }

  private bool startNextTask() {
    ImageProcessorMT task = this->getNextTask();
    if(task != null) {
      Thread thread = new Thread(task);
      thread.start();
      return thread;
    }
    return null;
  }

  private ImageProcessorMT getNextTask() {
    // check if we have a task we can run in the queue
    if(queue.size() == 0) return null;
    // check if we can run another task or we have hit the limit
    if(activeThreads.size() >= MAX_PARALLEL_TASKS) return null;
    // return the next task
    ImageProcessorMT task = queue.takeAt(0); // take the first task in queue and reduce the queue
    // return task
    return task;
  }

}