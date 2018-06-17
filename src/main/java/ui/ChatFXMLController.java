package ui;

import javafx.scene.control.SplitPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import production.system.MemoriaDeProduccion;
import production.system.Regla;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import production.system.SistemaDeProduccion;
import production.system.criteria.*;
import ui.chat.ChatMessageCell;
import ui.chat.MessagePOJO;
import ui.logger.ArchiveLogger;
import ui.logger.LogEntry;
import ui.logger.LogEntryCell;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChatFXMLController implements Initializable {

    @FXML
    private TextField msgInput;
    @FXML
    private ListView<MessagePOJO> chatListView;
    @FXML
    private ListView<LogEntry> logsListView;

    private ObservableList<MessagePOJO> mensajesChatList;
    private ArchiveLogger logger;
    private SistemaDeProduccion sistemaDeProduccion;

    private Stage primaryStage;

    @FXML
    private void send(ActionEvent event) {
        // Obtengo la frase del cartoy desde el input
        String fraseCarToy = msgInput.getText();
        msgInput.clear();

        // Si es un mensaje en blanco no lo proceso
        if(fraseCarToy.trim().isEmpty())
            return;

        // Muestro en pantalla el mensaje del cartoy
        mensajesChatList.add(new MessagePOJO(fraseCarToy, MessagePOJO.TipoMensaje.CARTOY));

        // Obtengo del sistema de producciones la respuesta del agente
        String respuestaAgente = sistemaDeProduccion.getAccionAgente(fraseCarToy);

        // Muestro la respueta del agente
        mensajesChatList.add(new MessagePOJO(respuestaAgente, MessagePOJO.TipoMensaje.RESPUESTA_AGENTE));

        //Scrollea hasta abajo cuando se agrega un mensaje
        Platform.runLater( () -> chatListView.scrollTo(mensajesChatList.size()-1));
        Platform.runLater( () -> logsListView.scrollTo(logger.getObservableListLogs().size()-1));
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Seteo ListView del chat
        mensajesChatList = FXCollections.observableArrayList();
        chatListView.setItems(mensajesChatList);
        chatListView.setCellFactory(listView -> new ChatMessageCell());

        //Selecciono un archivo donde guardar los logs y se lo seteo al ArchiveLogger
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona donde guardar el archivo de log de la simulacion");
        File logFile = fileChooser.showSaveDialog(primaryStage);
        //Si no selecciona archivo sale del programa
        if(logFile == null){
            Platform.exit();
            return;
        }
        //Si ya existe un archivo con ese nombre lo borra
        if(logFile.exists()) {
            logFile.delete();
        }
        logger = new ArchiveLogger(logFile);
        logger.addLog(new LogEntry("Iniciando simulador..."));

        //Inicializo vista logs
        logsListView.setItems(logger.getObservableListLogs());
        logsListView.setCellFactory(listView ->  new LogEntryCell());

        //Pongo foco en el input
        Platform.runLater( () -> msgInput.requestFocus());

        //Obtengo todas las reglas precargadas
        List<Regla> reglas = MemoriaDeProduccion.getReglas();

        //Creo la lista de criterias a usar. Para resolver los conflictos se usan todas.
        //Es importante el orden (NoDuplicacion es la primera en usarse y Random la ultima).
        List<Criteria> criterias = new ArrayList<>();
        criterias.add(new NoDuplication());
        criterias.add(new Priority());
        criterias.add(new Novelty());
        criterias.add(new Specifity());
        criterias.add(new Random());

        //Obtengo un SistemaDeProduccion con mis reglas y criterias. Le seteo un logger.
        sistemaDeProduccion = new SistemaDeProduccion(reglas, criterias, logger);
    }
}
