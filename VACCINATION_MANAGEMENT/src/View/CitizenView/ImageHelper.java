/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.CitizenView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ImageHelper {
    
    //fuction resize Image
    public static Image reSize(Image image, int Width, int Height)
    {
        Image rsImage= image.getScaledInstance(Width, Height, Image.SCALE_SMOOTH);
        return rsImage;
    }
    
    //convert Image to byteArray
    public static byte[] toByteArray(Image image, String type) throws IOException
    {
        BufferedImage bImage= new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g=bImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(bImage, type, baos);
        byte[] imageInByte=baos.toByteArray();
        
        return imageInByte;
    }
    
    //convert byteArray to Image
    public static Image createImageFromByteArray(byte[] data, String type) throws IOException
    {
        ByteArrayInputStream bis=new ByteArrayInputStream(data);
        BufferedImage img2=ImageIO.read(bis);
        
        Image img=img2.getScaledInstance(img2.getWidth(),img2.getHeight(), Image.SCALE_SMOOTH);
        return img;
    }
}
