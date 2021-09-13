package heritagesitesfx.models;

import heritagesitesfx.util.DataHandler;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HeritageSitesPM {
    private final ObservableList<HeritageSitesDataPM> resultate = FXCollections.observableArrayList();
    private final ObservableList<String> visitedIsoCodes = FXCollections.observableArrayList();
    private final IntegerProperty visitedSiteCount = new SimpleIntegerProperty(0);
    private final IntegerProperty selectedHeritageSiteId = new SimpleIntegerProperty(-1);
    private final HeritageSitesDataPM heritageSiteProxy = new HeritageSitesDataPM();

    public HeritageSitesPM() {
        resultate.addAll(DataHandler.readFromCSVFile());
        selectedHeritageSiteIdProperty().addListener((observable, oldValue, newValue) -> {
                    HeritageSitesDataPM oldSelection = getHeritageSite(oldValue.intValue());
                    HeritageSitesDataPM newSelection = getHeritageSite(newValue.intValue());
                    if (oldSelection != null) {
                        unbindFromProxy(oldSelection);
                    }

                    if (newSelection != null) {
                        bindToProxy(newSelection);
                    }
                }
        );
    }

    private void bindToProxy(HeritageSitesDataPM heritageSite) {
        heritageSiteProxy.categoryProperty().bindBidirectional(heritageSite.categoryProperty());
        heritageSiteProxy.yearInscribedProperty().bindBidirectional(heritageSite.yearInscribedProperty());
        heritageSiteProxy.idProperty().bindBidirectional(heritageSite.idProperty());
        heritageSiteProxy.imageURLProperty().bindBidirectional(heritageSite.imageURLProperty());
        heritageSiteProxy.countryISOProperty().bindBidirectional(heritageSite.countryISOProperty());
        heritageSiteProxy.locationProperty().bindBidirectional(heritageSite.locationProperty());
        heritageSiteProxy.regionProperty().bindBidirectional(heritageSite.regionProperty());
        heritageSiteProxy.descriptionProperty().bindBidirectional(heritageSite.descriptionProperty());
        heritageSiteProxy.nameProperty().bindBidirectional(heritageSite.nameProperty());
        heritageSiteProxy.statesProperty().bindBidirectional(heritageSite.statesProperty());
        heritageSiteProxy.visitedProperty().bindBidirectional(heritageSite.visitedProperty());
    }

    private void unbindFromProxy(HeritageSitesDataPM heritageSite) {
        heritageSiteProxy.categoryProperty().unbindBidirectional(heritageSite.categoryProperty());
        heritageSiteProxy.yearInscribedProperty().unbindBidirectional(heritageSite.yearInscribedProperty());
        heritageSiteProxy.idProperty().unbindBidirectional(heritageSite.idProperty());
        heritageSiteProxy.imageURLProperty().unbindBidirectional(heritageSite.imageURLProperty());
        heritageSiteProxy.countryISOProperty().unbindBidirectional(heritageSite.countryISOProperty());
        heritageSiteProxy.locationProperty().unbindBidirectional(heritageSite.locationProperty());
        heritageSiteProxy.regionProperty().unbindBidirectional(heritageSite.regionProperty());
        heritageSiteProxy.descriptionProperty().unbindBidirectional(heritageSite.descriptionProperty());
        heritageSiteProxy.nameProperty().unbindBidirectional(heritageSite.nameProperty());
        heritageSiteProxy.statesProperty().unbindBidirectional(heritageSite.statesProperty());
        heritageSiteProxy.visitedProperty().unbindBidirectional(heritageSite.visitedProperty());
    }

    public HeritageSitesDataPM getHeritageSite(int id) {
        return resultate.stream()
                .filter(site -> site.getId() == id)
                .findAny()
                .orElse(null);
    }

    public ObservableList<HeritageSitesDataPM> getAlleResultate() {
        return resultate;
    }

    public int getSelectedHeritageSiteId() {
        return selectedHeritageSiteId.get();
    }

    public IntegerProperty selectedHeritageSiteIdProperty() {
        return selectedHeritageSiteId;
    }

    public void setSelectedHeritageSiteId(int selectedHeritageSiteId) {
        this.selectedHeritageSiteId.set(selectedHeritageSiteId);
    }

    public HeritageSitesDataPM getHeritageSiteProxy() {
        return heritageSiteProxy;
    }

    public ObservableList<String> getVisitedIsoCodes() {
        return visitedIsoCodes;
    }

    public int getVisitedSiteCount() {
        return visitedSiteCount.get();
    }

    public IntegerProperty visitedSiteCountProperty() {
        return visitedSiteCount;
    }

    public void setVisitedSiteCount(int visitedSiteCount) {
        this.visitedSiteCount.set(visitedSiteCount);
    }
}
