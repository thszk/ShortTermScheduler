# ShortTermScheduler


This program was developed to simulate a multilevel queue scheduling, implementing the following algorithms:

- First Come First Served (FCFS)
- Shorted Job First (SJF)
- Shorted Remaining Time First (SRTF)
- Priority (PS)
- Priority with Preemption (PWPS)
- Round-Robin (RR)


### Data input

The data input are read through the text file named input.txt. To data modify you need follow the below rules:
```
Number of classes:

__1st class__
Scheduler type (most priority, case-insensitive):
Number of process in the class:

__1st process__
CPU-burst:
Arrival Time:
Priority:
Quantum:

__2nd process__
CPU-burst:
Arrival Time:
Priority:
Quantum:

[...]
__2nd class__
[...]
```
