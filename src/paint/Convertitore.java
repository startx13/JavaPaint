package paint;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Convertitore
{
    int bianco = 16777215;
    int[][][] BufferedImageToInt3d(BufferedImage img){
        int x,y;
        Color c;
        int[][][] img2 = new int[img.getWidth()][img.getHeight()][3];
        for(x=0;x<img.getWidth();x++)
            for(y=0;y<img.getHeight();y++){
                c = new Color(img.getRGB(x, y));
                img2[x][y][0] = c.getRed();
                img2[x][y][1] = c.getGreen();
                img2[x][y][2] = c.getBlue();
            }
        return img2;
    }

     BufferedImage Int3dToBufferedImage(int[][][] img){
        int x,y;

        BufferedImage img2 = new BufferedImage(img.length,img[0].length,BufferedImage.TYPE_INT_ARGB);
        for(x=0;x<img.length;x++)
            for(y=0;y<img[0].length;y++){
                img2.setRGB(img[x][y][0],img[x][y][1],img[x][y][2]);
            }
        return img2;
    }
    BufferedImage GrayScaler(int[][][] img){
        int x,y;

        for(x=0;x<img.length;x++)
            for(y=0;y<img[0].length;y++)
            {
                    img[x][y][0] =  (img[x][y][0] + img[x][y][1] + img[x][y][2])/3;
                    img[x][y][1] = img[x][y][0];
                    img[x][y][2] = img[x][y][0];
            }
        BufferedImage img2 = new BufferedImage(img.length,img[0].length,BufferedImage.TYPE_USHORT_GRAY);
        for(x=0;x<img.length;x++)
            for(y=0;y<img[0].length;y++)
            {
                int colore = img[x][y][0]<<16 | img[x][y][1]<<8 | img[x][y][2];
                img2.setRGB(x,y,colore);
            }
        return img2;
    }
    BufferedImage Smoother(int[][][] img){
        int x,y;

        for(x=1;x<img.length-1;x++)
            for(y=1;y<img[0].length-1;y++)
            {
                    img[x][y][0] = (img[x][y][0] + img[x-1][y][0] + img[x][y-1][0] + img[x+1][y][0] + img[x][y+1][0])/5;
                    img[x][y][1] = (img[x][y][1] + img[x-1][y][1] + img[x][y-1][1] + img[x+1][y][1] + img[x][y+1][1])/5;
                    img[x][y][2] = (img[x][y][2] + img[x-1][y][2] + img[x][y-1][2] + img[x+1][y][2] + img[x][y+1][2])/5;
            }
        BufferedImage img2 = new BufferedImage(img.length,img[0].length,BufferedImage.TYPE_INT_RGB);
        for(x=0;x<img.length;x++)
            for(y=0;y<img[0].length;y++)
            {
                int colore = img[x][y][0]<<16 | img[x][y][1]<<8 | img[x][y][2];
                img2.setRGB(x,y,colore);
            }
        return img2;
    }

}


/**
 *
 *
 *      matrice[y][x] = pippo
 *
 *      vettore[x+width*y] = pipo
 *
 */
