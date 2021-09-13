package heritagesitesfx.components;

import heritagesitesfx.models.HeritageSitesDataPM;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class CountryTableCell extends javafx.scene.control.TableCell<HeritageSitesDataPM, String> {
    private final Map<String, Image> countries = new HashMap<>();

    private final Insets insets = new Insets(1, 8, 1, 5);

    @Override
    protected void updateItem(String item, boolean empty) {
        setText("");
        setGraphic(null);
        if (item != null && !empty && item.length() >= 2) {
            Image img = countries.get(item);
            if (img == null) {
                if (item.contains(",")) {
                    String[] countryTokens = item.split(",");
                    for (String country : countryTokens) {
                        country = country.strip();
                        img = getImage(country);
                        countries.put(country, img);
                    }
                } else {
                    img = getImage(item);
                    countries.put(item, img);
                }
            }

            ImageView imageView = new ImageView(img);

            setGraphic(imageView);
            setTooltip(new Tooltip(item));
            setAlignment(Pos.CENTER);
            setPadding(insets);
        }

    }

    private Image getImage(String country) {
        return new Image(getClass().getResource("/icons/countries/" + country + ".png")
                .toExternalForm(), 32, 32, true, true, true);
    }
}

