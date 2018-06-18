package ui.logger;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LogEntryCell extends ListCell<LogEntry> {

    @Override
    public void updateItem(LogEntry logEntry, boolean empty) {
        super.updateItem(logEntry, empty);
        Label label = new Label();
        label.setWrapText(true);
        label.maxWidthProperty().bind(Bindings.createDoubleBinding(
                    () -> getWidth() - getPadding().getLeft() - getPadding().getRight() - 1,
                    widthProperty(), paddingProperty()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        if (empty) {
            setGraphic(null);
        } else {
            label.setText(logEntry.toString());
            if(logEntry.isFirstLog()) {
                label.setFont(Font.font("Montserrat-Black", FontWeight.BOLD,14.0));
            }
            setGraphic(label);
        }
    }
}