package scheduler_algorithms;

import scheduler.Process;
import java.util.LinkedList;

public class PriorityWithPreemptionScheduling {

    private LinkedList<Process> newProcess = new LinkedList<>();
    private LinkedList<Process> readyProcess = new LinkedList<>();
    private LinkedList<Process> finishedProcess = new LinkedList<>();

    public PriorityWithPreemptionScheduling() {
    }

    public void addNewProcess(Process newP) {
        this.newProcess.addLast(newP);
    }

    public boolean hasReady() {
        return this.readyProcess.isEmpty();
    }

    private int addReadyProcess(Process p) {
        if (!this.readyProcess.isEmpty()) { // readyProcess not empty
            for (int i = 0; i < this.readyProcess.size(); i++) {
                if (p.getPriority() < this.readyProcess.get(i).getPriority()) { // priority <
                    this.readyProcess.add(i,p);
                } else if (p.getPriority() == this.readyProcess.get(i).getPriority()) { // priority =
                    if (p.getRemainingCpuBurst() < this.readyProcess.get(i).getRemainingCpuBurst()) { // cpu burst <
                        this.readyProcess.add(i,p);
                    } else if (p.getRemainingCpuBurst() == this.readyProcess.get(i).getRemainingCpuBurst()){ // cpu burst =
                        if (p.getArrivalTime() < this.readyProcess.get(i).getArrivalTime()) { // arrival time <
                            this.readyProcess.add(i,p);
                        } else if (p.getArrivalTime() == this.readyProcess.get(i).getArrivalTime()){ // arrival time =
                            if (p.getPID() < this.readyProcess.get(i).getPID()) { // pid <
                                this.readyProcess.add(i,p);
                            }
                        }
                    }
                } else {
                    if (i + 1 == this.readyProcess.size()) {
                        this.readyProcess.addLast(p);
                    } else {
                        this.readyProcess.add(i + 1, p);
                    }
                }
                return 0;
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
                Process exec = this.readyProcess.removeFirst();
                System.out.println("PID: " + exec.getPID() + " PWPS");
                System.out.println("initialBurst: " + exec.getInitialCpuBurst());
                exec.setRemainingCpuBurst(exec.getRemainingCpuBurst() - 1);
                System.out.println("remainingBurst: " + exec.getRemainingCpuBurst());
                System.out.println("arriveTime: " + exec.getArrivalTime());
                addReadyProcess(exec);
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
