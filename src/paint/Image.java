package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Image extends JPanel implements MouseListener,MouseMotionListener
{
    private ToolBox tb;
    private int modX=0,modY=0;
    private BufferedImage img = null;
    private int[][][] imgInt = null;
    private boolean firstRun = true;
    private boolean pressed = false;
    private Convertitore PioIX = new Convertitore();
    
    Image(ToolBox tb, BufferedImage img)
    {
        
        this.tb = tb;
        this.img = img;
        imgInt = PioIX.BufferedImageToInt3d(img);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setBounds(1,1,imgInt.length,imgInt[0].length);
        //Dimension dim = new Dimension(imgInt.length,imgInt[0].length);
        //setMaximumSize(dim);
        
        
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        paintImage(g);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
       
    }

    @Override
    public void mousePressed(MouseEvent me) {
        pressed=true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        pressed=false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
    public void applyChange()
    {
        if(modX<imgInt.length && modY<imgInt[0].length && modX>-1 && modY>-1)
        {
            switch(tb.tool)
            {
                case Lapis:
                    //Alto
                    if(modY-1>=0){
                        imgInt[modX][modY-1][0] = tb.color[0];
                        imgInt[modX][modY-1][1] = tb.color[1];
                        imgInt[modX][modY-1][2] = tb.color[2];
                    }
                    //Sinistra
                    if(modX-1>=0){
                        imgInt[modX-1][modY][0] = tb.color[0];
                        imgInt[modX-1][modY][1] = tb.color[1];
                        imgInt[modX-1][modY][2] = tb.color[2];
                    }
                    //centro
                    imgInt[modX][modY][0] = tb.color[0];
                    imgInt[modX][modY][1] = tb.color[1];
                    imgInt[modX][modY][2] = tb.color[2];
                    //Destra
                    if(modX+1<imgInt.length){
                        imgInt[modX+1][modY][0] = tb.color[0];
                        imgInt[modX+1][modY][1] = tb.color[1];
                        imgInt[modX+1][modY][2] = tb.color[2];
                    }
                    //Basso
                    if(modY+1<imgInt[0].length){
                        imgInt[modX][modY+1][0] = tb.color[0];
                        imgInt[modX][modY+1][1] = tb.color[1];
                        imgInt[modX][modY+1][2] = tb.color[2];
                    }
                   
                    repaint();
                    break;
                case Gomma:
                    //Alto
                    if(modY-1>=0){
                        imgInt[modX][modY-1][0] = 255;
                        imgInt[modX][modY-1][1] = 255;
                        imgInt[modX][modY-1][2] = 255;
                    }
                    //Sinistra
                    if(modX-1>=0){
                        imgInt[modX-1][modY][0] = 255;
                        imgInt[modX-1][modY][1] = 255;
                        imgInt[modX-1][modY][2] = 255;
                    }
                    //centro
                    imgInt[modX][modY][0] = 255;
                    imgInt[modX][modY][1] = 255;
                    imgInt[modX][modY][2] = 255;
                    //Destra
                    if(modX+1<imgInt.length){
                        imgInt[modX+1][modY][0] = 255;
                        imgInt[modX+1][modY][1] = 255;
                        imgInt[modX+1][modY][2] = 255;
                    }
                    //Basso
                    if(modY+1<imgInt[0].length){
                        imgInt[modX][modY+1][0] = 255;
                        imgInt[modX][modY+1][1] = 255;
                        imgInt[modX][modY+1][2] = 255;
                    }
                   
                    repaint();
                    break;
                case Effetto:
                    if(tb.effect == Tools.BlackAndWhite)
                        PioIX.GrayScaler(imgInt);
                    else
                        PioIX.Smoother(imgInt);
                    repaint();
                    break;
            }
        }
        
    }
    public BufferedImage saveImage()
    {
        return PioIX.Int3dToBufferedImage(imgInt);
    }

    

    private void paintImage(Graphics g) 
    {
        
        for(int i=0;i<imgInt.length;i++)
        {
            for(int j=0;j<imgInt[0].length;j++)
            {
                g.setColor(new Color(imgInt[i][j][0],imgInt[i][j][1],imgInt[i][j][2]));
                //g.setColor(Color.BLACK);
                //System.out.println("Ci so");
                g.drawLine(i, j, i, j);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(pressed)
        {
            modX = me.getX();
            modY = me.getY();
            applyChange();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
      
    }
    
}
