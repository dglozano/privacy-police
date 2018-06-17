package ui.logger;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase base de una entrada de log.
 */
public abstract class LogEntry {

    public static final SimpleDateFormat df = new SimpleDateFormat("[EEE, d MMM yyyy HH:mm:ss]\n");

    public abstract String toString();
}
