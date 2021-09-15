package com.l33t_c0d3r_66.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;

public class MusicModel
{
    private StringProperty pathToImage = new SimpleStringProperty("");
    private StringProperty singerName = new SimpleStringProperty("");
    private StringProperty songName = new SimpleStringProperty("");
    private StringProperty startTime = new SimpleStringProperty("0:00");
    private StringProperty endTime = new SimpleStringProperty("0:00");
    private StringProperty pathToMusic = new SimpleStringProperty("");
    private ObjectProperty<Image> imageIcon = new SimpleObjectProperty<>();

    public String getPathToMusic() {
        return pathToMusic.get();
    }

    public StringProperty pathToMusicProperty() {
        return pathToMusic;
    }

    public void setPathToMusic(String pathToMusic) {
        this.pathToMusic.set(pathToMusic);
    }

    public Image getImageIcon() {
        return imageIcon.get();
    }

    public ObjectProperty<Image> imageIconProperty() {
        return imageIcon;
    }

    public void setImageIcon(Image imageIcon) {
        this.imageIcon.set(imageIcon);
    }

    public MusicModel()
    {
        pathToImage.set("src/main/resources/icons/itunes.png");
        try {
            imageIcon.setValue(new Image(new File(pathToImage.getValue()).toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getPathToImage() {
        return pathToImage.get();
    }

    public StringProperty pathToImageProperty() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage.set(pathToImage);
    }

    public String getSingerName() {
        return singerName.get();
    }

    public StringProperty singerNameProperty() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName.set(singerName);
    }

    public String getSongName() {
        return songName.get();
    }

    public StringProperty songNameProperty() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName.set(songName);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public ObservableList<File> getAllMusicFiles()
    {
        ObservableList<File> listOfMusicFiles = FXCollections.observableArrayList();
        try {
            File rootFolder = new File(pathToMusic.getValue());
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (!file.isDirectory() && file.getName().endsWith(".mp3")) {
                    listOfMusicFiles.add(file);
                }
            }
            return listOfMusicFiles;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
