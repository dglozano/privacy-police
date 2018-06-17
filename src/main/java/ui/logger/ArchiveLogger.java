package ui.logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArchiveLogger {

    private File logFile;
    //El ListView del log esta subscripto para observar esta lista
    //por lo que cada vez que se agrega algo nuevo lo va mostrando.
    private ObservableList<LogEntry> logs;

    public ArchiveLogger(File logFile) {
        this.logFile = logFile;
        logs = FXCollections.observableArrayList();
    }

    public File getLogFile() {
        return logFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    public ObservableList<LogEntry> getObservableListLogs() {
        return logs;
    }

    public void setLogs(ObservableList<LogEntry> logs) {
        this.logs = logs;
    }

    public void addLog(LogEntry log) {
        logs.add(log);
        writeEntry(log);
    }

    private void writeEntry(LogEntry log) {
        try(FileWriter fw = new FileWriter(logFile, true)) {
            //Obtengo un writer de caracteres para mi archivo
            PrintWriter pw = new PrintWriter(fw);
            //Escribo el log (toString)
            pw.println(log);
            //Escribo Linea
            pw.println();
            //Cierro
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
