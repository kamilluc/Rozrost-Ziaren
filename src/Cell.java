import javafx.scene.paint.Color;

/**
 * Created by kamil on 03.05.2017.
 */
public class Cell {
    private Color color;
    private boolean state;
    private int id; //dodac i state 3 opcje

    public Cell(){
        this.color = Color.BLACK;
        this.state = false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


}
