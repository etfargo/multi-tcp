package csci455_project1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private static Lock lock = new ReentrantLock(); 
    private int count = 0;

    public int getTotal() {
        return count;
    }

    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            count += 1;
        } catch (Exception ex) {
        } finally {
            lock.unlock(); // Release the lock
        }
    }
    
}
