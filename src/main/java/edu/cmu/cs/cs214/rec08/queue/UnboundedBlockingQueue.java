package edu.cmu.cs.cs214.rec08.queue;

import java.util.ArrayDeque;
import java.util.Deque;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Modify this class to be thread-safe and be an UnboundedBlockingQueue.
 */
@ThreadSafe
public class UnboundedBlockingQueue<E> implements SimpleQueue<E> {
    @GuardedBy("this")
    private Deque<E> queue = new ArrayDeque<>();

    public UnboundedBlockingQueue() { }

    public synchronized boolean isEmpty() { return queue.isEmpty(); }

    public synchronized int size() { return queue.size(); }

    public synchronized E peek() { return queue.peek(); }

    public synchronized void enqueue(E element) { 
        queue.add(element); 
        notifyAll();
    }

    /**
     * TODO:  Change this method to block (waiting for an enqueue) rather
     * than throw an exception.  Completing this task may require
     * modifying other methods.
     */
    public synchronized E dequeue() throws InterruptedException { 
        // If queue is empty, block until notifyAll() is called by enqueue()
        while (queue.isEmpty()) {
            wait();
        }
        return queue.remove(); 
    }

    public synchronized String toString() { return queue.toString(); }
}
