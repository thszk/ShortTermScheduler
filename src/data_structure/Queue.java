package data_structure;

import scheduler.Process;
import java.util.LinkedList;

public class Queue{

    private LinkedList<Process> queue = new LinkedList<>();

    public Queue() {
    }

    public LinkedList<Process> getQueue() {
        return queue;
    }

    public void setQueue(LinkedList<Process> queue) {
        this.queue = queue;
    }

    public void insert(Process p) {
        this.queue.add(p);
    }

    public Process remove() {
        return this.queue.remove(0);
    }

    public boolean empty() {
        return this.queue.size() == 0;
    }

}
