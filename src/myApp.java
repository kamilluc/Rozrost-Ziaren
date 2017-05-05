import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by kamil on 04.05.2017.
 */
public class myApp extends Application {

private static int appHeight=100*4;
private static int appWidth=150*4;
private static final int pixelSize=1;
private static int logicNeighbourhood=0;
private static boolean logicPeriodic=false;
private static int numberOfFirstSeeds=150;


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

        //seeds
        Label seedLabel=new Label("Seeds:");
        seedLabel.setLayoutX(appWidth*pixelSize+5);
        seedLabel.setLayoutY(rb7.getLayoutY()+2*buttonSpace);

        final ToggleGroup group3 = new ToggleGroup();

        RadioButton rb8=new RadioButton("Random");
        rb8.setToggleGroup(group3);
        rb8.setSelected(true);
        RadioButton rb9=new RadioButton("Even");
        rb9.setToggleGroup(group3);
        RadioButton rb10=new RadioButton("Random with Radius");
        rb10.setToggleGroup(group3);
        RadioButton rb11=new RadioButton("Clicked");
        rb11.setToggleGroup(group3);


        rb8.setLayoutX(appWidth*pixelSize+5);
        rb8.setLayoutY(seedLabel.getLayoutY()+buttonSpace);
        rb9.setLayoutX(appWidth*pixelSize+5);
        rb9.setLayoutY(rb8.getLayoutY()+buttonSpace);
        rb10.setLayoutX(appWidth*pixelSize+5);
        rb10.setLayoutY(rb9.getLayoutY()+buttonSpace);
        rb11.setLayoutX(appWidth*pixelSize+5);
        rb11.setLayoutY(rb10.getLayoutY()+buttonSpace);
        //seeds end


        //textfieldy
        Label tf1Label=new Label("Seeds count:");
        TextField textField1=new TextField("150");
        Label tf2Label=new Label("Radius:");
        TextField textField2=new TextField("4");
        Label tf3Label=new Label("Height:");
        TextField textField3=new TextField("600");
        Label tf4Label=new Label("Width:");
        TextField textField4=new TextField("500");

        tf1Label.setLayoutX(appWidth*pixelSize+5);
        tf1Label.setLayoutY(rb11.getLayoutY()+buttonSpace+10);
        textField1.setLayoutX(appWidth*pixelSize+5);
        textField1.setLayoutY(tf1Label.getLayoutY()+buttonSpace);

        tf2Label.setLayoutX(appWidth*pixelSize+5);
        tf2Label.setLayoutY(textField1.getLayoutY()+buttonSpace+10);
        textField2.setLayoutX(appWidth*pixelSize+5);
        textField2.setLayoutY(tf2Label.getLayoutY()+buttonSpace);

        tf3Label.setLayoutX(appWidth*pixelSize+5);
        tf3Label.setLayoutY(textField2.getLayoutY()+buttonSpace+10);
        textField3.setLayoutX(appWidth*pixelSize+5);
        textField3.setLayoutY(tf3Label.getLayoutY()+buttonSpace);

        tf4Label.setLayoutX(appWidth*pixelSize+5);
        tf4Label.setLayoutY(textField3.getLayoutY()+buttonSpace+10);
        textField4.setLayoutX(appWidth*pixelSize+5);
        textField4.setLayoutY(tf4Label.getLayoutY()+buttonSpace);
        //koniec text field

        Button btn2=new Button();
        btn2.setText("PAUSE");
        btn2.setLayoutX(appWidth*pixelSize);
        btn2.setLayoutY(textField4.getLayoutY()+2*buttonSpace);

        Button btn=new Button();
        btn.setText("START");
        btn.setLayoutX(btn2.getLayoutX()+4*buttonSpace);
        btn.setLayoutY(textField4.getLayoutY()+2*buttonSpace);

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

                appHeight=Integer.parseInt(textField3.getText());
                appWidth=Integer.parseInt(textField4.getText());
                canvas.setHeight(appHeight*pixelSize);
                canvas.setWidth(appWidth*pixelSize);
                numberOfFirstSeeds=Integer.parseInt(textField1.getText());

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
        root.getChildren().addAll(seedLabel, rb8,rb9,rb10,rb11, btn2);
        root.getChildren().addAll(tf1Label,tf2Label,tf3Label,tf4Label,textField1,textField2,textField3,textField4);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    private void drawShapes(GraphicsContext gc) {
        Logic logic = new Logic(appWidth, appHeight, numberOfFirstSeeds);
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
