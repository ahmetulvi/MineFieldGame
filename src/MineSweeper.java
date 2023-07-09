import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber;
    int colNumber;
    String[][] mineMap;
    String[][] gameMap;
    int mineNumber;
    boolean isTrue=true;
    int a,b,count;


    public MineSweeper(int row, int col){
        this.colNumber=col;
        this.rowNumber=row;
        this.mineNumber=(row*col)/4;
        this.mineMap=new String[rowNumber][colNumber];
        this.gameMap=new String[rowNumber][colNumber];

    }
    public void mineMap(){
        for (int i=0;i<rowNumber;i++){
            for (int j=0;j<colNumber;j++){
                mineMap[i][j]="-";
                gameMap[i][j]="-";
            }
        }
    }
    public void random(){
        Random r=new Random();
        for (int i=0;i<mineNumber;i++){
            int r1=r.nextInt(rowNumber);//rowNumber 4 ise 0 ile 4 arasında sayı üretir.
            int r2=r.nextInt(colNumber);//0 ile colNumber arasında random sayı üretir.
            if (!this.mineMap[r1][r2].equals("*"))
                this.mineMap[r1][r2]="*";

        }
    }
    public void printMineMap(){
        random();
        for (int i=0;i<this.rowNumber;i++){
            for (int j=0;j<this.colNumber;j++){
                if (!this.mineMap[i][j].equals("*")) {
                    this.mineMap[i][j]="-";
                }
                System.out.print(this.mineMap[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }
    public void printGameMap(){
        for (int i=0;i<this.rowNumber;i++){
            for (int j=0;j<this.colNumber;j++){
                this.gameMap[i][j]="-";
                System.out.print(this.gameMap[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void IndexSelecting(){
        Scanner sc=new Scanner(System.in);
        isTrue=false;
        System.out.println("Lütfen indis seçiniz :");
        while (!isTrue){
            System.out.println("Satır giriniz");
            a= sc.nextInt();
            System.out.println("Sütun giriniz");
            b= sc.nextInt();
            if (a>rowNumber || b>colNumber){
                System.out.println("Harita sınırları dışında seçim yaptınız!");
                continue;
            }
            if (mineMap[a][b].equals("*")){
                System.out.println("Game over!");
                break;
            }
            control();
            if (finish()){
                System.out.println("Tebrikler oyunu kazandınız.");
                break;
            }


        }

    }

    public void control() {
        count = 0;
        for (int i = (a - 1); i <= (a + 1); i++) {
            for (int j = (b - 1); j <= (b + 1); j++) {
                if (i < 0 || j < 0 || i >= this.rowNumber || j >= this.colNumber) {
                    continue;
                }
                if (this.mineMap[i][j].equals("*")) {
                    count++;
                }
            }
        }

        this.gameMap[a][b] = String.valueOf(count);
        this.mineMap[a][b] = String.valueOf(count);
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                System.out.print(this.gameMap[i][j] + " ");
            }
            System.out.println("");
        }
    }
   public boolean  finish(){
        for (int i=0;i<this.rowNumber;i++){
            for (int j=0; j<this.colNumber;j++){
                if (this.mineMap[i][j].equals("-"))
                    return false;
            }
        }
        return true;
    }
    public void run(){
        mineMap();
        printMineMap();
        printGameMap();
        IndexSelecting();

    }
}
