package heritagesitesfx.components;

import heritagesitesfx.models.HeritageSitesDataPM;
import heritagesitesfx.models.HeritageSitesPM;
import heritagesitesfx.util.DataHandler;
import heritagesitesfx.util.Translator;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class Header extends HBox implements ComponentTemplate {
    private final HeritageSitesPM model;

    private ButtonType confirmButton;
    private ButtonType deleteCancelButton;

    private ButtonType englishButton;
    private ButtonType germanButton;
    private ButtonType languageCancelButton;

    private Button create;
    public Button delete;
    private Button save;
    private Button undo;
    private Button redo;
    private Button language;
    private TextField search;
    private FilteredList<HeritageSitesDataPM> filteredData;
    private TableView<HeritageSitesDataPM> table;

    public Header(HeritageSitesPM model, TableView<HeritageSitesDataPM> table) {
        this.model = model;
        this.table = table;
        init();
    }

    @Override
    public void initializeControls() {
        create = new Button();
        create.setGraphic(getIcon("plus.png"));
        delete = new Button();
        delete.setGraphic(getIcon("trash.png"));
        save = new Button();
        save.setGraphic(getIcon("save.png"));
        undo = new Button();
        undo.setGraphic(getIcon("undo.png"));
        redo = new Button();
        redo.setGraphic(getIcon("redo.png"));
        language = new Button();
        language.setGraphic(getIcon("language.png"));
        search = new TextField();
        search.setPrefWidth(250);
    }

    @Override
    public void layoutControls() {
        setMargin(search, new Insets(5, 5, 5, 5));
        // Makes sure search is always on far right
        Region spacer = new Region();
        getChildren().addAll(create, delete, save, undo, redo, language, spacer, search);
        setHgrow(spacer, Priority.ALWAYS);
    }

    @Override
    public void setupEventHandlers() {
        create.setOnAction(e -> createHeritageSite());
        delete.setOnAction(e -> deleteHeritageSite());
        save.setOnAction(e -> saveHeritageSites());
        language.setOnAction(e -> changeLanguage());
    }

    @Override
    public void setupValueChangedListeners() {
        // Wraps the ObservableList in a FilteredList (initially display all data)
        FilteredList<HeritageSitesDataPM> filteredData = new FilteredList<>(model.getAlleResultate(), p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(hs -> {
                // If filter text is empty, display all
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare name, category and country of every heritage site with filter text
                String lowerCaseFilter = newValue.toLowerCase();
                if (hs.getName().toLowerCase().contains(lowerCaseFilter) ||
                        hs.getCategory().toLowerCase().contains(lowerCaseFilter) ||
                        hs.getStates().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match
            });
        });
        // Wraps the FilteredList in a SortedList.
        SortedList<HeritageSitesDataPM> sortedData = new SortedList<>(filteredData);
        // Binds the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        // Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
    }


    private void createHeritageSite() {
        int id = model.getAlleResultate().stream().map(HeritageSitesDataPM::getId).max(Integer::compare).get() + 1;
        model.getAlleResultate().add(new HeritageSitesDataPM(id));
    }

    private void deleteHeritageSite() {
        Optional<ButtonType> result = getDeleteAlert().showAndWait();
        if (result.get() == confirmButton) {
            model.getAlleResultate().remove(model.getHeritageSite(model.getHeritageSiteProxy().getId()));
        }
    }

    private void saveHeritageSites() {
        DataHandler.writeToCSVFile(model.getAlleResultate().stream().collect(Collectors.toList()));
        getSaveAlert().showAndWait();
    }

    private void changeLanguage() {
        Optional<ButtonType> result = getLanguageAlert().showAndWait();
        if (result.get() == englishButton) {
            Translator.setLocale(Locale.ENGLISH);
        } else if (result.get() == germanButton) {
            Translator.setLocale(Locale.GERMAN);
        }
    }

    private Alert getDeleteAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/darkStyle.css").toExternalForm());
        // Belongs here in my opinion so everything is together
        alert.titleProperty().bind(Translator.createStringBinding("alert.title.delete"));
        alert.headerTextProperty().bind(Translator.createStringBinding("alert.header.delete"));
        // ButtonType does not have a property to bind to, solution would be to create custom Alert but to lazy
        confirmButton = new ButtonType(Translator.get("application.confirm"), ButtonBar.ButtonData.OK_DONE);
        deleteCancelButton = new ButtonType(Translator.get("application.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirmButton, deleteCancelButton);
        return alert;
    }

    private Alert getSaveAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/darkStyle.css").toExternalForm());
        // Belongs here in my opinion so everything is together
        alert.titleProperty().bind(Translator.createStringBinding("alert.title.save"));
        alert.headerTextProperty().bind(Translator.createStringBinding("alert.header.save"));
        // ButtonType does not have a property to bind to, solution would be to create custom Alert but to lazy
        confirmButton = new ButtonType(Translator.get("application.confirm"), ButtonBar.ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(confirmButton);
        return alert;
    }

    private Alert getLanguageAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/darkStyle.css").toExternalForm());
        // Belongs here in my opinion so everything is together
        alert.titleProperty().bind(Translator.createStringBinding("alert.title.language"));
        alert.headerTextProperty().bind(Translator.createStringBinding("alert.header.language"));
        // ButtonType does not have a property to bind to, solution would be to create custom Alert but to lazy
        englishButton = new ButtonType(Translator.get("application.english"));
        germanButton = new ButtonType(Translator.get("application.german"));
        languageCancelButton = new ButtonType(Translator.get("application.cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(englishButton, germanButton, languageCancelButton);
        return alert;
    }

    private ImageView getIcon(String icon) {
        Image img = new Image(getClass().getResource("/icons/" + icon).toExternalForm());
        ImageView view = new ImageView(img);
        view.setFitHeight(32);
        view.setPreserveRatio(true);
        return view;
    }
}
