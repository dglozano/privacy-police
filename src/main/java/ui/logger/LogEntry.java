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
    private boolean firstLog;

    public LogEntry() {
        date = new Date();
        firstLog = false;
        logText = "";
    }

    public LogEntry(String logText) {
        date = new Date();
        firstLog = false;
        this.logText = logText;
    }

    public void writeLine(String line) {
        this.logText += "\n" + line;
    }

    public void writeTitleLine(String title) {
        this.logText += "#### " + title + " #### \n";
    }

    public void markAsFirstLog(){
        this.firstLog = true;
    }

    public boolean isFirstLog(){
        return this.firstLog;
    }

    @Override
    public String toString() {
        return df.format(date) + logText;
    }
}




