package scheduler;

import scheduler_algorithms.FirstComeFirstServed;
import scheduler_algorithms.ShortedJobFirst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Scheduler {
    public static void main(String[] args) {

        try {
            System.setIn(new FileInputStream(new File("/home/xogaiht/Code/ShortTermScheduler/input.txt")));
            System.setOut(new PrintStream(new FileOutputStream("/home/xogaiht/Code/ShortTermScheduler/output.txt", true)));
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
        FirstComeFirstServed fcfs = new FirstComeFirstServed();
        ShortedJobFirst sjf = new ShortedJobFirst();

        System.out.print("qtClass: ");
        qtClass = scanner.nextInt();

        for (int i = 1; i <= qtClass; i++) {
            System.out.print("choose the " + i + "º scheduling algorithm- FCFS | SJF | SRJF | PS | RR: ");
            tpClass = scanner.next().toUpperCase();

            switch (tpClass) {
                case "FCFS":
                    System.out.println("FCFS");
                    System.out.print("qtProcess: ");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.println(j + "º process");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: ");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: ");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        fcfs.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "SJF":
                    System.out.println("SJF");
                    System.out.print("qtProcess: ");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
                        System.out.println(j + "º process");
                        process.setPID(pid);
                        pid++;
                        System.out.print("CPU-burst: ");
                        gamb = scanner.nextInt();
                        totalTime += gamb;
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
                        System.out.print("arrival time: ");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);
                        sjf.addNewProcess(process);
                        process = new Process(); // clear
                    }
                    break;

                case "SRJF":
                    System.out.println("SRJF");
                    break;

                case "PS":
                    System.out.println("PS");
                    break;

                case "RR":
                    System.out.println("RR");
                    break;
            }
        }
        System.out.println("\n-------------EXEC----------------");

        // test
//        System.out.println("fila fcfs:");
//        for (int i = 0; i < fcfs.getNewProcess().size(); i++) {
//            System.out.println("PID: "+fcfs.getNewProcess().get(i).getPID());
//        }
//        System.out.println("-----------------------------");

        int time = 0;
        // while that will run schedule algorithms
        while(time <= totalTime) {
            System.out.println("time = " + time);
            fcfs.execute(time);
            sjf.execute(time);
            time++;
            System.out.println(". . . . .");
        }


    }
}
