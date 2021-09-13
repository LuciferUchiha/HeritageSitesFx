package heritagesitesfx.components;

import heritagesitesfx.models.HeritageSitesDataPM;
import heritagesitesfx.models.HeritageSitesPM;
import heritagesitesfx.util.Translator;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

public class HeritageSitesUI extends BorderPane implements ComponentTemplate {
    private final HeritageSitesPM model;

    private SplitPane splitPane;
    private TableView<HeritageSitesDataPM> table;
    private Editor editor;
    private Header header;
    private Footer footer;

    public HeritageSitesUI(HeritageSitesPM model) {
        this.model = model;
        init();
        addStylesheetFiles("/darkStyle.css");
    }

    @Override
    public void initializeControls() {
        table = initializeResultatTabelle();
        // Must be below table otherwise table is null
        header = new Header(model, table);

        editor = new Editor(model);
        splitPane = new SplitPane();
        splitPane.getItems().addAll(table, editor);
        splitPane.setDividerPosition(0, 0.6);
        footer = new Footer(model);
    }

    @Override
    public void layoutControls() {
        setTop(header);
        setCenter(splitPane);
        setBottom(footer);
    }

    @Override
    public void setupValueChangedListeners() {
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                model.setSelectedHeritageSiteId(newValue.getId());
        });
    }

    private TableView<HeritageSitesDataPM> initializeResultatTabelle() {
        // Data gets added in Header.java so that it can be filtered
        TableView<HeritageSitesDataPM> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<HeritageSitesDataPM, String> nameCol = new TableColumn<>();
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.textProperty().bind(Translator.createStringBinding("application.name"));

        TableColumn<HeritageSitesDataPM, String> categoryCol = new TableColumn<>();
        categoryCol.setCellValueFactory(cell -> cell.getValue().categoryProperty());
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.textProperty().bind(Translator.createStringBinding("application.category"));

        TableColumn<HeritageSitesDataPM, String> countryCol = new TableColumn<>();
        countryCol.setCellValueFactory(cell -> cell.getValue().countryISOProperty());
        countryCol.setCellFactory(param -> new CountryTableCell());
        countryCol.textProperty().bind(Translator.createStringBinding("application.country"));

        TableColumn<HeritageSitesDataPM, String> statesCol = new TableColumn<>();
        statesCol.setCellValueFactory(cell -> cell.getValue().statesProperty());
        statesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statesCol.textProperty().bind(Translator.createStringBinding("application.states"));

        // StatesCol width now always fills the table
        DoubleBinding usedWidth = nameCol.widthProperty()
                .add(categoryCol.widthProperty())
                .add(countryCol.widthProperty().add(18));
        statesCol.prefWidthProperty().bind(tableView.widthProperty().subtract(usedWidth));

        tableView.getColumns().addAll(nameCol, categoryCol, countryCol, statesCol);
        return tableView;
    }
}
