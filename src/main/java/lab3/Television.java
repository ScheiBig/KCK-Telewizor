package lab3;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 *
 * @author Marcin Jeznach
 */
public class Television implements Initializable {
    
    @FXML
    private Pane channelKnob;
    @FXML
    private Pane valueKnob;
    @FXML
    private ToggleButton powerSwitch;
    @FXML
    private ImageView tvOff;
    @FXML
    MediaView mediaMaster = new MediaView();
    @FXML
    Slider volumeSlider = new Slider();
    
    boolean power = false;
    boolean channelC = false;
    boolean channelR = false;
    boolean channelN = false;
    
    Media wiadomosciCeny;
    Media wiadomosciRozpad;
    Media noChannel;
    MediaPlayer playerWiadC;
    MediaPlayer playerWiadR;
    MediaPlayer emptyScreen;

    public Television() throws MalformedURLException {
    }

    @FXML
    private void togglePower(MouseEvent event) {
        
        if (powerSwitch.isSelected()) {
            tvOff.setVisible(false);
            tvOff.setManaged(false);
            power = true;
            playerWiadC.setMute(!(power && channelC));
            playerWiadR.setMute(!(power && channelR));
            emptyScreen.setMute(!(power && channelN));
            
        } else {
            //tvOff.setVisible(false);
            //tvOff.setManaged(false);
            tvOff.setVisible(true);
            tvOff.setManaged(true);
            power = false;
            playerWiadC.setMute(!(power && channelC));
            playerWiadR.setMute(!(power && channelR));
            emptyScreen.setMute(!(power && channelN));
        }
    }
    
    static boolean isBetween(int a, int b, int c) {
        return a > b && a <= c;
    }
    
    static double fixAtan2(double d) {
        return d + (d < 0 ? 1 : 0) * 360 - (d >= 360 ? 1 : 0) * 360;
    }
    
    static int getKnobRotationOnEvent(Pane knob, MouseEvent event) {
        
        int dx = (int) (event.getSceneX() - knob.getLayoutX() - (knob.getPrefWidth() / 2));
        int dy = (int) (event.getSceneY() - knob.getLayoutY() - (knob.getPrefHeight() / 2));
        double ddeg = Math.atan2(dy, dx) * 180 / Math.PI + 90;
        ddeg = fixAtan2(ddeg);
        return (int) ddeg;
    }
    
    @FXML
    private void channelKnobRotate(MouseEvent event) {
        
        int deg = getKnobRotationOnEvent(channelKnob, event);
        int finalDeg;
        
        if (isBetween(deg, 0, 15) || isBetween(deg, 345, 360)) {
            finalDeg = 0;
        } else if (isBetween(deg, 15, 45)) {
            finalDeg = 30;
        } else if (isBetween(deg, 45, 75)) {
            finalDeg = 60;
        } else if (isBetween(deg, 75, 105)) {
            finalDeg = 90;
        } else if (isBetween(deg, 105, 135)) {
            finalDeg = 120;
        } else if (isBetween(deg, 135, 165)) {
            finalDeg = 150;
        } else if (isBetween(deg, 165, 195)) {
            finalDeg = 180;
        } else if (isBetween(deg, 195, 225)) {
            finalDeg = 210;
        } else if (isBetween(deg, 225, 255)) {
            finalDeg = 240;
        } else if (isBetween(deg, 255, 285)) {
            finalDeg = 270;
        } else if (isBetween(deg, 285, 315)) {
            finalDeg = 300;
        } else if (isBetween(deg, 315, 345)) {
            finalDeg = 330;
        } else {
            finalDeg = 0;
        }
        
        channelKnob.setStyle("-fx-rotate:" + finalDeg);
        
        if (finalDeg == 210) {
            channelR = false;
            channelC = true;
            channelN = false;
            playerWiadR.setMute(!(power && channelR));
            mediaMaster.setMediaPlayer(playerWiadC);
            playerWiadC.setMute((!power && channelC));
            emptyScreen.setMute(!(power && channelN));
        } else if (finalDeg == 330 ) {
            channelR = true;
            channelC = false;
            channelN = false;
            playerWiadC.setMute(!(power && channelC));
            mediaMaster.setMediaPlayer(playerWiadR);
            playerWiadR.setMute(!(power && channelR));
            emptyScreen.setMute(!(power && channelN));
        } else {
            channelR = false;
            channelC = false;
            channelN = true;
            playerWiadC.setMute(!(power && channelC));
            playerWiadR.setMute(!(power && channelR));
            mediaMaster.setMediaPlayer(emptyScreen);
            emptyScreen.setMute(!(power && channelN));
        }
    }
    
    @FXML
    private void valueKnobRotate(MouseEvent event) {
        
        int deg = getKnobRotationOnEvent(valueKnob, event);
        int finalDeg;
        
        if (isBetween(deg, 0, 15) || isBetween(deg, 345, 360)) {
            finalDeg = 0;
        } else if (isBetween(deg, 15, 45)) {
            finalDeg = 30;
        } else if (isBetween(deg, 45, 75)) {
            finalDeg = 60;
        } else if (isBetween(deg, 75, 105)) {
            finalDeg = 90;
        } else if (isBetween(deg, 105, 180)) {
            finalDeg = 120;
        } else if (isBetween(deg, 180, 255)) {
            finalDeg = 240;
        } else if (isBetween(deg, 255, 285)) {
            finalDeg = 270;
        } else if (isBetween(deg, 285, 315)) {
            finalDeg = 300;
        } else if (isBetween(deg, 315, 345)) {
            finalDeg = 330;
        } else {
            finalDeg = 0;
        }
        
        valueKnob.setStyle("-fx-rotate:" + Integer.toString(finalDeg));
    }

    private URL resource(String name) {
        return this.getClass().getResource(name);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        wiadomosciCeny = new Media(resource("image/wiadomosci_ceny_z_kosmosu.mp4").toString());
        wiadomosciRozpad = new Media(resource("image/wiadomosci_rozpad_zsrr.mp4").toString());
        noChannel = new Media(resource("image/blank.mp4").toString());


        playerWiadC = new MediaPlayer(wiadomosciCeny);
        playerWiadR = new MediaPlayer(wiadomosciRozpad);
        emptyScreen = new MediaPlayer(noChannel);
        
        mediaMaster.setMediaPlayer(emptyScreen);
        
        playerWiadC.setStartTime(Duration.seconds(0));
        playerWiadC.setStopTime(Duration.seconds(258));
        playerWiadR.setStartTime(Duration.seconds(0));
        playerWiadR.setStopTime(Duration.seconds(297));
        emptyScreen.setStartTime(Duration.seconds(2));
        emptyScreen.setStopTime(Duration.seconds(8));
        
        playerWiadC.setCycleCount(MediaPlayer.INDEFINITE);
        playerWiadR.setCycleCount(MediaPlayer.INDEFINITE);
        emptyScreen.setCycleCount(MediaPlayer.INDEFINITE);
        playerWiadC.play();
        playerWiadR.play();
        emptyScreen.play();
        
        playerWiadC.setMute(true);
        playerWiadR.setMute(true);
        emptyScreen.setMute(true);
        channelN = true;
        playerWiadC.setVolume(0.3);
        playerWiadR.setVolume(0.3);
        emptyScreen.setVolume(0.075);
        
        volumeSlider.setMin(0);
        volumeSlider.setMax(1);
        volumeSlider.setValue(0.3);
        volumeSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {

                playerWiadC.setVolume(volumeSlider.getValue());
                playerWiadR.setVolume(volumeSlider.getValue());
                emptyScreen.setVolume(volumeSlider.getValue()*0.25);
            }
        });
    }
}
