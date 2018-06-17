package ui.logger;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlainLogEntry extends LogEntry {

    private String logText;
    private Date date;

    public PlainLogEntry(String logText){
        this.logText = logText;
        date = new Date();
    }

    @Override
    public String toString() {
        return LogEntry.df.format(date) + logText;
    }
}
