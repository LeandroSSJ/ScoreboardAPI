package dev.hely.scoreboard.thread;

import dev.hely.scoreboard.ScoreboardManager;

/**
 * Created By LeandroSSJ
 * Created on 22/09/2021
 */
public class ScoreboardThread extends Thread {

    private final ScoreboardManager scoreboardManager;

    public ScoreboardThread(ScoreboardManager scoreboardManager) {
        setName("Hely - Scoreboard Thread");
        setDaemon(true);

        this.scoreboardManager = scoreboardManager;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                scoreboardManager.getPlayerScoreboards().forEach((uniqueId, playerScoreboard) -> playerScoreboard.update());
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            try {
                sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
