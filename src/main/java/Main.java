import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mostrarPantallaInicial();
        primaryStage.show();
    }

    private void mostrarPantallaInicial() throws IOException {
        //Le seteo un icono a la app para vender humo
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/police.png")));

        //Obtengo un nodo Parent desde el archivo FXML usando un FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/ChatFXML.fxml"));
        Parent fileChooserView = loader.load();

        //Creo la escena inicial (y unica escena en este caso) con el nodo Parent creado a partir del FXML
        Scene initScene = new Scene(fileChooserView);

        //Seteo titulo ni que no se pueda ajustar el tamaño de la ventana
        primaryStage.setTitle("Privacy Police");
        primaryStage.setResizable(false);

        //Agrego un EventListener para que si apreta ESC salga de la aplicacion
        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()){
                Platform.exit();
            }
        });

        //Seteo la escena cargadad desde el FXML al primary Stage
        primaryStage.setScene(initScene);
    }
}