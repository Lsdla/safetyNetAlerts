package com.safetynet.safetynetalerts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication {
    /**
     * SafetyNetAlertsApplication logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(SafetyNetAlertsApplication.class);

    /**
     * A protected class constructor.
     */
    protected SafetyNetAlertsApplication() {
    }

    /**
     * The main method's role is launching the app.
     * @param args args
     */
    public static void main(final String[] args) {
        LOGGER.debug("SafetyNet alert started");
        SpringApplication.run(SafetyNetAlertsApplication.class, args);
    }

}
