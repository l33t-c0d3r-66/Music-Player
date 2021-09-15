package com.l33t_c0d3r_66;

import com.l33t_c0d3r_66.controller.MusicPlayerController;
import com.l33t_c0d3r_66.model.MusicModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MusicPlayer extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/MusicPlayer.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        MusicModel model = new MusicModel();
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> aClass) {
                return new MusicPlayerController(model);
            }
        });
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("/stylesheets/stylesheet.css");
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(450);
        primaryStage.setMaxHeight(500);
        primaryStage.setMaxWidth(750);
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
