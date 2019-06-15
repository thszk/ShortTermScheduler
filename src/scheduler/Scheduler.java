package scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Scheduler {
    public static void main(String[] args) {

        // set I/O default
        try {
            System.setIn(new FileInputStream(new File("/home/xogaiht/Code/ShortTermScheduler/input.txt")));
            System.setOut(new PrintStream(new FileOutputStream("/home/xogaiht/Code/ShortTermScheduler/output.txt", false)));
        } catch (Exception e) {
            System.out.println(e);
        }

        Scanner scanner = new Scanner(System.in);
        int qtClass;
        String tpClass;
        int qtProcess;
        int gamb;
        int totalTime = 0;

        int pid = 1;
        Process process = new Process();
        Class classP = new Class();
        LinkedList<Class> mqs = new LinkedList<>();

        System.out.print("qtClass: \n");
        qtClass = scanner.nextInt();


        for (int i = 1; i <= qtClass; i++) {
            System.out.print("choose the " + i + "º scheduling algorithm- FCFS | SJF | SRTF | PS | PWPS | RR: \n");
            tpClass = scanner.next().toUpperCase();

            switch (tpClass) {
                case "FCFS":
                    System.out.print("FCFS\n");
                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        // principal queue settings
                        classP.setTpClass("FCFS");
                        classP.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "SJF":
                    System.out.print("SJF\n");
                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        // principal queue settings
                        classP.setTpClass("SJF");
                        classP.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "SRTF":
                    System.out.print("SRTF\n");
                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        // principal queue settings
                        classP.setTpClass("SRTF");
                        classP.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "PS":
                    System.out.print("PS\n");
                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        System.out.print("priority: \n");
                        gamb = scanner.nextInt();
                        process.setPriority(gamb);
                        // whatever
                        process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        // principal queue settings
                        classP.setTpClass("PS");
                        classP.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "PWPS":
                    System.out.print("PWPS\n");
                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        System.out.print("priority: \n");
                        gamb = scanner.nextInt();
                        process.setPriority(gamb);
                        // whatever
                        process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        // principal queue settings
                        classP.setTpClass("PWPS");
                        classP.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "RR":
                    System.out.print("RR\n");
                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        System.out.print("quantum: \n");
                        gamb = scanner.nextInt();
                        process.setInitialQuantum(gamb);
                        process.setRemainingQuantum(gamb);
                        // whatever
                        process.setPriority(0);
                        // principal queue settings
                        classP.setTpClass("RR");
                        classP.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;
            }
            mqs.add(classP);
            classP = new Class();
        }

        System.out.println("\n-------------EXEC----------------");

        int time = 0;
        Boolean pivot;

        // while that will run schedule algorithms
        while(time <= totalTime) {
            System.out.println("time = " + time);
            pivot = true;

            for (int i = 0; i < mqs.size(); i++) {
                if (mqs.get(i).execute(time, pivot)) {
                    pivot = false;
                }
            }

            time++;
            System.out.println(". . . . . . . . . .");
        }
    }
}
