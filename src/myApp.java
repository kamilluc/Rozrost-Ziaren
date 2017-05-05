import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by kamil on 04.05.2017.
 */
public class myApp extends Application {

private static final int appHeight=100*4;
private static final int appWidth=150*4;
private static final int pixelSize=1;
private static int logicNeighbourhood=0;
private static boolean logicPeriodic=false;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("myApp");
        Group root = new Group();
        Canvas canvas = new Canvas(appWidth*pixelSize, appHeight*pixelSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
//        drawShapes(gc);
        //test
        //radio
        Label neighLabel=new Label("Neighbourhood:");
        neighLabel.setLayoutX(appWidth*pixelSize+5);
        neighLabel.setLayoutY(0);
        final ToggleGroup group = new ToggleGroup();

        RadioButton rb0=new RadioButton("Moore");
        rb0.setToggleGroup(group);
        rb0.setSelected(true);

        RadioButton rb1=new RadioButton("Neumann");
        rb1.setToggleGroup(group);

        RadioButton rb2=new RadioButton("Hexagonal Left");
        rb2.setToggleGroup(group);

        RadioButton rb3=new RadioButton("Hexagonal Right");
        rb3.setToggleGroup(group);

        RadioButton rb4=new RadioButton("Hexagonal Random");
        rb4.setToggleGroup(group);

        RadioButton rb5=new RadioButton("Pentagonal Random");
        rb5.setToggleGroup(group);
        int buttonSpace=20;
        rb0.setLayoutX(appWidth*pixelSize+5);
        rb0.setLayoutY(buttonSpace);
        rb1.setLayoutX(appWidth*pixelSize+5);
        rb1.setLayoutY(rb0.getLayoutY()+buttonSpace);
        rb2.setLayoutX(appWidth*pixelSize+5);
        rb2.setLayoutY(rb1.getLayoutY()+buttonSpace);
        rb3.setLayoutX(appWidth*pixelSize+5);
        rb3.setLayoutY(rb2.getLayoutY()+buttonSpace);
        rb4.setLayoutX(appWidth*pixelSize+5);
        rb4.setLayoutY(rb3.getLayoutY()+buttonSpace);
        rb5.setLayoutX(appWidth*pixelSize+5);
        rb5.setLayoutY(rb4.getLayoutY()+buttonSpace);
        //koniec radio

        //periodic
        Label periodicLabel=new Label("Borders:");
        periodicLabel.setLayoutX(appWidth*pixelSize+5);
        periodicLabel.setLayoutY(rb5.getLayoutY()+2*buttonSpace);

        final ToggleGroup group2 = new ToggleGroup();

        RadioButton rb6=new RadioButton("Non-periodic");
        rb6.setToggleGroup(group2);
        rb6.setSelected(true);

        RadioButton rb7=new RadioButton("Periodic");
        rb7.setToggleGroup(group2);

        rb6.setLayoutX(appWidth*pixelSize+5);
        rb6.setLayoutY(periodicLabel.getLayoutY()+buttonSpace);
        rb7.setLayoutX(appWidth*pixelSize+5);
        rb7.setLayoutY(rb6.getLayoutY()+buttonSpace);
        //end periodic

        Button btn=new Button();
        btn.setText("START");
        btn.setLayoutX(appWidth*pixelSize+20);
        btn.setLayoutY(appHeight*pixelSize-25);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello World!");
                if(rb0.isSelected()) logicNeighbourhood=0;
                else if(rb1.isSelected()) logicNeighbourhood=1;
                else if(rb2.isSelected()) logicNeighbourhood=2;
                else if(rb3.isSelected()) logicNeighbourhood=3;
                else if(rb4.isSelected()) logicNeighbourhood=4;
                else if(rb5.isSelected()) logicNeighbourhood=5;

                if(rb6.isSelected()) logicPeriodic=false;
                else if(rb7.isSelected()) logicPeriodic=true;

                drawShapes(gc);
            }
        });

        //end
        root.getChildren().add(canvas);
        root.getChildren().add(btn);
        root.getChildren().add(rb0);
        root.getChildren().add(rb1);
        root.getChildren().add(rb2);
        root.getChildren().add(rb3);
        root.getChildren().add(rb4);
        root.getChildren().add(rb5);
        root.getChildren().add(neighLabel);
        root.getChildren().addAll(periodicLabel, rb6, rb7);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    private void drawShapes(GraphicsContext gc) {
        Logic logic = new Logic(appWidth, appHeight, 20);
        logic.setNeigh(logicNeighbourhood);
        logic.setPeriodic(logicPeriodic);
//        setNeigh: 0 moore
//                  1 neumann
//                  2 hexaLeft
//                  3 hexaRight
//                  4 hexaRand
//                  5 pentaRand
        logic.setPeriodic(logicPeriodic);
        logic.start();

        while (logic.emptyFields() > 0){
            //System.out.println(logic.emptyFields());
            logic.nextStep();
        }

        for(int i=0;i<appHeight;i++){
            for(int j=0;j<appWidth;j++){
                gc.setFill(logic.map[i+1][j+1].getColor());
                gc.fillRect(j*pixelSize,i*pixelSize,pixelSize,pixelSize);
            }
        }
    }
}
