package lab3;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.function.Function;

/**
 *
 * @author Marcin Jeznach
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Function<String, URL> res = this.getClass()::getResource;

        Parent root = FXMLLoader.load(res.apply("Television.fxml"));
        stage.setTitle("Rubin");
//        stage.getIcons().add(new Image(res.apply("Television.fxml").getPath()));
        
        Scene scene = new Scene(root);
        
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
            }
        });
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setHeight(679);
        stage.setWidth(956);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
