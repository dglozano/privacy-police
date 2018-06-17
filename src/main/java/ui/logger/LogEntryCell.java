package ui.logger;

import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

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
            setGraphic(label);
        }
    }
}