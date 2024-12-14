package gr.perisnik.cj.schoolapp.service.util;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
    //Set full path name of LoggerUtil
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        //Install the bridge (JUL-to-SLF) Handler
        SLF4JBridgeHandler.install();
        Handler fileHandler;
        try {
            fileHandler = new FileHandler("cj.long", true); //true for update the file
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            throw new RuntimeException();
        }

        logger.addHandler(fileHandler);
    }

    private LoggerUtil() {}

    public static Logger getCurrentLogger() {
        return logger;
    }
}
