import java.awt.*;
import java.util.Random;

public class ColorGrid {

    private Color[][] colorArray;
    private int r, g, b;
    private Random random = new Random();

    public ColorGrid(){
        colorArray = new Color[20][20];
        refresh();
    }

    public Color[][] getColorArray(){
        return colorArray;
    }

    public void refresh(){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                r = random.nextInt(255);
                g = random.nextInt(255);
                b = random.nextInt(255);
                colorArray[i][j] = new Color(r, g, b);
            }
        }
    }

    public boolean compareRGB(Color c1, Color c2){
        if(c1.getRGB() > c2.getRGB()){
            return true;
        } else {
            return false;
        }
    }

    public void BubbleSort(){
        Thread time = new Thread();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 19; j++){
                if(compareRGB(colorArray[i][j], colorArray[i][j+1])){
                    Color temp = colorArray[i][j];
                    colorArray[i][j] = colorArray[i][j+1];
                    colorArray[i][j+1] = temp;
                    GUI.updateGUI(colorArray);
                }
            }
        }
    }
}
