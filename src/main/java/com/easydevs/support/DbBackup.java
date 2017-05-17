package com.easydevs.support;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

import static java.util.concurrent.TimeUnit.*;
import java.io.IOException;
import java.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DbBackup {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostConstruct
    public void setBackup() {
        final Runnable backup = new Runnable() {
            @Override
            public void run() {
                log.info("Backup database...");
                try {
                    Runtime.getRuntime().exec("mongodump --out ~/dbBackup");
                    log.info("Database backed up successfully!");
                } catch (IOException ex) {
                    log.info("Could not back up database!");
                }
            }
        };
        final ScheduledFuture<?> backupHandle = scheduler.scheduleAtFixedRate(backup, 1, 1, MINUTES);
    }

    public void restore() {
        try {
            Runtime.getRuntime().exec("mongorestore dbBackup");
        } catch (IOException ex) {
            log.info("Could not restore database!");
        }
    }
}
