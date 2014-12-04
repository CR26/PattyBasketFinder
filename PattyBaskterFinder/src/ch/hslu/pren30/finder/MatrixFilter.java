/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.finder;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

/**
 *
 * @author Patrik
 */
public class MatrixFilter {
    
    private static MatrixFilter instance = null;
        
    private MatrixFilter() {
        
    }
         
    public static MatrixFilter getInstance() {
        if (instance == null) {
            instance = new MatrixFilter();
        }
        return instance;
    }
    
    public int[] getRow(Mat imageMatrix, int row) {
        int[] result = new int[imageMatrix.cols()];

        Size sizeA = imageMatrix.size();
        
        for (int j = 0; j < sizeA.width; j++) {
            double[] data = imageMatrix.get(row, j);
            data[0] = data[0] / 2;
            data[1] = data[1] / 2;
            data[2] = data[2] / 2;
            //C.put(i, j, data);
            result[j] = (int)(data[0] + data[1] + data[2]);
        }
                
        return result;
    }
    
    public static BufferedImage matToBufferedImage(Mat matrix) {  
        int cols = matrix.cols();  
        int rows = matrix.rows();  
        int elemSize = (int)matrix.elemSize();  
        byte[] data = new byte[cols * rows * elemSize];  
        int type;  
        matrix.get(0, 0, data);  
        switch (matrix.channels()) {  
            case 1:  
                type = BufferedImage.TYPE_BYTE_GRAY;  
                break;  
            case 3:  
                type = BufferedImage.TYPE_3BYTE_BGR;  
                // bgr to rgb  
                byte b;  
                for(int i=0; i<data.length; i=i+3) {  
                    b = data[i];  
                    data[i] = data[i+2];  
                    data[i+2] = b;  
                }  
                break;  
            default:  
                return null;  
        }  
        BufferedImage image2 = new BufferedImage(cols, rows, type);  
        image2.getRaster().setDataElements(0, 0, cols, rows, data);  
        return image2;  
    }
    
    public static Mat imageToMat(Image image) {
        BufferedImage bImg = toBufferedImageOfType(toBufferedImage(image), BufferedImage.TYPE_3BYTE_BGR);
        
        byte[] pixels = ((DataBufferByte) bImg.getRaster().getDataBuffer()).getData();

        Mat mat = new Mat(bImg.getHeight(), bImg.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);

        return mat;
    }
        
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }
    
    public static BufferedImage toBufferedImageOfType(BufferedImage original, int type) {
        if (original == null) {
            throw new IllegalArgumentException("original == null");
        }

        // Don't convert if it already has correct type
        if (original.getType() == type) {
            return original;
        }

        // Create a buffered image
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), type);

        // Draw the image onto the new buffer
        Graphics2D g = image.createGraphics();
        try {
            g.setComposite(AlphaComposite.Src);
            g.drawImage(original, 0, 0, null);
        }
        finally {
            g.dispose();
        }

        return image;
    }

    public Image exchangeRow(Image snapshotImage, int row) {
        Mat mat = imageToMat(snapshotImage);
        
        Size sizeA = mat.size();
        
        for (int j = 0; j < sizeA.width; j++) {
            byte[] data = new byte[3];
            data[0] = 127;
            data[1] = 0;
            data[2] = 0;
            mat.put(row, j, data);
        }
        
        return matToBufferedImage(mat);
    }
    
     public Image exchangeColumn(Image snapshotImage, int column) {
        Mat mat = imageToMat(snapshotImage);
        
        Size sizeA = mat.size();
        
        for (int j = 0; j < sizeA.height; j++) {
            byte[] data = new byte[3];
            data[0] = 0;
            data[1] = 127;
            data[2] = 0;
            mat.put(j, column, data);
        }
        
        return matToBufferedImage(mat);
    }
}
