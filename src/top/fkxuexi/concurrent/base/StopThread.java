package top.fkxuexi.concurrent.base;

import javafx.scene.paint.Stop;

public class StopThread {

    public static void main(String[] args) {
        StopThread stopThread = new StopThread();
        stopThread.stopByInterrupted();
        //stopThread.isStopped();
    }

    public void stopByInterrupted() {
        ThreadInterrupt threadStopTest = new ThreadInterrupt();
        threadStopTest.start();
    }

    public void isStopped() {
        try {
            ThreadException threadException = new ThreadException();
            threadException.start();
            Thread.sleep(1 / 100);
            threadException.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadInterrupt extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.isInterrupted());
            if (this.isInterrupted()) {
                this.interrupted();
                System.out.println(this.isInterrupted());
                continue;
            }
            System.out.println(i);
            if (i == 50) {
                this.interrupt();
            }


        }
    }
}

class ThreadException extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                if (this.isInterrupted()) {
                    throw new InterruptedException();
                }
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我还是能输出");
    }
}