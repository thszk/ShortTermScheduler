package scheduler_algorithms;

import scheduler.Process;

import java.util.LinkedList;

public class FirstComeFirstServed {
    private LinkedList<Process> newProcess = new LinkedList<>();
    private LinkedList<Process> readyProcess = new LinkedList<>();
    private LinkedList<Process> finishedProcess = new LinkedList<>();

    public FirstComeFirstServed() {
    }

    public void addNewProcess(Process newP) {
        this.newProcess.addLast(newP);
    }

    public int addReadyProcess(Process p) {
        if (!this.readyProcess.isEmpty()) { // readyProcess not empty
            for (int i = 0; i < this.readyProcess.size(); i++) {
                if (p.getArrivalTime() < this.readyProcess.get(i).getArrivalTime()) { // arrive time <
                    this.readyProcess.add(i,p);
                    return 0;
                } else if (p.getArrivalTime() == this.readyProcess.get(i).getArrivalTime()){ // arrive time =
                    if (p.getRemainingCpuBurst() < this.readyProcess.get(i).getRemainingCpuBurst()) { // cpu burst <
                        this.readyProcess.add(i,p);
                        return 0;
                    } else if (p.getRemainingCpuBurst() == this.readyProcess.get(i).getRemainingCpuBurst()){ // cpu burst =
                        if (p.getPID() < this.readyProcess.get(i).getPID()) {
                            this.readyProcess.add(i,p);
                            return 0;
                        }
                    }
                } else {
                    if (i+1 == this.readyProcess.size()) {
                        this.readyProcess.addLast(p);
                    } else {
                        this.readyProcess.add(i+1,p);
                    }
                }
            }
        } else {
            this.readyProcess.add(p); // is empty, only add on ready queue
        }
        return 1;
    }

    public void execute(int time) {
        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcess(newP);
                }else {
                    i++;
                }
            }
        }
        // have ready process
        if (!this.readyProcess.isEmpty()) {
            if (this.readyProcess.getFirst().getArrivalTime() <= time) {
                System.out.println("PID: " + this.readyProcess.getFirst().getPID() + " FCFS");
                System.out.println("initialBurst: " + this.readyProcess.getFirst().getInitialCpuBurst());
                this.readyProcess.getFirst().setRemainingCpuBurst(this.readyProcess.getFirst().getRemainingCpuBurst() - 1);
                System.out.println("remainingBurst: " + this.readyProcess.getFirst().getRemainingCpuBurst());
                System.out.println("arriveTime: " + this.readyProcess.getFirst().getArrivalTime());
            }
        }
        // already finished some process
        if (!this.readyProcess.isEmpty()) {
            if (this.readyProcess.getFirst().getRemainingCpuBurst() == 0) {
                Process newP = this.readyProcess.removeFirst();
                this.finishedProcess.addLast(newP);
            }
        }
    }
}
