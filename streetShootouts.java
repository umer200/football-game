/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

//import javafx.animation.TimelineBuilder;
//import javafx.animation.TranslateTransitionBuilder;


public class streetShootouts extends Application {

    //to keep user's turn record
    static int turns=1;

    static int i=1;

    //For keeping highscore of player
    static int hScore=HighScoreManaging.ReadingFromFile();

    //to know about player's turn whether it's first turn after opening game or other than that which helps in managing highscore
    static int turn=1;



    //loading all images
    final static Image MAN_1 =  new Image(streetShootouts.class.getResource("player1.png").toString());
    final static Image MAN_2 =  new Image(streetShootouts.class.getResource("player2.png").toString());
    final static Image MAN_3 =  new Image(streetShootouts.class.getResource("player3.png").toString());
    final static Image MAN_4 =  new Image(streetShootouts.class.getResource("player4.png").toString());
    final static Image MAN_5 =  new Image(streetShootouts.class.getResource("dust1.png").toString());
    final static Image BALL =  new Image(streetShootouts.class.getResource("ball.png").toString());
    final static Image DEFAULT =  new Image(streetShootouts.class.getResource("default.png").toString());
    final static Image DLEFT =  new Image(streetShootouts.class.getResource("dleft.png").toString());
    final static Image DLEFT2 =  new Image(streetShootouts.class.getResource("dleft2.png").toString());
    final static Image ULEFT =  new Image(streetShootouts.class.getResource("uleft.png").toString());
    final static Image ULEFT2 =  new Image(streetShootouts.class.getResource("uleft2.png").toString());
    final static Image UP =  new Image(streetShootouts.class.getResource("up.png").toString());
    final static Image DOWN =  new Image(streetShootouts.class.getResource("down.png").toString());
    final static Image DRIGHT =  new Image(streetShootouts.class.getResource("dright.png").toString());
    final static Image DRIGHT2 =  new Image(streetShootouts.class.getResource("dright2.png").toString());
    final static Image URIGHT =  new Image(streetShootouts.class.getResource("uright.png").toString());
    final static Image URIGHT2 =  new Image(streetShootouts.class.getResource("uright2.png").toString());
    final static Image GROUND =  new Image(streetShootouts.class.getResource("ground.png").toString());
    Image UPBT = new Image(getClass().getResourceAsStream("upBT.png"));
    Image DOWNBT = new Image(getClass().getResourceAsStream("downBT.png"));
    Image ULBT = new Image(getClass().getResourceAsStream("ulBT.png"));
    Image URBT = new Image(getClass().getResourceAsStream("urBT.png"));
    Image DLBT = new Image(getClass().getResourceAsStream("dlBT.png"));
    Image DRBT = new Image(getClass().getResourceAsStream("drBT.png"));





    //Stage and scene declaration
    Stage window;
    Scene scene1, scene2;

    //for storing goalie dive position
    int randomGK;

    //generate random number for the GK
    public int getRandom(int min,int max){
        Random random = new Random();
        return random.nextInt(max - min)+ min;
    }



    //Group declaration
    private Group man;
    private Group ballGroup;
    private Group GK;
    private Group groundgrp;

    //Button Declaration
    Button dLeftBT = new Button();
    Button dRightBT = new Button();
    Button uRightBT = new Button();
    Button uLeftBT = new Button();
    Button upBT = new Button();
    Button downBT= new Button();

    int Option=0; //option selected by the user
    private static int score=0; //to keep record of the user score

    public int getScore() {
        return score;
    } //getter for the score variable

    //convert images to ImageViews
    final ImageView man1 = new ImageView(MAN_1);
    final ImageView man2 = new ImageView(MAN_2);
    final ImageView man3 = new ImageView(MAN_3);
    final ImageView man4 = new ImageView(MAN_4);
    final ImageView man5 = new ImageView(MAN_5);
    final ImageView ball = new ImageView(BALL);
    final ImageView uleft = new ImageView(ULEFT);
    final ImageView uleft2 = new ImageView(ULEFT2);
    final ImageView up = new ImageView(UP);
    final ImageView dleft = new ImageView(DLEFT);
    final ImageView dleft2= new ImageView(DLEFT2);
    final ImageView dright = new ImageView(DRIGHT);
    final ImageView dright2 = new ImageView(DRIGHT2);
    final ImageView uright = new ImageView(URIGHT);
    final ImageView uright2 = new ImageView(URIGHT2);
    final ImageView def = new ImageView(DEFAULT);
    final ImageView ground=new ImageView(GROUND);
    final ImageView down = new ImageView(DOWN);



    //disables all buttons
    public void disableButtons(){
        uLeftBT.setOpacity(0);
        uRightBT.setOpacity(0);
        dLeftBT.setOpacity(0);
        dRightBT.setOpacity(0);
        upBT.setOpacity(0);
        dLeftBT.setDisable(true);
        uLeftBT.setDisable(true);
        dRightBT.setDisable(true);
        uRightBT.setDisable(true);
        upBT.setDisable(true);
    }

    //enables all buttons
    public void enableButtons(){
//        uLeftBT.setOpacity(100);
//        uRightBT.setOpacity(100);
//        dLeftBT.setOpacity(100);
//        dRightBT.setOpacity(100);
//        upBT.setOpacity(100);
        dLeftBT.setDisable(false);
        uLeftBT.setDisable(false);
        dRightBT.setDisable(false);
        uRightBT.setDisable(false);
        upBT.setDisable(false);
    }







    //start method of application class
    @Override
    public void start(Stage primaryStage) throws IOException {

        window=primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(streetShootouts.class.getResource("score.fxml")); //loads the fxml file
        Parent gameOver = loader.load();
        Adeel.Controller controller = loader.getController();

        //hint
        FadeTransition upBThint = new FadeTransition(Duration.seconds(2));
        upBThint.setNode(upBT);
        upBThint.setFromValue(1);
        upBThint.setToValue(0);
        upBThint.setDelay(Duration.seconds(2));
        upBThint.play();

        FadeTransition downBThint = new FadeTransition(Duration.seconds(2));
        downBThint.setNode(downBT);
        downBThint.setFromValue(1);
        downBThint.setToValue(0);
        downBThint.setDelay(Duration.seconds(2));
        downBThint.play();

        FadeTransition ulBThint = new FadeTransition(Duration.seconds(2));
        ulBThint.setNode(uLeftBT);
        ulBThint.setFromValue(1);
        ulBThint.setToValue(0);
        ulBThint.setDelay(Duration.seconds(2));
        ulBThint.play();

        FadeTransition urBThint = new FadeTransition(Duration.seconds(2));
        urBThint.setNode(uRightBT);
        urBThint.setFromValue(1);
        urBThint.setToValue(0);
        urBThint.setDelay(Duration.seconds(2));
        urBThint.play();

        FadeTransition dlBThint = new FadeTransition(Duration.seconds(2));
        dlBThint.setNode(dLeftBT);
        dlBThint.setFromValue(1);
        dlBThint.setToValue(0);
        dlBThint.setDelay(Duration.seconds(2));
        dlBThint.play();

        FadeTransition drBThint = new FadeTransition(Duration.seconds(2));
        drBThint.setNode(dRightBT);
        drBThint.setFromValue(1);
        drBThint.setToValue(0);
        drBThint.setDelay(Duration.seconds(2));;
        drBThint.play();





        //putting images in man Group for displaying the penalty taker and setting its properties
            man = new Group(man1);
            man.setScaleX(0.135);
            man.setScaleY(0.135);
            man.setTranslateX(-100);
            man.setTranslateY(500);

            //creating timeline for penalty taker run-up animation
            Timeline t = new Timeline();
            t.setCycleCount(1);
            double time=0;
            double alt=100;

            //adding keyframes in the timeline and doing event handling on the keyframes
            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=alt), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man1);

                }
            }));

            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=alt), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man2);

                }
            }));

            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=alt), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man3);
                    SoundEffects.kickSound();
                }
            }));

            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=alt), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man4);

                }
            }));

            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=alt), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man5);

                }
            }));

            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=300), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man2);

                    }
            }));

            t.getKeyFrames().add(new KeyFrame(Duration.millis(time+=alt), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    man.getChildren().setAll(man1);

                }
            }));


            //Creating Translate Transition to make player move to the ball spot and then using sequential transition for creating a synchronized movement
            TranslateTransition run = new TranslateTransition();
            run.setDuration(Duration.seconds(0.7));
            run.setNode(man);
            run.setByX(65);
            run.setByY(-50);




        //putting image of the ball in the ballGroup Group and setting its properties
            ballGroup=new Group(ball);
            ballGroup.setTranslateX(7);
            ballGroup.setTranslateY(485);
            ball.setScaleX(0.014);
            ball.setScaleY(0.014);

            //TranslateTransition for moving the ball in top of the goal area
            TranslateTransition top = new TranslateTransition();
            top.setDuration(Duration.seconds(1));
            top.setNode(ballGroup);
            top.setByY(-380);
            SequentialTransition sTop = new SequentialTransition (
                    new PauseTransition(Duration.millis(500)), // wait 0.5 seconds
                    top
            );


        //animate ball in topLeft Direction
            //top left
            TranslateTransition topLeft = new TranslateTransition();
            topLeft.setDuration(Duration.seconds(1.2));
            topLeft.setNode(ballGroup);
            topLeft.setByY(-370);
            topLeft.setByX(-145);
            SequentialTransition sTopLeft = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait 0.7 seconds
                    topLeft
            );



        //animate ball in topRight Direction
            //top Right
            TranslateTransition topRight = new TranslateTransition();
            topRight.setDuration(Duration.seconds(1.2));
            topRight.setNode(ballGroup);
            topRight.setByY(-370);
            topRight.setByX(145);
            SequentialTransition sTopRight = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait 0.7 seconds
                    topRight
            );



        //animate ball in bottom left direction
             //bottom left
            TranslateTransition bottomLeft = new TranslateTransition();
            bottomLeft.setDuration(Duration.seconds(1.2));
            bottomLeft.setNode(ballGroup);
            bottomLeft.setByY(-250);
            bottomLeft.setByX(-145);
            SequentialTransition sBottomLeft = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait 0.7 seconds
                    bottomLeft
            );



        //animate ball in bottom right direction
            //bottom right
            TranslateTransition bottomRight = new TranslateTransition();
            bottomRight.setDuration(Duration.seconds(1.2));
            bottomRight.setNode(ballGroup);
            bottomRight.setByY(-250);
            bottomRight.setByX(145);
            SequentialTransition sBottomRight = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait 0.7 seconds
                    bottomRight
            );



           //creating GK group for putting the images of goalkeeper in the group which is used in goalkeeper movement
            Group GK = new Group(def);

            //setting GoalKeeper Size
            double GKsize=0.1;
            GK.setScaleY(GKsize);
            GK.setScaleX(GKsize);


            //animating Goalkeeper in up direction
            //GK movement
            //up
            GK.setTranslateX(7);
            GK.setTranslateY(200);

            //creating timeline for creating goalkeeper animation in the upward direction
            Timeline GKup = new Timeline();
            GKup.setCycleCount(1);

        //animate ball in bottom direction
        //bottom

        TranslateTransition bottom = new TranslateTransition();
        bottom.setDuration(Duration.seconds(1.2));
        bottom.setNode(ballGroup);
        bottom.setByY(-250);
        SequentialTransition sBottom = new SequentialTransition (
                new PauseTransition(Duration.millis(700)), // wait 0.7 seconds
                bottom
        );


            //adding keyframes to the timeline and doing event handling on the keyframes
            GKup.getKeyFrames().add(new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(def);

                }
            }));

            GKup.getKeyFrames().add(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(up);

                }
            }));

            //creating TranslateTransition for goalkeeper movement and finally putting the transition into the Sequential Transition for synchronizing purpose
            TranslateTransition upT = new TranslateTransition();
            upT.setDuration(Duration.seconds(0.7));
            upT.setNode(GK);
            upT.setByY(-30);
            SequentialTransition sUpT = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait a second
                    upT
            );


        //animate GK in bottom left direction


            //GK movement
            //bottom left
            GK.setTranslateX(7);
            GK.setTranslateY(200);

            //creating timeline for goalkeeper down left movement
            Timeline GKdl = new Timeline(); //dl=down left
            GKdl.setCycleCount(1);


        //adding keyframes to the timeline and doing event handling on the keyframes
            GKdl.getKeyFrames().add(new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(def);

                }
            }));

            GKdl.getKeyFrames().add(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(dleft);

                }
            }));

            GKdl.getKeyFrames().add(new KeyFrame(Duration.millis(900), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(dleft2);

                }
            }));

        //creating TranslateTransition for goalkeeper movement and finally putting the transition into the Sequential Transition for synchronizing purpose
            TranslateTransition dlT = new TranslateTransition();
            dlT.setDuration(Duration.seconds(1.2));
            dlT.setNode(GK);
            dlT.setByX(-70);
            dlT.setByY(20);
            SequentialTransition sDlT = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait 0.7 seconds
                    dlT
            );


        //animate GK in bottom right direction

            //GK movement
            //bottom right
            GK.setTranslateX(7);
            GK.setTranslateY(200);

            //creating timeline for goalkeeper down right movement
            Timeline GKdr = new Timeline(); //dr=down right
            GKdr.setCycleCount(1);


            //adding keyframes to the timeline and doing event handling on the keyframes
            GKdr.getKeyFrames().add(new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(def);

                }
            }));

            GKdr.getKeyFrames().add(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(dright);

                }
            }));

            GKdr.getKeyFrames().add(new KeyFrame(Duration.millis(900), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(dright2);

                }
            }));

        //creating TranslateTransition for goalkeeper movement and finally putting the transition into the Sequential Transition for synchronizing purpose
            TranslateTransition drT = new TranslateTransition();
            drT.setDuration(Duration.seconds(1.2));
            drT.setNode(GK);
            drT.setByX(70);
            drT.setByY(20);
            SequentialTransition sDrT = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait a second
                    drT
            );


        //animate GK in top left direction


            //GK movement
            //topLeft
            GK.setTranslateX(7);
            GK.setTranslateY(200);

            //creating timeline for the animation of the goalkeeper in the top left position
            Timeline GKul = new Timeline(); //ul=upleft
            GKul.setCycleCount(1);
            double ultime=0;
            double ulalt=300;


            //adding keyframes to the transition and doing event handling on the keyframes
            GKul.getKeyFrames().add(new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(def);

                }
            }));

            GKul.getKeyFrames().add(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(uleft);

                }
            }));

            GKul.getKeyFrames().add(new KeyFrame(Duration.millis(900), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(uleft2);

                }
            }));

        //creating TranslateTransition for goalkeeper movement and finally putting the transition into the Sequential Transition for synchronizing purpose
            TranslateTransition ulT = new TranslateTransition();
            ulT.setDuration(Duration.seconds(1.2));
            ulT.setNode(GK);
            ulT.setByX(-85);
            ulT.setByY(-55);
            SequentialTransition sUlT = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait a second
                    ulT
            );



        //animate GK in topRight direction


            //GK movement
            //topRight
            GK.setTranslateX(7);
            GK.setTranslateY(200);

        //creating timeline for the animation of the goalkeeper in the top right position
            Timeline GKur = new Timeline(); //ur=upright
            GKur.setCycleCount(1);
            double urtime=0;
            double uralt=300;


        //adding keyframes to the transition and doing event handling on the keyframes
            GKur.getKeyFrames().add(new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(def);

                }
            }));

            GKur.getKeyFrames().add(new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(uright);

                }
            }));

            GKur.getKeyFrames().add(new KeyFrame(Duration.millis(900), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    GK.getChildren().setAll(uright2);

                }
            }));


        //creating TranslateTransition for goalkeeper movement and finally putting the transition into the Sequential Transition for synchronizing purpose
            TranslateTransition urT = new TranslateTransition();
            urT.setDuration(Duration.seconds(1.2));
            urT.setNode(GK);
            urT.setByX(85);
            urT.setByY(-55);

            SequentialTransition sUrT = new SequentialTransition (
                    new PauseTransition(Duration.millis(700)), // wait a second
                    urT
            );
        //animating Goalkeeper in down direction
        //GK movement
        //down
        GK.setTranslateX(7);
        GK.setTranslateY(200);
        Timeline GKdown = new Timeline();
        GKdown.setCycleCount(1);




        GKdown.getKeyFrames().add(new KeyFrame(Duration.millis(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GK.getChildren().setAll(def);




            }
        }));
        GKdown.getKeyFrames().add(new KeyFrame(Duration.millis(850), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GK.getChildren().setAll(down);




            }
        }));
        TranslateTransition downT = new TranslateTransition();
        downT.setDuration(Duration.seconds(0.1));
        downT.setNode(GK);
        downT.setByY(5);
        downT.setByX(-5);

        SequentialTransition sDownT = new SequentialTransition (
                new PauseTransition(Duration.millis(700)), // wait a second
                downT
        );



            //creating timieline animation for restauring buttons and everything to default
        Timeline t2=new Timeline();



        t2.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SoundEffects.whistle();
                enableButtons();
                score = score + 10;
                controller.getMyScore().setText("" + score);

            }
        }));

        //Creating an Event to restore everything to default positions for a next penalty kick
            EventHandler<ActionEvent> restoreEvent = new EventHandler<ActionEvent>() {


                @Override
                public void handle(ActionEvent actionEvent) {

                    if(turns==5)
                    {

                        GameEndIndication.writingPoints(score);
                        GameEndIndication g = new GameEndIndication();
                        g.start(primaryStage);
                    }


                    man.getChildren().setAll(man1);
                    GK.getChildren().setAll(def);
                    man.setTranslateX(-100);
                    man.setTranslateY(500);
                    ballGroup.setTranslateX(7);
                    ballGroup.setTranslateY(485);
                    GK.setTranslateX(7);
                    GK.setTranslateY(200);



                    //conditions to check the result of the kick
                    if (Option != randomGK)
                    {

                        turns++;
                        if(turns==5)
                        {
                            score = score + 10;
                            controller.getMyScore().setText("" + score);
                        }

                        SoundEffects.goal();

                        t2.setDelay(Duration.seconds(5));
   t2.play();


                        }
                    else
                        {
                            //giving the booh sound effect on penalty miss
                            SoundEffects.penaltyMiss();

                            turns=1;
                            //doing file handling for the purpose of keeping up with the highscore of the user

                        if (HighScoreManaging.file.length() == 0)
                        {
                            hScore = score;
                            HighScoreManaging.WritingToFile(hScore);
                            System.out.println(HighScoreManaging.ReadingFromFile());
                        }
                        else
                            {
                            if (score > HighScoreManaging.ReadingFromFile()) {
                                hScore = score;

                                HighScoreManaging.WritingToFile(hScore);
                            }
                            else
                                hScore = HighScoreManaging.ReadingFromFile();

                        }


                        //After managing highscore GameEndIndication class is called which points that the user's turn is over
                            // and asks user to play again or go to main menu
                        GameEndIndication.writingPoints(score);
                        GameEndIndication g = new GameEndIndication();
                        g.start(primaryStage);

                    }

                }
            };


            //transition to call the restoreEvent after 1 kick
            PauseTransition restore = new PauseTransition(Duration.seconds(2.5));
            restore.setOnFinished(restoreEvent);
            //restore.play();



        //Button Identification
        dLeftBT.setId("dLeft");
        dRightBT.setId("dRight");
        uLeftBT.setId("uLeft");
        uRightBT.setId("uRight");
        upBT.setId("up");
        downBT.setId("down");

        //positioning the buttons on the screen
        dLeftBT.setTranslateX(-105);
        dLeftBT.setTranslateY(215);
        dRightBT.setTranslateX(130);
        dRightBT.setTranslateY(215);
        uLeftBT.setTranslateX(-105);
        uLeftBT.setTranslateY(130);
        uRightBT.setTranslateX(130);
        uRightBT.setTranslateY(130);
        upBT.setTranslateY(128);
        upBT.setTranslateX(12);
        downBT.setTranslateY(215);
        downBT.setTranslateX(12);

        //Button Size
        dLeftBT.setMinWidth(100);
        dLeftBT.setMinHeight(80);
        dRightBT.setMinWidth(100);
        dRightBT.setMinHeight(80);
        uLeftBT.setMinWidth(100);
        uLeftBT.setMinHeight(80);
        uRightBT.setMinWidth(100);
        uRightBT.setMinHeight(80);
        upBT.setMinWidth(125);
        upBT.setMinHeight(80);
        downBT.setMinWidth(125);
        downBT.setMinHeight(70);

        //Button Graphics
        upBT.setGraphic(new ImageView(UPBT));
        downBT.setGraphic(new ImageView(DOWNBT));
        uLeftBT.setGraphic(new ImageView(ULBT));
        uRightBT.setGraphic(new ImageView(URBT));
        dLeftBT.setGraphic(new ImageView(DLBT));
        dRightBT.setGraphic(new ImageView(DRBT));

        //event handling of buttons
        EventHandler<ActionEvent> downL = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option=1;
                randomGK=getRandom(1,7);
                disableButtons();
                t.play();
                run.play();
                sBottomLeft.play();
                if(randomGK==1)
                {
                    GKdl.play();
                    sDlT.play();
                }
                else if(randomGK==2)
                {
                    GKul.play();
                    sUlT.play();
                }
                else if(randomGK==3)
                {
                    GKup.play();
                    sUpT.play();
                }
                else if(randomGK==4)
                {
                    GKur.play();
                    sUrT.play();
                }
                else if(randomGK==5)
                {
                    GKdr.play();
                    sDrT.play();
                }
                else if(randomGK==6)
                {
                    GKdown.play();
                    sDownT.play();
                }

                restore.play();





            }
        };

        EventHandler<ActionEvent> upL = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option=2;
                randomGK=getRandom(1,7);
                disableButtons();
                t.play();
                run.play();
                sTopLeft.play();
                if(randomGK==1)
                {
                    GKdl.play();
                    sDlT.play();
                }
                else if(randomGK==2)
                {
                    GKul.play();
                    sUlT.play();
                }
                else if(randomGK==3)
                {
                    GKup.play();
                    sUpT.play();
                }
                else if(randomGK==4)
                {
                    GKur.play();
                    sUrT.play();
                }
                else if(randomGK==5)
                {
                    GKdr.play();
                    sDrT.play();
                }
                else if(randomGK==6)
                {
                    GKdown.play();
                    sDownT.play();
                }

                restore.play();

            }
        };

        EventHandler<ActionEvent> downR = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option=5;
                randomGK=getRandom(1,7);
                disableButtons();
                t.play();
                run.play();
                sBottomRight.play();
                if(randomGK==1)
                {
                    GKdl.play();
                    sDlT.play();
                }
                else if(randomGK==2)
                {
                    GKul.play();
                    sUlT.play();
                }
                else if(randomGK==3)
                {
                    GKup.play();
                    sUpT.play();
                }
                else if(randomGK==4)
                {
                    GKur.play();
                    sUrT.play();
                }
                else if(randomGK==5)
                {
                    GKdr.play();
                    sDrT.play();
                }
                else if(randomGK==6)
                {
                    GKdown.play();
                    sDownT.play();
                }

                restore.play();




            }
        };
        EventHandler<ActionEvent> upR = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option=4;
                randomGK=getRandom(1,7);
                disableButtons();
                t.play();
                run.play();
                sTopRight.play();
                if(randomGK==1)
                {
                    GKdl.play();
                    sDlT.play();
                }
                else if(randomGK==2)
                {
                    GKul.play();
                    sUlT.play();
                }
                else if(randomGK==3)
                {
                    GKup.play();
                    sUpT.play();
                }
                else if(randomGK==4)
                {
                    GKur.play();
                    sUrT.play();
                }
                else if(randomGK==5)
                {
                    GKdr.play();
                    sDrT.play();
                }
                else if(randomGK==6)
                {
                    GKdown.play();
                    sDownT.play();
                }

                restore.play();

            }

        };
        EventHandler<ActionEvent> upC = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option=3;
                randomGK=getRandom(1,7);
                disableButtons();
                t.play();
                run.play();
                sTop.play();

                if(randomGK==1)
                {
                    GKdl.play();
                    sDlT.play();
                }
                else if(randomGK==2)
                {
                    GKul.play();
                    sUlT.play();
                }
                else if(randomGK==3)
                {
                    GKup.play();
                    sUpT.play();
                }
                else if(randomGK==4)
                {
                    GKur.play();
                    sUrT.play();
                }
                else if(randomGK==5)
                {
                    GKdr.play();
                    sDrT.play();
                }
                else if(randomGK==6)
                {
                    GKdown.play();
                    sDownT.play();
                }


                restore.play();




            }

        };

        EventHandler<ActionEvent> downC = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Option=6;
                randomGK=getRandom(1,7);
                disableButtons();
                t.play();
                run.play();
                sBottom.play();

                if(randomGK==1)
                {
                    GKdl.play();
                    sDlT.play();
                }
                else if(randomGK==2)
                {
                    GKul.play();
                    sUlT.play();
                }
                else if(randomGK==3)
                {
                    GKup.play();
                    sUpT.play();
                }
                else if(randomGK==4)
                {
                    GKur.play();
                    sUrT.play();
                }
                else if(randomGK==5)
                {
                    GKdr.play();
                    sDrT.play();
                }
                else if(randomGK==6)
                {
                    GKdown.play();
                    sDownT.play();
                }


                restore.play();




            }

        };


        dLeftBT.setOnAction(downL);
        dRightBT.setOnAction(downR);
        uLeftBT.setOnAction(upL);
        uRightBT.setOnAction(upR);
        upBT.setOnAction(upC);
        downBT.setOnAction(downC);


            //setting of the ground image
            groundgrp=new Group(ground);
            groundgrp.setScaleX(0.803);
            groundgrp.setScaleY(0.81);
            groundgrp.setTranslateY(400);


            //root pane for holding all the panes used in the scene
        StackPane root = new StackPane();

        //creating scenes for the stage
        scene1=new Scene(root,900,750);
        scene2=new Scene(gameOver,1000,600);

        //setting root properties
        root.setId("pane");
        root.setTranslateX(0);
        root.setTranslateY(-300);

        //adding nodes to the root pane
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());//loads the background image from css file
        root.getChildren().addAll(groundgrp,man,GK,ballGroup,dRightBT,dLeftBT,uLeftBT,uRightBT,upBT,downBT);//stacks the images(nodes) on eachother

        //getting the dimensions of resolution
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();



        window.setResizable(false);
        window.centerOnScreen();
        window.setY(-8);

        //Remove Screen Exit Hint message

        window.setFullScreenExitHint("");
        window.setFullScreenExitKeyCombination(null);

        //for indicating the player to shoot
        SoundEffects.whistle();

        //setting title for the stage and setting scene into the stage and displaying the stage
        window.setTitle("Street Shootouts");
        window.setScene(scene1);
        window.show();

    }
//
//    public static void main(String[] args) {
//        launch(args);
//
//    }

}


