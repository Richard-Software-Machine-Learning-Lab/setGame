package client;

/**
 * Interface for a timer of challenges.
 */
public interface ITimer {

    /**
     * Start the timer.
     */
    void startTimer();

    /**
     * Stops the timer.
     */
    void stopTimer();

    /**
     * Check if the timer is running.
     * @return true if the timer is running, false if not.
     */
    boolean isRunning();

    /**
     * Gets the current time on the clock if running, if stopped, gets teh time on the clock when stopped.
     * @return
     */
    long getTime();

    /**
     * Returns the elapsed time between starting the timer and stopping / the current time.
     * @return
     */
    long getElapsedTime();

}
