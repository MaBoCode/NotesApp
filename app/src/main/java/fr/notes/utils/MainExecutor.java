package fr.notes.utils;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class MainExecutor implements Executor {

    protected final Queue<Runnable> tasks = new ArrayDeque<>();
    protected final Executor executor;
    protected Runnable active;

    public MainExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public synchronized void execute(Runnable command) {
        tasks.add(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}
