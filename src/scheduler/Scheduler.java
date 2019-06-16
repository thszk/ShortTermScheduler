package scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Scheduler {
    public static void main(String[] args) {

        // sets the default I/O to the text file
        try {
            System.setIn(new FileInputStream(new File("/home/xogaiht/Code/ShortTermScheduler/input.txt")));
            System.setOut(new PrintStream(new FileOutputStream("/home/xogaiht/Code/ShortTermScheduler/output.txt", false)));
        } catch (Exception e) {
            System.out.println(e);
        }

        // from data input
        Scanner scanner = new Scanner(System.in);
        int qtClass, qtProcess, gamb;
        String tpClass;

        // from storage
        int pid = 1;
        Class classP = new Class();
        Process process = new Process();
        LinkedList<Class> mqs = new LinkedList<>(); // mqs :: multilevel queue scheduling, stores all the process classes

        // from execution
        int time = 0;
        boolean can, exec = true;

        // data input
//        System.out.print("qtClass: \n");
        qtClass = scanner.nextInt();

        for (int i = 1; i <= qtClass; i++) {
//            System.out.print("choose the " + i + "º scheduling algorithm- FCFS | SJF | SRTF | PS | PWPS | RR: \n");
            tpClass = scanner.next().toUpperCase();

            switch (tpClass) {
                case "FCFS":
//                    System.out.print("FCFS\n");
//                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
//                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
//                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
//                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);process.setDepartureTime(0);
                        classP.addNewProcess(process); // add process at class's queue
                        process = new Process(); // clear
                    }
                    classP.setTpClass("FCFS");
                    break;

                case "SJF":
//                    System.out.print("SJF\n");
//                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
//                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
//                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
//                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);process.setDepartureTime(0);
                        classP.addNewProcess(process); // add process at class's queue
                        process = new Process(); // clear
                    }
                    classP.setTpClass("SJF");
                    break;

                case "SRTF":
//                    System.out.print("SRTF\n");
//                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
//                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
//                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
//                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
                        // whatever
                        process.setPriority(0);process.setInitialQuantum(0);process.setRemainingQuantum(0);process.setDepartureTime(0);
                        classP.addNewProcess(process); // add process at class's queue
                        process = new Process(); // clear
                    }
                    classP.setTpClass("SRTF");
                    break;

                case "PS":
//                    System.out.print("PS\n");
//                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
//                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
//                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
//                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
//                        System.out.print("priority: \n");
                        gamb = scanner.nextInt();
                        process.setPriority(gamb);
                        // whatever
                        process.setInitialQuantum(0);process.setRemainingQuantum(0);process.setDepartureTime(0);
                        classP.addNewProcess(process); // add process at class's queue
                        process = new Process(); // clear
                    }
                    classP.setTpClass("PS");
                    break;

                case "PWPS":
//                    System.out.print("PWPS\n");
//                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
//                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
//                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
//                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
//                        System.out.print("priority: \n");
                        gamb = scanner.nextInt();
                        process.setPriority(gamb);
                        // whatever
                        process.setInitialQuantum(0);process.setRemainingQuantum(0);process.setDepartureTime(0);
                        classP.addNewProcess(process); // add process at class's queue
                        process = new Process(); // clear
                    }
                    classP.setTpClass("PWPS");
                    break;

                case "RR":
//                    System.out.print("RR\n");
//                    System.out.print("qtProcess: \n");
                    qtProcess = scanner.nextInt();
                    for (int j = 1; j <= qtProcess; j++) {
//                        System.out.print(j + "º process\n");
                        process.setPID(pid);
                        pid++;
//                        System.out.print("CPU-burst: \n");
                        gamb = scanner.nextInt();
                        process.setInitialCpuBurst(gamb);
                        process.setRemainingCpuBurst(gamb);
//                        System.out.print("arrival time: \n");
                        gamb = scanner.nextInt();
                        process.setArrivalTime(gamb);
//                        System.out.print("quantum: \n");
                        gamb = scanner.nextInt();
                        process.setInitialQuantum(gamb);
                        process.setRemainingQuantum(gamb);
                        // whatever
                        process.setPriority(0);process.setDepartureTime(0);
                        classP.addNewProcess(process); // add process at class's queue
                        process = new Process(); // clear
                    }
                    classP.setTpClass("RR");
                    break;
            }
            mqs.add(classP);
            classP = new Class(); // clear
        } // end of data input

        System.out.println("false && false :" + (false && false));

        // execution
        System.out.println("\n-------------EXEC----------------");

        // structure that will run the schedule algorithms stored in mqs
        while(true) {

            for (int i = 0; i < qtClass; i++) { // boolean logic tha checks for new process in mqs
                exec = mqs.get(i).hasNew() && exec;
            }

            if (exec) { // has new process :: execute
                System.out.println("time = " + time);
                can = true;

                for (int i = 0; i < qtClass; i++) {
                    if (mqs.get(i).execute(time, can)) {
                        can = false;
                    }
                }

                time++;
                System.out.println(". . . . . . . . . .");
            } else {
                break; // exits from infinite loop
            }
            exec = true;
        }

        // print the final information
        System.out.println("\n-------------RESULTS----------------");
        System.out.println("Process\tClass\tWaiting\t\tProcessing");
        for (int i = 0; i < qtClass; i++) {
            mqs.get(i).printFinished();
        }
    }
}
