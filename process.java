//package Adeel;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class process extends Application {
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//
//
//        //ball movement
//
//
//
//
//
//
//        //GoalKeeper Movement Group Declaration
//
//
//
//
//        //bottom left
//
//
//
//
//        //bottom right
//
//
//
//
//        //drT.play();
//
//        //top left
//
//
//
//
//        //ulT.play();
//
//        //top right
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        Methods obj1 = new Methods();
//        //Button Identification
//        obj1.dLeftBT.setText("dLeft");
//        obj1.dRightBT.setText("dRight");
//        obj1.uLeftBT.setText("uLeft");
//        obj1.uRightBT.setText("uRight");
//        obj1.upBT.setText("up");
//        obj1.dLeftBT.setId("dLeft");
//        obj1.dRightBT.setId("dRight");
//        obj1.uLeftBT.setId("uLeft");
//        obj1.uRightBT.setId("uRight");
//        obj1.upBT.setId("up");
//
//        //Button Positioning
//        obj1.dLeftBT.setTranslateX(-130);
//        obj1.dLeftBT.setTranslateY(240);
//        obj1.dRightBT.setTranslateX(150);
//        obj1.dRightBT.setTranslateY(240);
//        obj1.uLeftBT.setTranslateX(-130);
//        obj1.uLeftBT.setTranslateY(107);
//        obj1.uRightBT.setTranslateX(150);
//        obj1.uRightBT.setTranslateY(107);
//        obj1.upBT.setTranslateY(107);
//        obj1.upBT.setTranslateX(8);
//
//        //event handling of buttons
//        EventHandler<ActionEvent> downL = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                obj1.Option=1;
//                obj1.disableButtons();
//                obj1.animatePlayer();
//                obj1.moveBalldL();
//                //obj1.randomGK();
//
//
//
//            }
//        };
//
//        EventHandler<ActionEvent> upL = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                obj1.Option=2;
//                obj1.disableButtons();
//                obj1.animatePlayer();
//                obj1.moveBalluL();
//                //obj1.randomGK();
//
//
//
//            }
//        };
//
//        EventHandler<ActionEvent> downR = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                obj1.Option=5;
//                obj1.disableButtons();
//                obj1.animatePlayer();
//                obj1.moveBalluP();
//                //obj1.randomGK();
//
//
//
//
//            }
//        };
//        EventHandler<ActionEvent> upR = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                obj1.Option=4;
//                obj1.disableButtons();
//                obj1.animatePlayer();
//                obj1.moveBalluR();
//                //obj1.randomGK();
//
//
//
//
//            }
//        };
//        EventHandler<ActionEvent> upC = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                obj1.Option=3;
//                obj1.disableButtons();
//                obj1.animatePlayer();
//                obj1.moveBalldR();
//                //obj1.randomGK();
//
//
//
//
//            }
//        };
//        obj1.dLeftBT.setOnAction(downL);
//        obj1.dRightBT.setOnAction(downR);
//        obj1.uLeftBT.setOnAction(upL);
//        obj1.uRightBT.setOnAction(upR);
//        obj1.upBT.setOnAction(upC);
//
//
//
//
//
//
//        StackPane root = new StackPane();
//        root.setId("pane");
//        root.setTranslateX(0);
//        root.setTranslateY(-300);
//        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
//        root.getChildren().addAll(new Methods().man,new Methods().GK,new Methods().ballGroup,new Methods().dRightBT,new Methods().dLeftBT,new Methods().uLeftBT,new Methods().uRightBT,new Methods().upBT);
//        primaryStage.setTitle("Street Shootouts");
//        primaryStage.setScene(new Scene(root, 1250, 700));
//        primaryStage.show();
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
//}