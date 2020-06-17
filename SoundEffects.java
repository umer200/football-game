package sample;


import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Time;
import java.util.Collection;

import static java.awt.SystemColor.info;

public class SoundEffects extends Application{

    public void start(Stage stage)
    {

    }



    static MediaPlayer mP;
    //giving background music to the game
    static void backgroundtheme()
    {

        String s="src\\sample\\theme.mp3";
        Media m=new Media(new File(s).toURI().toString());
        mP =new MediaPlayer(m);
        mP.setAutoPlay(true);

    }

    static void themepause() {
        mP.stop();
    }

    public static void goal()
    {
        String s="src\\sample\\goalsound.mp3";
        Media m=new Media(new File(s).toURI().toString());
        MediaPlayer mP=new MediaPlayer(m);
        mP.play();
    }

    public static void whistle()
    {
        String s="src\\sample\\whistle.mp3";
        Media m=new Media(new File(s).toURI().toString());
        MediaPlayer mP=new MediaPlayer(m);
        mP.play();
    }

    public static void penaltyMiss()
    {
        String s="src\\sample\\booh.wav";
        Media m=new Media(new File(s).toURI().toString());
        MediaPlayer mP=new MediaPlayer(m);
        mP.play();
    }

    public static void kickSound()
    {
        String s="src\\sample\\kicksound.wav";
        Media m=new Media(new File(s).toURI().toString());
        MediaPlayer mP=new MediaPlayer(m);
        mP.play();

    }

//    public static void main(String[] args)
//    {
//
//     launch(args);
//
//    }

}




