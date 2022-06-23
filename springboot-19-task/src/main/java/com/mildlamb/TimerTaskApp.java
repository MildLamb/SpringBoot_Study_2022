package com.mildlamb;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskApp {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("千珏珏珏子...");
            }
        };
        timer.schedule(task,0,2000);
    }
}
