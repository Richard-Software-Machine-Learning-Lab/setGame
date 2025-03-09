package client.gamemanagement;


import client.ITimer;

/**
 * Implementation of a {@link ITimer}.
 */
public class Timer implements ITimer {
  boolean isRunning = false;
  long startTime;
  long endTime;


  @Override
  public void startTimer() {
    this.startTime = System.currentTimeMillis();
    isRunning = true;
  }

  @Override
  public void stopTimer() {
    this.endTime = System.currentTimeMillis();
    this.isRunning = false;
  }

  @Override
  public long getElapsedTime() {
    if (isRunning) {
      return (System.currentTimeMillis() - startTime);
    } else {
      return (endTime - startTime);
    }
  }

  public long getTime() {
    return getElapsedTime();
  }

  @Override
  public boolean isRunning() {
    return this.isRunning;
  }
}
