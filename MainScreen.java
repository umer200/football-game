package sample;


import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;


public class MainScreen extends Application {

    private ProgressIndicator progress =new ProgressIndicator();
        private Label label =new Label("PRESS ENTER TO CONTINUE");
        private VBox pane=new VBox();
      private Group root=new Group();
        private VBox pane1=new VBox();
        private Group root1=new Group();

         Scene scene1=new Scene(root,1000,600);
         Scene scene2=new Scene(root1,1000,600);



        public void start (Stage pstage)throws Exception {


            //setting size of the progress indicator
            progress.setMinSize(100,100);

            //setting size of the label
            label.setMinSize(100, 100);

            //styling label
            label.setStyle("-fx-font-size:36px;-fx-font-weight: bold;");

            //styling the panes
            pane.setStyle("-fx-background-color:red;");
            pane.setAlignment(Pos.CENTER);
            pane1.setStyle("-fx-background-color:red;");
            pane1.setAlignment(Pos.CENTER);



            //adding image to the imageView
            FileInputStream mainscreen = new FileInputStream("C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\football JavaFX\\src\\sample\\football change.png");
            Image img = new Image(mainscreen);
            ImageView imageView = new ImageView(img);
            ImageView imageView1=new ImageView(img);

            //setting properties of image that is height and width
            imageView.setFitHeight(scene1.getHeight()-100);
            imageView.setFitWidth(scene1.getWidth());

            imageView1.setFitHeight(scene1.getHeight()-100);
            imageView1.setFitWidth(scene1.getWidth());

            //creating timeline animation for moving to the next scene without user interaction
            Timeline t2=new Timeline();

            t2.setCycleCount(1);


            //adding keyframe to the timeline and doing event handling on the keyframe
            t2.getKeyFrames().add(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    imageView.setFitHeight(scene1.getHeight() - 100);

                    pstage.setScene(scene2);

                }
                }));

            //delaying the timeline for giving a little time to the game loading screen  to be shown on the screen
            t2.setDelay(Duration.millis(5000));

            //playing the timeline animation
            t2.play();


            //adding event handling which deals with the user
            //interaction to move into main menu when the user is
            //prompted to press enter key

                scene2.setOnKeyPressed(ke ->
                {


                    try {
                        KeyCode b = ke.getCode();
                        if (b.getCode() == 10) {
                            MainMenu m1 = new MainMenu();
                            m1.start(pstage);
                        }
                    } catch (Exception e) {
                        System.out.println("Error");
                    }

                });


                //adding nodes to the pane
            pane.getChildren().addAll(imageView, progress);

            //adding node to the root pane
            root.getChildren().addAll(pane);

            //adding nodes to the pane1
            pane1.getChildren().addAll(imageView1, label);

            //adding node to the root node of scene2
            root1.getChildren().addAll(pane1);

            pstage.setResizable(true);

            pstage.setTitle("FOOTBALL");
            pstage.setScene(scene1);
            pstage.show();

        }


        public  static void  main(String[] args)
        {

           SoundEffects.backgroundtheme();
            launch(args);

        }

}


