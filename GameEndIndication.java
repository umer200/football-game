package sample;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;

public class GameEndIndication extends Application{

    //variable for dealing with the sound effect purpose when user hovers the mouse over button
    static int i=0;

    //points button made static because of managing the score of the player
    //this button shows the user score
    static Button points=new Button();

    //encapsulating the data fields of the class
   private Label remarks=new Label("BETTER LUCK NEXT TIME !!!");
    private Label score=new Label("YOUR SCORE: ");
    private Button menu=new Button("BACK TO MAIN MENU");
    private Button playAgain =new Button("PLAY AGAIN");
    private VBox pane1=new VBox();
    private Group root1=new Group();
    private VBox pane2=new VBox();
    private Group root2=new Group();

    //creating scenes for the stage
    Scene scene1=new Scene(root1,1000,600);
    Scene scene2=new Scene(root2,1000,600);



//for storing the score highscore button which is points button
    static void writingPoints(int score){
        points.setText(""+score);
    }

    public void mouseHoverEffect()
    {
        String s="C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\Adeel\\src\\sample\\mouseclick.mp3";
        Media m=new Media(new File(s).toURI().toString());
        MediaPlayer mP=new MediaPlayer(m);
        mP.play();

    }

    //Encapsulating the data fields
    private Image img;
    private ImageView imgView;
    private FileInputStream f;

    //giving path of the image in fileInputStream
    void settingFileInputStream() throws Exception
    {
        f=new FileInputStream("C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\Adeel\\src\\sample\\gameover2.jpg");
    }

    //creating image instance
    void settingImage()
    {
        img=new Image(f);
    }

    //creating image view object
    void settingImageView()
    {
        imgView=new ImageView(img);
        imgView.setFitWidth(scene1.getWidth());
        imgView.setFitHeight(scene1.getHeight());
    }

    //getter method of image view object
    ImageView getImgView()
    {
        return imgView;
    }


    //method for styling the pane
    void paneStyling()
    {

        //pane1 styling
        pane1.setAlignment(Pos.CENTER);
        pane1.setMinSize(scene1.getWidth(),scene1.getHeight());

        //pane2 styling
        pane2.setStyle("-fx-background-color:green;");
        pane2.setSpacing(20);
        pane2.setAlignment(Pos.CENTER);
        pane2.setMinSize(scene1.getWidth(),scene1.getHeight());



    }


    //styling the nodes
    void styling()
    {
        remarks.setStyle("-fx-font-size:50px;-fx-font-weight: bold;");
        score.setStyle("-fx-font-size:50px;-fx-font-weight: bold;");
        remarks.setTextFill(Color.DARKRED);
        score.setTextFill(Color.DARKRED);
        points.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
        menu.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
        playAgain.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
        playAgain.setTextFill(Color.BLUE);
        menu.setTextFill(Color.BLUE);
    }

    //method for putting the functionalities of the button
    void buttonFunctionalities(Stage stage)
    {

        menu.setOnMouseMoved(e->{

            i++;

            menu.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

            if(i==1)
            mouseHoverEffect();

        });

        menu.setOnMouseClicked(e->{
            try
            {
                SoundEffects.backgroundtheme();
                MainMenu m=new MainMenu();
                m.start(stage);
            }
            catch (Exception abc)
            {
                System.out.println("Error");
            }


        });

        playAgain.setOnMouseMoved(e->{

            i++;
            playAgain.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

            if(i==1)
            mouseHoverEffect();

        });

        playAgain.setOnMouseClicked(e->{

            try
            {
                streetShootouts s=new streetShootouts();
                s.start(stage);
            }
            catch (Exception a)
            {
                System.out.println("Error");
            }

        });

        scene2.setOnMouseMoved(e->{

            i=0;
            playAgain.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
            menu.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");

        });


    }



    public void start(Stage stage)
    {
        //styling the pane
        paneStyling();
        styling();

        //adding button functionalities
        buttonFunctionalities(stage);

        //creating timeline for switching to the next scene
        Timeline t=new Timeline();
        t.getKeyFrames().add(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                stage.setScene(scene2);

            }
        }));
        t.setDelay(Duration.seconds(4));
        t.play();

        try
        {
            settingFileInputStream();
            settingImage();
            settingImageView();
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }

        //adding nodes to pane and pane to root node
        pane2.getChildren().addAll(remarks,score,points, playAgain,menu);
        root2.getChildren().add(pane2);

        pane1.getChildren().add(getImgView());
        root1.getChildren().add(pane1);

        //setting scene on to the stage
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.show();

    }

//    public static void main(String[] args)
//    {
//        launch(args);
//    }



}

