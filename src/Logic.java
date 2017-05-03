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

    public void start(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                map[i][j]=new Cell();
            }
        }
        Random rng=new Random();
        for(int i=0;i<firstGeneration;i++){
            int x=rng.nextInt((width-1)-1);
            int y=rng.nextInt((height-1)-1);
            map[x][y].setState(true);
        }
    }
}
