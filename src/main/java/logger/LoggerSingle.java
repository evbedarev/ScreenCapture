package logger;
import org.apache.log4j.Logger;
public class LoggerSingle {
    private static LoggerSingle instance;
    private static Logger logger = Logger.getLogger(LoggerSingle.class);

    private LoggerSingle() {
    }

    public static LoggerSingle instance() {
        if (instance == null) {
            instance = new LoggerSingle();
        }
        return instance;
    }

    public static void logInfo(String classFrom, String message) {
        logger.info(classFrom + ": " + message);
    }

    public static void logDebug(String classFrom, String message) {
        logger.debug(classFrom + ": " + message);
    }
}
