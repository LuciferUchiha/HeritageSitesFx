package heritagesitesfx.models;

import javafx.beans.property.*;

public class HeritageSitesDataPM {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty category = new SimpleStringProperty();
    private IntegerProperty yearInscribed = new SimpleIntegerProperty();
    private StringProperty states = new SimpleStringProperty();
    private StringProperty countryISO = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty region = new SimpleStringProperty();
    private StringProperty imageURL = new SimpleStringProperty();
    private BooleanProperty visited = new SimpleBooleanProperty();

    // For Proxy
    public HeritageSitesDataPM() {
    }

    // For create
    public HeritageSitesDataPM(int id) {
        setId(id);
    }

    // For reading from file
    public HeritageSitesDataPM(String[] tokens) {
        setCategory(tokens[0]);
        setYearInscribed(Integer.parseInt(tokens[1]));
        setId(Integer.parseInt(tokens[2]));
        setImageURL(tokens[3]);
        setCountryISO(tokens[4]);
        setLocation(tokens[5]);
        setRegion(tokens[6]);
        setDescription(tokens[7]);
        setName(tokens[8]);
        setStates(tokens[9]);
        setVisited(Boolean.parseBoolean(tokens[10]));
    }

    public String infoAsLine(String delimiter) {
        StringBuilder info = new StringBuilder();
        info.append(getCategory()).append(delimiter);
        info.append(getYearInscribed()).append(delimiter);
        info.append(getId()).append(delimiter);
        info.append(getImageURL()).append(delimiter);
        info.append(getCountryISO()).append(delimiter);
        info.append(getLocation()).append(delimiter);
        info.append(getRegion()).append(delimiter);
        info.append(getDescription()).append(delimiter);
        info.append(getName()).append(delimiter);
        info.append(getStates()).append(delimiter);
        if (visited.get())
            info.append("yes");
        else
            info.append("no");
        return info.toString();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getYearInscribed() {
        return yearInscribed.get();
    }

    public IntegerProperty yearInscribedProperty() {
        return yearInscribed;
    }

    public void setYearInscribed(int yearInscribed) {
        this.yearInscribed.set(yearInscribed);
    }

    public String getStates() {
        return states.get();
    }

    public StringProperty statesProperty() {
        return states;
    }

    public void setStates(String states) {
        this.states.set(states);
    }

    public String getCountryISO() {
        return countryISO.get();
    }

    public StringProperty countryISOProperty() {
        return countryISO;
    }

    public void setCountryISO(String countryISO) {
        this.countryISO.set(countryISO);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

    public String getImageURL() {
        return imageURL.get();
    }

    public StringProperty imageURLProperty() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL.set(imageURL);
    }

    public boolean isVisited() {
        return visited.get();
    }

    public BooleanProperty visitedProperty() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited.set(visited);
    }
}
