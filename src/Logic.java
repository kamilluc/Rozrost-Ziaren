import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by kamil on 03.05.2017.
 */
public class Logic {

    public Cell[][] map,newmap;
    private int width,height;
    private int firstGeneration;

    public Logic(int width, int height, int firstGeneration) {
        this.width = (width+2);
        this.height = (height+2);
        this.firstGeneration = firstGeneration;
        map=new Cell[height+2][width+2];
        newmap=new Cell[height+2][width+2];
    }

    public void show(){
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++){
                if(map[i][j].isState())
                    System.out.print("#");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void updateMap(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                //map[i][j]=newmap[i][j];

                map[i][j].setState(newmap[i][j].isState());
                map[i][j].setColor(newmap[i][j].getColor());
            }
        }
    }

    public void start(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                map[i][j]=new Cell();
                newmap[i][j]=new Cell();
            }
        }
        Random rng=new Random();
        for(int i=0;i<firstGeneration;i++){
            int x=rng.nextInt(width-2);
            int y=rng.nextInt(height-2);
            map[y+1][x+1].setState(true);
            newmap[y+1][x+1].setState(true);
            map[y+1][x+1].setColor(Color.rgb(rng.nextInt(255),rng.nextInt(255),rng.nextInt(255)));
            newmap[y+1][x+1].setColor(map[y+1][x+1].getColor());
        }
//
//        for(int i=0;i<height;i++){
//            for(int j=0;j<width;j++){
//                newmap[i][j].setState(map[i][j].isState());
//                newmap[i][j].setColor(map[i][j].getColor());
//            }
//        }
    }

    public void nextStep(){
        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++){
                if(map[i][j].isState()) {
                    if(!map[i-1][j-1].isState()){
                        newmap[i-1][j-1].setColor(map[i][j].getColor());
                        newmap[i-1][j-1].setState(true);
                    }
                    if(!map[i-1][j].isState()){
                        newmap[i-1][j].setColor(map[i][j].getColor());
                        newmap[i-1][j].setState(true);
                    }
                    if(!map[i-1][j+1].isState()){
                        newmap[i-1][j+1].setColor(map[i][j].getColor());
                        newmap[i-1][j+1].setState(true);
                    }
                    if(!map[i][j-1].isState()){
                        newmap[i][j-1].setColor(map[i][j].getColor());
                        newmap[i][j-1].setState(true);
                    }
                    if(!map[i][j+1].isState()){
                        newmap[i][j+1].setColor(map[i][j].getColor());
                        newmap[i][j+1].setState(true);
                    }
                    if(!map[i+1][j-1].isState()){
                        newmap[i+1][j-1].setColor(map[i][j].getColor());
                        newmap[i+1][j-1].setState(true);
                    }
                    if(!map[i+1][j].isState()){
                        newmap[i+1][j].setColor(map[i][j].getColor());
                        newmap[i+1][j].setState(true);
                    }
                    if(!map[i+1][j+1].isState()){
                        newmap[i+1][j+1].setColor(map[i][j].getColor());
                        newmap[i+1][j+1].setState(true);
                    }
                }
            }
        }
        updateMap();
    }
}
