package com.l33t_c0d3r_66.controller;

import com.l33t_c0d3r_66.model.MusicModel;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MusicPlayerController extends AbstractController<MusicModel>
{
    @FXML
    private Label singerName;
    @FXML
    private Label songName;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private ImageView imageView;

    private ObservableList<File> files = FXCollections.observableArrayList();

    public MusicPlayerController(MusicModel model)
    {
        super(model);
    }

    @Override
    public void addBinding() {
        singerName.textProperty().bindBidirectional(model.singerNameProperty());
        songName.textProperty().bindBidirectional(model.songNameProperty());
        startTime.textProperty().bindBidirectional(model.startTimeProperty());
        endTime.textProperty().bindBidirectional(model.endTimeProperty());
        imageView.imageProperty().bindBidirectional(model.imageIconProperty());
    }

    @Override
    public void addComponents() {

    }

    @Override
    public void initializeComponents() {

    }

    @FXML
    public void backBtnOnClick()
    {
        System.out.println("Hello Back");
    }

    @FXML
    public void forwardBtnOnClick()
    {
        System.out.println("Hello Forward");
    }

    @FXML
    public void playBtnOnClick()
    {
        System.out.println("Hello Play");
    }

    @FXML
    public void openBtnOnClick()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Please Select Your Music Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if(selectedDirectory!=null && selectedDirectory.isDirectory())
        {
            model.setPathToMusic(selectedDirectory.getPath());
            files.addAll(model.getAllMusicFiles());
        }

    }
}
