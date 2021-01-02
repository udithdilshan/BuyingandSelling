package lk.buyingandselling.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BuyingandSelling extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/buyingandselling/view/UserDashbord.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/lk/buyingandselling/img/cashier.png"));
            stage.show();
            stage.setMaximized(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
