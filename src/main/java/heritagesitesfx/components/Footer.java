package heritagesitesfx.components;

import heritagesitesfx.models.HeritageSitesPM;
import heritagesitesfx.util.Translator;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.stream.Collectors;

public class Footer extends HBox implements ComponentTemplate {
    private final HeritageSitesPM model;

    private Label sitesVisited;
    private Label sitesVisitedAmount;
    private Label countriesVisited;
    private Label countriesVisitedISO;
    private Label countriesVisitedAmount;

    public Footer(HeritageSitesPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        sitesVisited = Translator.labelForKey("footer.visitedSites");
        sitesVisitedAmount = new Label("0"); // TODO not always gonna be 0 once visited works properly
        countriesVisitedISO = new Label();
        countriesVisited = Translator.labelForKey("footer.visitedCountries");
        countriesVisitedAmount = new Label("0"); // TODO not always gonna be 0 once visited works properly
    }

    @Override
    public void layoutControls() {
        Region spacer = new Region();
        getChildren().addAll(sitesVisited, sitesVisitedAmount, spacer, countriesVisitedISO, countriesVisited, countriesVisitedAmount);
        setHgrow(spacer, Priority.ALWAYS);
        sitesVisitedAmount.setPadding(new Insets(0, 0, 0, 10));
        countriesVisitedAmount.setPadding(new Insets(0, 0, 0, 10));
    }

    @Override
    public void setupValueChangedListeners() {
        model.getVisitedIsoCodes().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                sitesVisitedAmount.setText(String.valueOf(model.getVisitedSiteCount()));
                countriesVisitedISO.setText(model.getVisitedIsoCodes()
                        .stream()
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining(", ")));
                countriesVisitedAmount.setText(String.valueOf(model.getVisitedIsoCodes().stream().distinct().count()));
            }
        });
    }
}
