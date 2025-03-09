package client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.ITimer;
import client.gamemanagement.Timer;

/**
 * Tests for Timer.
 */
public class TimerImplementationTests {
    ITimer timer;

    @BeforeEach
    public void setup() {
        timer = new Timer();
    }

    @Test
    void startTimerStartsRunningTimer() {
        this.timer.startTimer();
        Assertions.assertTrue(this.timer.isRunning());
        // Check time passes
        Assertions.assertNotNull(this.timer.getElapsedTime());
   }

    @Test
    void stopTimerStopsRunningTimer() {
        this.timer.startTimer();
        long timeBeforeStopping = this.timer.getTime();
        Assertions.assertTrue(this.timer.isRunning());
        this.timer.stopTimer();
        Assertions.assertFalse(this.timer.isRunning());
        long timeAfterStopping = this.timer.getTime();
        Assertions.assertNotEquals(timeBeforeStopping, timeAfterStopping);
        long secondTimeAfterStopping = this.timer.getTime();
        //Time not change after stopping
        Assertions.assertEquals(timeAfterStopping, secondTimeAfterStopping);
    }

}
