package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {


    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        instance=this;
        this.primaryStage=primaryStage;
        Locale.setDefault(new Locale("gb"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"),bundle);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("sample.fxml"));
        loader.setResources(bundle);
        this.primaryStage.getIcons().add(new Image("/sample/plecak.jpg"));
        this.primaryStage.setTitle(bundle.getString("titleApp"));
        this.primaryStage.setScene(new Scene(root, 640, 542));
        this.primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void updateTitle(String title) {
        primaryStage.setTitle(title);
    }
}
