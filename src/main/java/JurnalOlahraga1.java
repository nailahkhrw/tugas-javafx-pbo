import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class JurnalOlahraga1 extends Application { 

    private static Scene scene;

    @Override 
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Ui"), 800, 600); 
        stage.setTitle("Jurnal Olahraga Harian");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        String path = "/View/" + fxml + ".fxml"; 
        
        FXMLLoader fxmlLoader = new FXMLLoader(JurnalOlahraga1.class.getResource(path));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}