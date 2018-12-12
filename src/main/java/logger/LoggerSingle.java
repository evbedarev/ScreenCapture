package logger;
import org.apache.log4j.Logger;
public class LoggerSingle {
    private static LoggerSingle instance;
    private static Logger logger = Logger.getLogger(LoggerSingle.class);
    private static StringBuilder msg = new StringBuilder();

    private LoggerSingle() {
    }

    public static LoggerSingle instance() {
        if (instance == null) {
            instance = new LoggerSingle();
        }
        return instance;
    }

    public static void logInfo(String classFrom, String message) {
        msg.delete(0, msg.length());
        msg.append(classFrom);
        msg.append(": ");
        msg.append(message);
        logger.info(msg);
    }

    public static void logDebug(String classFrom, String message) {
        msg.delete(0, msg.length());
        msg.append(classFrom);
        msg.append(": ");
        msg.append(message);
        logger.debug(msg);
    }
}
