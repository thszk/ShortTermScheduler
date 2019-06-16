package scheduler;

import java.util.LinkedList;

public class Class {
    // process queues
    private LinkedList<Process> newProcess = new LinkedList<>();
    private LinkedList<Process> readyProcess = new LinkedList<>();
    private LinkedList<Process> finishedProcess= new LinkedList<>();
    private String tpClass;

    // constructor
    public Class() { }

    // class type setter
    public void setTpClass(String tpClass) { this.tpClass = tpClass; }

    // add a new process at the queue
    public void addNewProcess(Process newP) { this.newProcess.addLast(newP); }

    public boolean execute(int time, boolean can) {
        boolean ret = false;
        switch (this.tpClass) {
            case "FCFS":
                ret = executeFCFS(time, can);
                break;
            case "SJF":
                ret = executeSJF(time, can);
                break;
            case "SRTF":
                ret = executeSRTF(time, can);
                break;
            case "PS":
                ret = executePS(time, can);
                break;
            case "PWPS":
                ret = executePWPS(time, can);
                break;
            case "RR":
                ret = executeRR(time, can);
                break;
        }
        return ret;
    }

    // methods of execution of the algorithms
    private int addReadyProcessFCFS(Process p) {
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
                        return 0;
                    } else {
                        this.readyProcess.add(i+1,p);
                        return 0;
                    }
                }
            }
        } else {
            this.readyProcess.add(p); // is empty, only add on ready queue
            return 0;
        }
        return 1;
    }

    private boolean executeFCFS(int time, boolean can) {
        boolean ret = false;

        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcessFCFS(newP);
                }else {
                    i++;
                }
            }
        }
        // have ready process
        if (!this.readyProcess.isEmpty() && can) {
            if (this.readyProcess.getFirst().getArrivalTime() <= time) {
                System.out.println("PID: " + this.readyProcess.getFirst().getPID() + " FCFS");
                System.out.println("initialBurst: " + this.readyProcess.getFirst().getInitialCpuBurst());
                this.readyProcess.getFirst().setRemainingCpuBurst(this.readyProcess.getFirst().getRemainingCpuBurst() - 1);
                System.out.println("remainingBurst: " + this.readyProcess.getFirst().getRemainingCpuBurst());
                System.out.println("arriveTime: " + this.readyProcess.getFirst().getArrivalTime());
                ret = true;
            }
        }
        // already finished some process
        if (!this.readyProcess.isEmpty()) {
            if (this.readyProcess.getFirst().getRemainingCpuBurst() == 0) {
                Process newP = this.readyProcess.removeFirst();
                newP.setDepartureTime(time);
                this.finishedProcess.addLast(newP);
            }
        }
        return ret;
    }

    private int addReadyProcessSJF(Process p) {
        if (!this.readyProcess.isEmpty()) { // readyProcess not empty
            for (int i = 0; i < this.readyProcess.size(); i++) {
                if (p.getRemainingCpuBurst() < this.readyProcess.get(i).getRemainingCpuBurst()) { // cpu burst <
                    this.readyProcess.add(i,p);
                    return 0;
                } else if (p.getRemainingCpuBurst() == this.readyProcess.get(i).getRemainingCpuBurst()){ // cpu burst =
                    if (p.getArrivalTime() < this.readyProcess.get(i).getArrivalTime()) { // arrival time <
                        this.readyProcess.add(i,p);
                        return 0;
                    } else if (p.getArrivalTime() == this.readyProcess.get(i).getArrivalTime()){ // arrival time =
                        if (p.getPID() < this.readyProcess.get(i).getPID()) { // pid <
                            this.readyProcess.add(i,p);
                            return 0;
                        }
                    }
                } else {
                    if (i+1 == this.readyProcess.size()) {
                        this.readyProcess.addLast(p);
                        return 0;
                    } else {
                        this.readyProcess.add(i+1,p);
                        return 0;
                    }
                }
            }
        } else {
            this.readyProcess.add(p); // is empty, only add on ready queue
            return 0;
        }
        return 1;
    }

    private boolean executeSJF(int time, boolean can) {
        boolean ret = false;

        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcessSJF(newP);
                }else {
                    i++;
                }
            }
        }
        // have ready process
        if (!this.readyProcess.isEmpty() && can) {
            if (this.readyProcess.getFirst().getArrivalTime() <= time) {
                System.out.println("PID: " + this.readyProcess.getFirst().getPID() + " SJF");
                System.out.println("initialBurst: " + this.readyProcess.getFirst().getInitialCpuBurst());
                this.readyProcess.getFirst().setRemainingCpuBurst(this.readyProcess.getFirst().getRemainingCpuBurst() - 1);
                System.out.println("remainingBurst: " + this.readyProcess.getFirst().getRemainingCpuBurst());
                System.out.println("arriveTime: " + this.readyProcess.getFirst().getArrivalTime());
                ret = true;
            }
        }
        // already finished some process
        if (!this.readyProcess.isEmpty()) {
            if (this.readyProcess.getFirst().getRemainingCpuBurst() == 0) {
                Process newP = this.readyProcess.removeFirst();
                newP.setDepartureTime(time);
                this.finishedProcess.addLast(newP);
            }
        }
        return ret;
    }

    private int addReadyProcessSRTF(Process p){
        if (!this.readyProcess.isEmpty()) {
            for (int i = 0; i < this.readyProcess.size(); i++) {
                if (p.getRemainingCpuBurst() < this.readyProcess.get(i).getRemainingCpuBurst()) {
                    this.readyProcess.add(i,p); return 0;
                } else if (p.getRemainingCpuBurst() == this.readyProcess.get(i).getRemainingCpuBurst()) {
                    if (p.getArrivalTime() < this.readyProcess.get(i).getArrivalTime()) {
                        this.readyProcess.add(i,p); return 0;
                    } else if (p.getArrivalTime() == this.readyProcess.get(i).getArrivalTime()) {
                        if (p.getPID() < this.readyProcess.get(i).getPID()) {
                            this.readyProcess.add(i,p); return 0;
                        }
                    }
                }
            }
            this.readyProcess.addLast(p); return 0;
        } else {
            this.readyProcess.add(p); return 0;
        }
    }

    private boolean executeSRTF(int time, boolean can){
        boolean ret = false;

        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcessSRTF(newP);
                }else {
                    i++;
                }
            }
        }
        // have ready process
        if(!this.readyProcess.isEmpty()  &&  can  &&  this.readyProcess.getFirst().getArrivalTime() <= time) {
            Process exec = this.readyProcess.removeFirst();
            System.out.println("PID: " + exec.getPID() + " SRTF");
            System.out.println("CPU-Burst inicial: " + exec.getInitialCpuBurst());
            exec.setRemainingCpuBurst(exec.getRemainingCpuBurst() - 1);
            System.out.println("CPU-Burst restante: " + exec.getRemainingCpuBurst());
            System.out.println("Tempo de chegada: " + exec.getArrivalTime());

            // already finished a process
            if (exec.getRemainingCpuBurst() == 0) {
                exec.setDepartureTime(time);
                this.finishedProcess.addLast(exec);
            } else {
                addReadyProcessSRTF(exec);
            }
            ret = true;
        }

        return ret;
    }

    private int addReadyProcessPS(Process p) {
        if (!this.readyProcess.isEmpty()) { // readyProcess not empty
            for (int i = 0; i < this.readyProcess.size(); i++) {
                if (p.getPriority() < this.readyProcess.get(i).getPriority() && !this.readyProcess.get(i).isStarted()) { // priority <
                    this.readyProcess.add(i,p);return 0;
                } else if (p.getPriority() == this.readyProcess.get(i).getPriority()) { // priority =
                    if (p.getRemainingCpuBurst() < this.readyProcess.get(i).getRemainingCpuBurst()) { // cpu burst <
                        this.readyProcess.add(i,p);return 0;
                    } else if (p.getRemainingCpuBurst() == this.readyProcess.get(i).getRemainingCpuBurst()){ // cpu burst =
                        if (p.getArrivalTime() < this.readyProcess.get(i).getArrivalTime()) { // arrival time <
                            this.readyProcess.add(i,p);return 0;
                        } else if (p.getArrivalTime() == this.readyProcess.get(i).getArrivalTime()){ // arrival time =
                            if (p.getPID() < this.readyProcess.get(i).getPID()) { // pid <
                                this.readyProcess.add(i,p);return 0;
                            }
                        }
                    }
                }
            }
        }
        // is empty, only add on ready queue
        // if all previous cases don't return, p will be inserted in the last index
        this.readyProcess.addLast(p);return 0;
    }

    private boolean executePS(int time, boolean can) {
        boolean ret = false;

        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcessPS(newP);
                }else {
                    i++;
                }
            }
        }
        // have ready process
        if (!this.readyProcess.isEmpty() && can) {
            if (this.readyProcess.getFirst().getArrivalTime() <= time) {
                this.readyProcess.getFirst().setStarted(true);
                System.out.println("PID: " + this.readyProcess.getFirst().getPID() + " PS");
                System.out.println("initialBurst: " + this.readyProcess.getFirst().getInitialCpuBurst());
                this.readyProcess.getFirst().setRemainingCpuBurst(this.readyProcess.getFirst().getRemainingCpuBurst() - 1);
                System.out.println("remainingBurst: " + this.readyProcess.getFirst().getRemainingCpuBurst());
                System.out.println("arriveTime: " + this.readyProcess.getFirst().getArrivalTime());
                ret = true;
            }
        }
        // already finished some process
        if (!this.readyProcess.isEmpty()) {
            if (this.readyProcess.getFirst().getRemainingCpuBurst() == 0) {
                Process newP = this.readyProcess.removeFirst();
                newP.setDepartureTime(time);
                this.finishedProcess.addLast(newP);
            }
        }
        return ret;
    }

    private int addReadyProcessPWPS(Process p) {
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

    private boolean executePWPS(int time, boolean can) {
        boolean ret = false;

        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcessPWPS(newP);
                }else {
                    i++;
                }
            }
        }
        // have ready process
        if (!this.readyProcess.isEmpty() && can) {
            if (this.readyProcess.getFirst().getArrivalTime() <= time) {
                Process exec = this.readyProcess.removeFirst();
                System.out.println("PID: " + exec.getPID() + " PWPS");
                System.out.println("initialBurst: " + exec.getInitialCpuBurst());
                exec.setRemainingCpuBurst(exec.getRemainingCpuBurst() - 1);
                System.out.println("remainingBurst: " + exec.getRemainingCpuBurst());
                System.out.println("arriveTime: " + exec.getArrivalTime());

                // already finished some process
                if (exec.getRemainingCpuBurst() == 0) {
                    exec.setDepartureTime(time);
                    this.finishedProcess.addLast(exec);
                } else {
                    addReadyProcessPWPS(exec);
                }
                ret = true;
            }
        }
        return ret;
    }

    private int addReadyProcessRR(Process p){
        if(!this.readyProcess.isEmpty() && p.getRemainingQuantum() > 0){
            for (int i = 0; i < this.readyProcess.size(); i++) {
                if (p.getArrivalTime() < this.readyProcess.get(i).getArrivalTime()) { // arrive time <
                    this.readyProcess.add(i,p); return 0;
                } else if (p.getArrivalTime() == this.readyProcess.get(i).getArrivalTime()){ // arrive time =
                    if (p.getRemainingCpuBurst() < this.readyProcess.get(i).getRemainingCpuBurst()) { // cpu burst <
                        this.readyProcess.add(i,p); return 0;
                    }
                    else if (p.getRemainingCpuBurst() == this.readyProcess.get(i).getRemainingCpuBurst()){ // cpu burst =
                        if (p.getPID() < this.readyProcess.get(i).getPID()) {
                            this.readyProcess.add(i,p); return 0;
                        }
                    }
                } else{
                    if(p.getRemainingQuantum() < this.readyProcess.get(i).getInitialQuantum()){
                        this.readyProcess.add(i,p); return 0;
                    }
                }
            }
            this.readyProcess.addLast(p); return 0;
        } else{
            p.setRemainingQuantum(p.getInitialQuantum());
            this.readyProcess.addLast(p); return 0;
        }
    }

    private boolean executeRR(int time, boolean can) {
        boolean ret = false;

        // have new process
        if (!this.newProcess.isEmpty()) {
            // check arrival time
            for (int i = 0; i < this.newProcess.size();) {
                if (time == this.newProcess.get(i).getArrivalTime()) {
                    // remove from new's queue
                    Process newP = this.newProcess.remove(i);
                    // insert on ready's queue
                    addReadyProcessRR(newP);
                }
                else {
                    i++;
                }
            }
        }
        // have ready process
        if (!this.readyProcess.isEmpty()  &&  can  &&  this.readyProcess.getFirst().getArrivalTime() <= time) {
            Process exec = this.readyProcess.removeFirst();
            System.out.println("PID: " + exec.getPID() + " RR");
            System.out.println("initialBurst: " + exec.getInitialCpuBurst());
            exec.setRemainingCpuBurst(exec.getRemainingCpuBurst() - 1);
            System.out.println("remainingBurst: " + exec.getRemainingCpuBurst());
            System.out.println("arriveTime: " + exec.getArrivalTime());
            exec.setRemainingQuantum(exec.getRemainingQuantum() - 1);
            System.out.println("remainingQuantum: " + exec.getRemainingQuantum());

            // already finished a process
            // maybe this validation should be implemented in the other algorithms
            if (exec.getRemainingCpuBurst() < 1) {
                exec.setDepartureTime(time);
                this.finishedProcess.add(exec);
            } else {
                addReadyProcessRR(exec);
            }
            ret = true;
        }
        return ret;
    }

    // prints the finished queue
    public void printFinished() {
        for (int i = 0; i < this.finishedProcess.size(); i++) {
            System.out.println(
                "\t" +
                this.finishedProcess.get(i).getPID() + "\t" +
                this.tpClass + "\t\t" +
                (((this.finishedProcess.get(i).getDepartureTime()-this.finishedProcess.get(i).getArrivalTime())-this.finishedProcess.get(i).getInitialCpuBurst())+1) + "\t\t\t" +
                ((this.finishedProcess.get(i).getDepartureTime()-this.finishedProcess.get(i).getArrivalTime())+1)
            );
        }
        System.out.println("------------------------------------");
    }

    public int sizeReady() { return this.readyProcess.size(); }

    public int sizeNew() { return this.newProcess.size(); }
}
