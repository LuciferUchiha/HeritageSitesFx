package heritagesitesfx;

import heritagesitesfx.components.HeritageSitesUI;
import heritagesitesfx.models.HeritageSitesPM;
import heritagesitesfx.util.Translator;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HeritageSitesApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        HeritageSitesPM model = new HeritageSitesPM();

        Parent rootPanel = new HeritageSitesUI(model);

        Scene scene = new Scene(rootPanel);

        primaryStage.titleProperty().bind(Translator.createStringBinding("application.title"));
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1600);
        primaryStage.setMinHeight(1000);

        primaryStage.show();

        model.setSelectedHeritageSiteId(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}