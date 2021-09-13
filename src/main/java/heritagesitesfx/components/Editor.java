package heritagesitesfx.components;

import heritagesitesfx.models.HeritageSitesDataPM;
import heritagesitesfx.models.HeritageSitesPM;
import heritagesitesfx.util.Translator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class Editor extends GridPane implements ComponentTemplate {
    private final HeritageSitesPM model;

    private Label titleField;
    private Label nameLabel;
    private TextField nameField;
    private Label yearInscribedLabel;
    private TextField yearInscribedField;
    private Label categoryLabel;
    private TextField categoryField;
    private Label locationLabel;
    private TextField locationField;
    private Label regionLabel;
    private TextField regionField;
    private Label statesLabel;
    private TextField statesField;
    private Label descriptionLabel;
    private TextArea descriptionArea;
    private Label isoCodeLabel;
    private TextField isoCodeField;
    private Label imageURLLabel;
    private TextField imageURLField;
    private Label visitedLabel;
    private CheckBox visitedCheckbox;


    public Editor(HeritageSitesPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        titleField = new Label();
        titleField.getStyleClass().add("editor-title");
        nameLabel = Translator.labelForKey("application.name");
        nameField = new TextField();
        yearInscribedLabel = Translator.labelForKey("application.yearsInscribed");
        yearInscribedField = new TextField();
        categoryLabel = Translator.labelForKey("application.category");
        categoryField = new TextField();
        locationLabel = Translator.labelForKey("application.location");
        locationField = new TextField();
        regionLabel = Translator.labelForKey("application.region");
        regionField = new TextField();
        statesLabel = Translator.labelForKey("application.states");
        statesField = new TextField();
        descriptionLabel = Translator.labelForKey("application.shortDescription");
        descriptionArea = new TextArea();
        isoCodeLabel = Translator.labelForKey("application.isoCode");
        isoCodeField = new TextField();
        imageURLLabel = Translator.labelForKey("application.imageUrl");
        imageURLField = new TextField();
        visitedLabel = Translator.labelForKey("application.visited");
        visitedCheckbox = new CheckBox();
    }

    @Override
    public void layoutControls() {
        add(titleField, 0, 0, 2, 1);
        add(nameLabel, 0, 1);
        add(nameField, 1, 1);
        add(yearInscribedLabel, 0, 2);
        add(yearInscribedField, 1, 2);
        add(categoryLabel, 0, 3);
        add(categoryField, 1, 3);
        add(locationLabel, 0, 4);
        add(locationField, 1, 4);
        add(regionLabel, 0, 5);
        add(regionField, 1, 5);
        add(statesLabel, 0, 6);
        add(statesField, 1, 6);
        add(descriptionLabel, 0, 7);
        add(descriptionArea, 1, 7);
        add(isoCodeLabel, 0, 8);
        add(isoCodeField, 1, 8);
        add(imageURLLabel, 0, 9);
        add(imageURLField, 1, 9);
        add(visitedLabel, 0, 10);
        add(visitedCheckbox, 1, 10);

        for (Node node : getChildren()) {
            setMargin(node, new Insets(5, 5, 5, 5));
        }
    }

    @Override
    public void setupValueChangedListeners() {
        visitedCheckbox.setOnAction(e -> {
            String[] isoCodes = model.getHeritageSiteProxy().getCountryISO().split(",");
            if (visitedCheckbox.isSelected()) {
                model.setVisitedSiteCount(model.getVisitedSiteCount() + 1);
                for (String isoCode : isoCodes) {
                    model.getVisitedIsoCodes().add(isoCode.strip());
                }
            } else {
                model.setVisitedSiteCount(model.getVisitedSiteCount() - 1);
                for (String isoCode : isoCodes) {
                    model.getVisitedIsoCodes().remove(isoCode.strip());
                }
            }
        });
    }

    @Override
    public void setupBindings() {
        HeritageSitesDataPM proxy = model.getHeritageSiteProxy();
        titleField.textProperty().bind(proxy.nameProperty().concat(" - ").concat(proxy.yearInscribedProperty()));
        nameField.textProperty().bindBidirectional(proxy.nameProperty());
        yearInscribedField.textProperty().bindBidirectional(proxy.yearInscribedProperty(), new NumberStringConverter());
        categoryField.textProperty().bindBidirectional(proxy.categoryProperty());
        locationField.textProperty().bindBidirectional(proxy.locationProperty());
        regionField.textProperty().bindBidirectional(proxy.regionProperty());
        statesField.textProperty().bindBidirectional(proxy.statesProperty());
        descriptionArea.textProperty().bindBidirectional(proxy.descriptionProperty());
        isoCodeField.textProperty().bindBidirectional(proxy.countryISOProperty());
        imageURLField.textProperty().bindBidirectional(proxy.imageURLProperty());
        visitedCheckbox.selectedProperty().bindBidirectional(proxy.visitedProperty());
    }
}
