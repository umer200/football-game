package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

//MainMenu class which contains main method and which acts as base class for Main Menu
public class MainMenu extends Application{

    //variable for dealing with the sound effect purpose when user hovers the mouse over button
    static int i=0;

    //encapsulating the data fields of the class
    private StackPane spane=new StackPane();
    private VBox pane=new VBox();
    private Group root=new Group();
    private Button startGame=new Button("NEW GAME");
    private Button controls=new Button("CONTROLS");
    private Button highscore=new Button("HIGHSCORE");
    private  Button gameDescription=new Button("DESCRIPTION");
    private Button exit=new Button("EXIT");

    Scene scene=new Scene(root,1000,600);

    private Image img;
    private ImageView imgView;

    //method for adding sound effects for mouse hovering over the buttons
    public void mouseHoverEffect()
    {
        String s="C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\Adeel\\src\\sample\\mouseclick.mp3";
        Media m=new Media(new File(s).toURI().toString());
        MediaPlayer mP=new MediaPlayer(m);
        mP.play();
    }

    public void start(Stage stage) throws Exception{


        FileInputStream f=new FileInputStream("C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\football JavaFX\\src\\sample\\bg2.jpg");
         img=new Image(f);
         imgView=new ImageView(img);
         imgView.setVisible(true);
         imgView.setFitHeight(stage.getHeight());
         imgView.setFitWidth(stage.getWidth());


         // pane adjustment
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(30);
        pane.setMinSize(1000,600);
        spane.setAlignment(Pos.CENTER);

        //pane styling
        spane.setStyle("-fx-background-color:blue;");
        startGame.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
       controls.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
       highscore.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
       gameDescription.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
        exit.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");


        //event handling for wwhen mouse is moved on the screen other than buttons
       scene.setOnMouseMoved(e->{
           i=0;
           startGame.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
           controls.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
           highscore.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
           gameDescription.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
           exit.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");
       });



       //mouse moved event handling on buttons

       startGame.setOnMouseMoved(e->{

           i++;

           startGame.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

           if(i==1)
           mouseHoverEffect();

       });

       controls.setOnMouseMoved(e->{

           i++;
           controls.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

           if(i==1)
           mouseHoverEffect();
       });

       highscore.setOnMouseMoved(e->{

           i++;

           highscore.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

           if(i==1)
           mouseHoverEffect();

       });

       gameDescription.setOnMouseMoved(e->{

           i++;
           gameDescription.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

           if(i==1)
           mouseHoverEffect();

       });

      exit.setOnMouseMoved(e->{

          i++;

           exit.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

           if(i==1)
           mouseHoverEffect();

        });


      //Mouse clicked event handling on buttons

      startGame.setOnMouseClicked(e->{
//          Timeline t1=new Timeline();
          Timeline t2=new Timeline();

          loadingScreen l=new loadingScreen();
          streetShootouts s = new streetShootouts();

          try{
              l.start(stage);
          }
          catch(Exception abc){

          }

          t2.getKeyFrames().add(new KeyFrame(Duration.seconds(5),new EventHandler<javafx.event.ActionEvent>() {

              @Override
              public void handle(ActionEvent actionEvent)
              {

                  try {

                      SoundEffects.themepause();

                      s.start(stage);
//                      stage.setScene(s.scene1);
                  }
                  catch(Exception abc){
                      System.out.println("Error");
                  }

              }


          }


          ));




t2.setDelay(Duration.seconds(3));
t2.play();


      });

      controls.setOnAction(e->{

          try {
              Controls c = new Controls();
              c.start(stage);
          }
          catch (Exception a){
              System.out.println("Error");
          }

      });

      highscore.setOnAction(e->{

          try {
              HighScore hS=new HighScore();
              hS.start(stage);
          }
          catch (Exception a){
              System.out.println("Error");
          }

      });

      gameDescription.setOnAction(e->{

          try {
              GameDescription gD = new GameDescription();
              gD.start(stage);
          }
          catch (Exception a){
              System.out.println("Error");
          }


      });

        exit.setOnAction(e->{

            stage.close();


        });



        //adding nodes to the pane and finally to the root pane
       pane.getChildren().addAll(startGame,controls,highscore,gameDescription,exit);
       spane.getChildren().addAll(imgView,pane);
       root.getChildren().add(spane);

       stage.setResizable(true);

       //adding scene to the stage
       stage.setScene(scene);
       stage.setTitle("STREET SHOOTOUTS");
       stage.show();

    }


}



//Abstract class which acts as parent class for the menu options and  will be inherited by all the main menu options

abstract class MenuOptions extends Application{
   protected StackPane spane=new StackPane();
   protected VBox pane=new VBox();
    protected Label info=new Label();
    protected Image img;
    protected ImageView imgView;
    protected Label heading =new Label();
    protected Group root=new Group();
    protected Button back=new Button("BACK TO MAIN MENU");

    Scene scene=new Scene(root,1000,600);

    abstract void settingInfoText();

     void styling()
     {

         info.setStyle("-fx-font-size:20px;-fx-font-weight: bold;");
         heading.setStyle("-fx-font-size:30px;-fx-font-weight: bold;");
         info.setTextFill(Color.YELLOW);
         heading.setTextFill(Color.RED);
         back.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");

    }

    void paneStyling()
    {
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(15);

        spane.setMinSize(scene.getWidth(),scene.getHeight());
    }


    void settingBackgroundImage()throws IOException
    {
        FileInputStream f=new FileInputStream("C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\football JavaFX\\src\\sample\\menubackground.png");
        img=new Image(f);
        imgView=new ImageView(img);
        imgView.setVisible(true);
        imgView.setFitHeight(scene.getHeight());
        imgView.setFitWidth(scene.getWidth());
    }


    void backButtonFunctionalities(Stage stage)
    {
        back.setOnMouseMoved(e->{

            back.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:yellow;");

        });

        back.setOnMouseClicked(e->{

            try
            {
                MainMenu m1 = new MainMenu();
                m1.start(stage);
            }
            catch (Exception a)
            {
                System.out.println("Error");
            }

        });

        scene.setOnMouseMoved(e->{

            back.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");

        });

    }

}


//Controls Class which acquaints the user with controls of the game
class Controls extends MenuOptions{


    void styling()
    {

        info.setStyle("-fx-font-size:20px;-fx-font-weight: bold;");
        heading.setStyle("-fx-font-size:30px;-fx-font-weight: bold;");
        info.setTextFill(Color.YELLOW);
        heading.setTextFill(Color.RED);
        back.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");

    }

    void settingInfoText()
    {
        info.setText("The controls of the game are pretty simple. The game is very much self-explanatory. Once the game starts\n" +
                "the user is shown the six boxes with arrows so as to where he can shoot the ball\n" +
                "But still we are here to make it crystal clear for you.\n" +
                "Once the game starts you play as penalty-kick taker and you have to click on formerly indicated " +
                "positions.\nOn which so ever " +
                "position the user clicks the player kicks the ball on that spot.");
    }

    public void start(Stage stage)throws Exception{

        styling();

        heading.setText("CONTROLS");

        settingInfoText();

        settingBackgroundImage();

       super.backButtonFunctionalities(stage);

       paneStyling();

        pane.getChildren().addAll(heading,info,back);
        spane.getChildren().addAll(imgView,pane);
        root.getChildren().add(spane);

        stage.setScene(scene);
        stage.setTitle("STREET SHOOTOUTS");
        stage.show();

    }


}


//Highscore Class to keep record of highscores
class HighScore extends MenuOptions{


    void styling()
    {

        info.setStyle("-fx-font-size:40px;-fx-font-weight: bold;");
        heading.setStyle("-fx-font-size:30px;-fx-font-weight: bold;");
        info.setTextFill(Color.YELLOW);
        heading.setTextFill(Color.RED);
        back.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");

    }

    void settingInfoText(){
        info.setText(""+ streetShootouts.hScore);
    }

    public void start(Stage stage)throws Exception{

        styling();

        heading.setText("HIGHSCORE");

        settingInfoText();

    settingBackgroundImage();

        super.backButtonFunctionalities(stage);

       paneStyling();

        pane.getChildren().addAll(heading,info,back);
        spane.getChildren().addAll(imgView,pane);

        root.getChildren().add(spane);

        stage.setScene(scene);
        stage.setTitle("STREET SHOOTOUTS");
        stage.show();


    }


}


//Game Description class to  give details about what the game really is
class GameDescription extends MenuOptions{


    void styling()
    {

        info.setStyle("-fx-font-size:20px;-fx-font-weight: bold;");
        heading.setStyle("-fx-font-size:30px;-fx-font-weight: bold;");
        info.setTextFill(Color.YELLOW);
        heading.setTextFill(Color.RED);
        back.setStyle("-fx-font-size:30px;-fx-font-weight: bold;-fx-color:red;");

    }

    void settingInfoText()
    {
        info.setText("Street Shootouts is a 2D-penalty game for appeasing the crave of football enthusiasts.\n" +
                "The game delineates a penalty shootout in which user plays as penalty taker and has to put\n" +
                "the ball inside the net by beating the keeper. For scoring each goal the score increases by 10 points\n" +
                "unless the keeper makes a save which ends the player's turn which insinuates the Death Penalty scenario.\n " +
                "This game provides you an experience of what it is like to play Death Penalties.\n" +
                "Now you are ready to get on with the game.\n" +
                "Just beat the keeper as many times as you can to score maximum points !!!\n" +
                "Challenge your friends to beat your score !!!");
    }


    public void start(Stage stage)throws Exception{

        styling();

        heading.setText("GAME DESCRIPTION");

        settingInfoText();

        settingBackgroundImage();

      super.backButtonFunctionalities(stage);

     paneStyling();

        pane.getChildren().addAll(heading,info,back);
        spane.getChildren().addAll(imgView,pane);

        root.getChildren().add(spane);

        stage.setScene(scene);
        stage.setTitle("STREET SHOOTOUTS");
        stage.show();

    }

}


//class for loading screen of game
class loadingScreen extends Application{

    //Encapsulating the data fields
    private ProgressIndicator p=new ProgressIndicator();
    static Group root=new Group();
    static Scene scene=new Scene(root,1000,600);
    private VBox v=new VBox();

    public void start (Stage stage) throws Exception
    {
        v.setStyle("-fx-background-color:red;");
        v.setAlignment(Pos.CENTER);

        p.setMinSize(100,100);

        FileInputStream mainscreen = new FileInputStream("C:\\Users\\urooj\\Desktop\\JAVA REPOSITORY\\football JavaFX\\src\\sample\\football change.png");
        Image img = new Image(mainscreen);
        ImageView imageView = new ImageView(img);
        ImageView imageView1=new ImageView(img);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(500);
        v.setMinSize(1000,600);

        v.getChildren().addAll(imageView,p);
        root.getChildren().add(v);

        stage.setScene(scene);
        stage.show();

    }

}