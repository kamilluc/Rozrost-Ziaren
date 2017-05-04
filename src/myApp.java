import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by kamil on 04.05.2017.
 */
public class myApp extends Application {

private static final int appHeight=100*4;
private static final int appWidth=150*4;
private static final int pixelSize=1;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("myApp");
        Group root = new Group();
        Canvas canvas = new Canvas(appWidth*pixelSize, appHeight*pixelSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        //test
//        Button btn=new Button();
//        btn.setText("hello");
//
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        //end
        root.getChildren().add(canvas);
        //root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    private void drawShapes(GraphicsContext gc) {
        Logic logic = new Logic(appWidth, appHeight, 150);
        logic.setNeigh(1);
//        setNeigh: 0 moore
//                  1 neumann
//                  2 hexaLeft
//                  3 hexaRight
//                  4 hexaRand
//                  5 pentaRand
        logic.start();

        while (logic.emptyFields() > 0){
            System.out.println(logic.emptyFields());
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
