import javafx.scene.paint.Color;

import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Created by kamil on 03.05.2017.
 */
public class Logic {

    public Cell[][] map,newmap;
    private int width,height;
    private int firstGeneration;
    private int neigh=0;
    boolean choice=false;
    boolean periodic=true;
    int seedRule=0;
    int radius=1;

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
    public void newSeed(int x,int y){
        map[y + 1][x + 1].setState(true);
        newmap[y + 1][x + 1].setState(true);
     //  newmap[y + 1][x + 1].setState(true);
        Random rng=new Random();
        map[y + 1][x + 1].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
        newmap[y + 1][x + 1].setColor(map[y + 1][x + 1].getColor());
    //    newmap[y + 1][x + 1].setColor(map[y + 1][x + 1].getColor());

    }
    public void start() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = new Cell();
                newmap[i][j] = new Cell();
            }
        }

        if(seedRule==0) {
            Random rng = new Random();
            for (int i = 0; i < firstGeneration; i++) {
//                int x = rng.nextInt(width - 2);
//                int y = rng.nextInt(height - 2);
                int x=rng.nextInt((width-1)-1);
                int y=rng.nextInt((height-1)-1);
                map[y + 1][x + 1].setState(true);
                newmap[y + 1][x + 1].setState(true);
                map[y + 1][x + 1].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
                newmap[y + 1][x + 1].setColor(map[y + 1][x + 1].getColor());
            }
        }
        else if(seedRule==1){
            Random rng = new Random();
            double formula=sqrt(width*height/firstGeneration);
            int iIterations=(int)(height/formula);
            int jIterations=(int)(width/formula);
            //int space=(int)(formula/2*1.5);
            int spaceX=width/(jIterations+1);
            int spaceY=height/(iIterations+1);
            for(int i=0;i<iIterations;i++){
                for(int j=0;j<jIterations;j++){
                    int x=(int)(spaceX+j*spaceX);
                    int y=(int)(spaceY+i*spaceY);
                    map[y][x].setState(true);
                    newmap[y][x].setState(true);
                    map[y][x].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
                    newmap[y][x].setColor(map[y][x].getColor());
                }
            }
//            int spaceX=0
//            int spaceY=0;
//            for (int i = 0; i < firstGeneration; i++) {
//                int x = i*spaceX+spaceX;
//                int y = i*spaceY+spaceY;
//                map[y + 1][x + 1].setState(true);
//                newmap[y + 1][x + 1].setState(true);
//                map[y + 1][x + 1].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
//                newmap[y + 1][x + 1].setColor(map[y + 1][x + 1].getColor());
        }
        else if(seedRule==2) {
            Random rng = new Random();
            int x,y;
            for(int i=0;i<firstGeneration;i++){
                x=rng.nextInt((width-1)-1);
                y=rng.nextInt((height-1)-1);
                if(!map[y][x].isRadiusMark()){
                    map[y][x].setState(true);
                    newmap[y][x].setState(true);
                    map[y][x].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
                    newmap[y][x].setColor(map[y][x].getColor());
                    for(int j=-radius;j<radius;j++){
                        for(int k=-radius;k<radius;k++){
                            if((y+j)<1 || (y+j)>(height-1) || (x+k)<1 || (x+k)>(width-1))
                                continue;
                            map[y+j][x+k].setRadiusMark(true);
                            newmap[y+j][x+k].setRadiusMark(true);
                        }
                    }

                }
                else
                    continue;
            }
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

    private void pentaLeft(int i, int j){
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

    private void pentaRight(int i, int j){
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

    private void pentaBottom(int i, int j){
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
//            if(!map[i+1][j-1].isState()){
//                newmap[i+1][j-1].setColor(map[i][j].getColor());
//                newmap[i+1][j-1].setState(true);
//            }
//            if(!map[i+1][j].isState()){
//                newmap[i+1][j].setColor(map[i][j].getColor());
//                newmap[i+1][j].setState(true);
//            }
//            if(!map[i+1][j+1].isState()){
//                newmap[i+1][j+1].setColor(map[i][j].getColor());
//                newmap[i+1][j+1].setState(true);
//            }
        }
    }

//    private void makePeriodic(){
//        for(int i=0;i<width;i++) {
////            map[0][i] = map[height-2][i];
//  //          map[height-1][i]=map[1][i];
//            map[0][i] = map[height-2][i];
//            map[height-1][i]=map[1][i];
//
//        }
//        for(int i=1;i<(height-1);i++){
////            map[i][0]=map[i][width-2];
//  //          map[i][width-1]=map[i][1];
//            map[i][0]=map[i][width-2];
//            map[i][width-1]=map[i][1];
//
//        }
//    }

    private void makePeriodic(){
        for(int i=1;i<(width-1);i++) {
//            map[0][i] = map[height-2][i];
            //          map[height-1][i]=map[1][i];
            newmap[0][i] = newmap[height-2][i];
            newmap[height-1][i]=newmap[1][i];

        }
        for(int i=1;i<(height-1);i++){
//            map[i][0]=map[i][width-2];
            //          map[i][width-1]=map[i][1];
            newmap[i][0]=newmap[i][width-2];
            newmap[i][width-1]=newmap[i][1];
// z prawej na lewo dziala
            // z dolu na gore tez
        }
    }

    public void nextStep(){

        if(periodic)
            makePeriodic();

//        for(int i=0;i<width;i++) {
//            map[0][i] = map[height-2][i];
//            map[height-1][i]=map[1][i];
//        }
//        for(int i=1;i<(height-1);i++){
//            map[i][0]=map[i][width-2];
//            map[i][width-1]=map[i][1];
//        }

        for(int i=1;i<height-1;i++){
            for(int j=1;j<width-1;j++){
                if(neigh==0) moore(i,j);
                else if(neigh==1) neumann(i,j);
                else if(neigh==2) hexaLeft(i,j);
                else if(neigh==3) hexaRight(i,j);
                else if(neigh==4) {
                            boolean tmp=randHex();
                            if(tmp) hexaLeft(i,j);
                            else hexaRight(i,j);
//                    if(!choice) hexaLeft(i,j);
//                    else if(choice) hexaRight(i,j);
                }
//                else if(neigh==5) {
//                    if(!choice) pentLeft(i,j);
//                    else if(choice) pentRight(i,j);
//                }
                else if(neigh==5) {
                    //pentLeft(i,j);
                    int tmp=randPenta();
                    if(tmp==0) pentaBottom(i,j);
                    else if(tmp==1) pentaTop(i,j);
                    else if(tmp==2) pentaLeft(i,j);
                    else if(tmp==3) pentaRight(i,j);
                }
                }
        }
        updateMap();
    }

    public void addNewSeeds(int newSeeds){
        Random rng = new Random();
        int x,y;
        for(int i=0;i<newSeeds;i++){
            x=rng.nextInt((width-1)-1);
            y=rng.nextInt((height-1)-1);

                map[y][x].setState(true);
                newmap[y][x].setState(true);
                map[y][x].setColor(Color.rgb(rng.nextInt(255), rng.nextInt(255), rng.nextInt(255)));
                newmap[y][x].setColor(map[y][x].getColor());



        }
    }

    private boolean randHex(){
        Random rng=new Random();
        boolean a=rng.nextBoolean();
        return a;
    }

    private int randPenta(){
        Random rng=new Random();
        int a=rng.nextInt(3-0);
        return a;
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

    public void setSeedRule(int seedRule){
        this.seedRule = seedRule;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}

