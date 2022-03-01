class ThreadPool {
  ArrayList<ImageProcessorMT> queue;
  ArrayList<Thread> activeThreads;
  int MAX_PARALLEL_TASKS = 5;


  public void addTask(ImageProcessorMT task) {
    // CHECK: if task is running already / or finished, ignore
    // else, add to queu
    if(CAN_ADD_TO_QUEUE) {
      queue.push(task);
      // don't start
    }
  }

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