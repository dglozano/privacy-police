package ui.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase base de una entrada de log.
 */
public class LogEntry {

    public static final SimpleDateFormat df = new SimpleDateFormat("[EEE, d MMM yyyy HH:mm:ss]\n");
    private String logText;
    private Date date;

    public LogEntry() {
        date = new Date();
        logText = "";
    }

    public LogEntry(String logText) {
        date = new Date();
        this.logText = logText;
    }

    public void writeLine(String line) {
        this.logText += "\n" + line;
    }

    public void writeTitleLine(String title) {
        this.logText += "#### " + title + " #### \n";
    }

    @Override
    public String toString() {
        return df.format(date) + logText;
    }
}




