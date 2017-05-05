import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by kamil on 03.05.2017.
 */
public class Logic {

    public Cell[][] map,newmap;
    private int width,height;
    private int firstGeneration;
    private int neigh=0;
    boolean choice=false;
    boolean periodic=false;

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

    public void start() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = new Cell();
                newmap[i][j] = new Cell();
            }
        }
        Random rng = new Random();
        for (int i = 0; i < firstGeneration; i++) {
            int x = rng.nextInt(width - 2);
            int y = rng.nextInt(height - 2);
            map[y + 1][x + 1].setState(true);
            newmap[y + 1][x + 1].setState(true);
            map[y + 1][x + 1].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
            newmap[y + 1][x + 1].setColor(map[y + 1][x + 1].getColor());
        }
//
//        for(int i=0;i<height;i++){
//            for(int j=0;j<width;j++){
//                newmap[i][j].setState(map[i][j].isState());
//                newmap[i][j].setColor(map[i][j].getColor());
//            }
//        }
    }

    public int emptyFields(){
        int x=0;
        for(int i=1;i<height-1;i++) {
            for (int j = 1; j < width - 1; j++) {
                if (!map[i][j].isState()) x++;
            }
        }
        return x;
    }

    private void moore(int i, int j){
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

    private void neumann(int i, int j){
        if(map[i][j].isState()) {
            if(!map[i-1][j].isState()){
                newmap[i-1][j].setColor(map[i][j].getColor());
                newmap[i-1][j].setState(true);
            }
            if(!map[i][j-1].isState()){
                newmap[i][j-1].setColor(map[i][j].getColor());
                newmap[i][j-1].setState(true);
            }
            if(!map[i][j+1].isState()){
                newmap[i][j+1].setColor(map[i][j].getColor());
                newmap[i][j+1].setState(true);
            }
            if(!map[i+1][j].isState()){
                newmap[i+1][j].setColor(map[i][j].getColor());
                newmap[i+1][j].setState(true);
            }
        }
    }

    private void hexaLeft(int i, int j){
        if(map[i][j].isState()) {
            if(!map[i-1][j-1].isState()){
                newmap[i-1][j-1].setColor(map[i][j].getColor());
                newmap[i-1][j-1].setState(true);
            }
            if(!map[i-1][j].isState()){
                newmap[i-1][j].setColor(map[i][j].getColor());
                newmap[i-1][j].setState(true);
            }
            if(!map[i][j-1].isState()){
                newmap[i][j-1].setColor(map[i][j].getColor());
                newmap[i][j-1].setState(true);
            }
            if(!map[i][j+1].isState()){
                newmap[i][j+1].setColor(map[i][j].getColor());
                newmap[i][j+1].setState(true);
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

    private void hexaRight(int i, int j){
        if(map[i][j].isState()) {
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
        }
    }

    private void pentLeft(int i, int j){
        if(map[i][j].isState()) {
            if(!map[i-1][j-1].isState()){
                newmap[i-1][j-1].setColor(map[i][j].getColor());
                newmap[i-1][j-1].setState(true);
            }
            if(!map[i-1][j].isState()){
                newmap[i-1][j].setColor(map[i][j].getColor());
                newmap[i-1][j].setState(true);
            }

            if(!map[i][j-1].isState()){
                newmap[i][j-1].setColor(map[i][j].getColor());
                newmap[i][j-1].setState(true);
            }

            if(!map[i+1][j-1].isState()){
                newmap[i+1][j-1].setColor(map[i][j].getColor());
                newmap[i+1][j-1].setState(true);
            }
            if(!map[i+1][j].isState()){
                newmap[i+1][j].setColor(map[i][j].getColor());
                newmap[i+1][j].setState(true);
            }
                    }
    }

    private void pentRight(int i, int j){
        if(map[i][j].isState()) {

            if(!map[i-1][j].isState()){
                newmap[i-1][j].setColor(map[i][j].getColor());
                newmap[i-1][j].setState(true);
            }
            if(!map[i-1][j+1].isState()){
                newmap[i-1][j+1].setColor(map[i][j].getColor());
                newmap[i-1][j+1].setState(true);
            }
            if(!map[i][j+1].isState()){
                newmap[i][j+1].setColor(map[i][j].getColor());
                newmap[i][j+1].setState(true);
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
    private void pentaTop(int i, int j){
        if(map[i][j].isState()) {
//            if(!map[i-1][j-1].isState()){
//                newmap[i-1][j-1].setColor(map[i][j].getColor());
//                newmap[i-1][j-1].setState(true);
//            }
//            if(!map[i-1][j].isState()){
//                newmap[i-1][j].setColor(map[i][j].getColor());
//                newmap[i-1][j].setState(true);
//            }
//            if(!map[i-1][j+1].isState()){
//                newmap[i-1][j+1].setColor(map[i][j].getColor());
//                newmap[i-1][j+1].setState(true);
//            }
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

    private void makePeriodic(){
        for(int i=0;i<width;i++) {
            map[0][i] = map[height-2][i];
            map[height-1][i]=map[1][i];
        }
        for(int i=1;i<(height-1);i++){
            map[i][0]=map[i][width-2];
            map[i][width-1]=map[i][1];
        }
    }
    public void nextStep(){

        if(periodic)
            makePeriodic();

        for(int i=0;i<width;i++) {
            map[0][i] = map[height-2][i];
            map[height-1][i]=map[1][i];
        }
        for(int i=1;i<(height-1);i++){
            map[i][0]=map[i][width-2];
            map[i][width-1]=map[i][1];
        }

        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++){
                if(neigh==0) moore(i,j);
                else if(neigh==1) neumann(i,j);
                else if(neigh==2) hexaLeft(i,j);
                else if(neigh==3) hexaRight(i,j);
                else if(neigh==4) {
                    if(!choice) hexaLeft(i,j);
                    else if(choice) hexaRight(i,j);
                }
//                else if(neigh==5) {
//                    if(!choice) pentLeft(i,j);
//                    else if(choice) pentRight(i,j);
//                }
                else if(neigh==5) pentLeft(i,j);
                }
        }
        updateMap();
    }

    public void setNeigh(int neigh) {
        this.neigh = neigh;

        if(neigh==5 || neigh==4) {
            Random rng=new Random();
            choice=rng.nextBoolean();
        }
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public boolean isPeriodic() {
        return periodic;
    }
}

