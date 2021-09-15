package com.l33t_c0d3r_66.controller;

import com.l33t_c0d3r_66.model.MusicModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    @FXML
    private Slider durationSlider;
    @FXML
    private FontAwesomeIconView playIcon;

    private ObservableList<File> files = FXCollections.observableArrayList();

    private Media media;
    private MediaPlayer mediaPlayer;
    private int songNumber = 0;
    private boolean isPlaying = false;
    private boolean isStarted = false;

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
        if(songNumber - 1 > 0)
        {
            songNumber--;
            mediaPlayer.stop();
        }
        else
        {
            songNumber = files.size()-1;
            mediaPlayer.stop();
        }
        media = new Media(files.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songName.setText(files.get(songNumber).getName().substring(0, 10) + "...");
        playBtnOnClick();
    }

    @FXML
    public void forwardBtnOnClick()
    {
        if(songNumber < files.size() - 1)
        {
            songNumber++;
            mediaPlayer.stop();
        }
        else
        {
            songNumber = 0;
            mediaPlayer.stop();
        }
        media = new Media(files.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songName.setText(files.get(songNumber).getName().substring(0,10) + "...");
        playBtnOnClick();
    }

    @FXML
    public void playBtnOnClick()
    {
        if(!isStarted)
        {
            mediaPlayer.play();
            playIcon.setIcon(FontAwesomeIcon.PAUSE);
            isPlaying = true;
            isStarted = true;
        }
        if(isPlaying)
        {
            mediaPlayer.pause();
            playIcon.setIcon(FontAwesomeIcon.PLAY);
        }

        mediaPlayer.currentTimeProperty().addListener((duration, oldTime, newTime) -> {
            durationSlider.setValue(newTime.toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
            int hours = (int) mediaPlayer.getCurrentTime().toHours() % 60;
            int minutes = (int) mediaPlayer.getCurrentTime().toMinutes() % 60;
            int seconds = (int) mediaPlayer.getCurrentTime().toSeconds() % 60;
            int endHours = ((int) mediaPlayer.getTotalDuration().toHours() - hours) % 60;
            int endMinutes = ((int) mediaPlayer.getTotalDuration().toMinutes() - minutes) % 60;
            int endSeconds = ((int) mediaPlayer.getTotalDuration().toSeconds() - seconds) % 60;
            if (hours > 0) {
                startTime.setText((hours < 10 ? "0" + hours : hours) + ":"
                        + (minutes < 10 ? "0" + minutes : minutes) + ":"
                        + (seconds < 10 ? "0" + seconds : seconds));
                endTime.setText((endHours < 10 ? "0" + endHours : endHours) + ":"
                        + (endMinutes < 10 ? "0" + endMinutes : endMinutes) +":"
                        + (endSeconds < 10 ? "0" + endSeconds : endSeconds));
            } else {
                startTime.setText((minutes < 10 ? "0" + minutes : minutes) + ":"
                        + (seconds < 10 ? "0" + seconds : seconds));
                endTime.setText((endMinutes < 10 ? "0" + endMinutes: endMinutes) +":"
                        + (endSeconds < 10 ? "0" + endSeconds : endSeconds));
            }
        });
    }

    @FXML
    public void openBtnOnClick()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Please Select Your Music Directory");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if(selectedDirectory != null && selectedDirectory.isDirectory())
        {
            model.setPathToMusic(selectedDirectory.getPath());
            files.addAll(model.getAllMusicFiles());
            if(files.size() > 0 && songNumber < files.size())
            {
                media = new Media(files.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                songName.setText(files.get(songNumber).getName().substring(0,10)+"...");
                songName.setWrapText(true);
            }
        }

    }
}
