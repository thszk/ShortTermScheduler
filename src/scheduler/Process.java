package scheduler;

public class Process {
  private int PID;
  private int initialCpuBurst;
  private int remainingCpuBurst;
  private int arrivalTime;
  private int priority;
  private int initialQuantum;
  private int remainingQuantum;
  private boolean started;

  public Process() {
    this.started = false; // all process are created with start = false by default
  }

  public int getPID() {
    return PID;
  }

  public void setPID(int PID) {
    this.PID = PID;
  }

  public int getInitialCpuBurst() {
    return initialCpuBurst;
  }

  public void setInitialCpuBurst(int initialCpuBurst) {
    this.initialCpuBurst = initialCpuBurst;
  }

  public int getRemainingCpuBurst() {
    return remainingCpuBurst;
  }

  public void setRemainingCpuBurst(int remainingCpuBurst) {
    this.remainingCpuBurst = remainingCpuBurst;
  }

  public int getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(int arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public int getInitialQuantum() {
    return initialQuantum;
  }

  public void setInitialQuantum(int initialQuantum) {
    this.initialQuantum = initialQuantum;
  }

  public int getRemainingQuantum() {
    return remainingQuantum;
  }

  public void setRemainingQuantum(int remainingQuantum) {
    this.remainingQuantum = remainingQuantum;
  }

  public boolean isStarted() {
    return started;
  }

  public void setStarted(boolean started) {
    this.started = started;
  }
}
