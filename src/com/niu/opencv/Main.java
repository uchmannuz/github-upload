/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niu.opencv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.net.ServerSocket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 *
 * @author typedef
 */
public class Main
{

    static
    {
        System.load("C:\\Users\\typedef\\Downloads\\opencv_extract\\opencv\\build\\java\\x64\\opencv_java341.dll");
        System.load("C:\\Users\\typedef\\Downloads\\opencv_extract\\opencv\\build\\bin\\opencv_ffmpeg341_64.dll");
    }

    public static void main(String[] args) throws Exception
    {
        CascadeClassifier classifier = new CascadeClassifier();
        VideoCapture camera = new VideoCapture("C:\\Users\\typedef\\Downloads\\opencv_extract\\video\test.mp4");

        Mat frame = new Mat();
        JFrame jframe = new JFrame("Title");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setSize(2000, 4000);
        jframe.setVisible(true);
//        
//        Parameters:	
//
//    filename – name of the opened video file (eg. video.avi) or image sequence (eg. img_%02d.jpg, which will read samples like img_00.jpg, img_01.jpg, img_02.jpg, ...)
//    device – id of the opened video capturing device (i.e. a camera index).

        camera.open(0);
        while (true)
        {
            if (camera.read(frame))
            {
                
                ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
                vidpanel.setIcon(image);
                vidpanel.repaint();
            }
        }
    }

    public static BufferedImage Mat2BufferedImage(Mat m)
    {
        //Method converts a Mat to a Buffered Image
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] data = new byte[bufferSize];
        m.get(0, 0, data); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(data, 0, targetPixels, 0, data.length);
        return image;
    }
//    Mat mat;
//    public BufferedImage image;
//    byte[] data;
//    int nByte = 3;
//
//    public BufferedImage mat2image(Mat mat){
//        if (mat.channels()!=3  && mat.channels()!=1) return null;
//        nByte = mat.channels();
//        allocateSpace(mat);
//        mat.get(0, 0, data);
//        image.getRaster().setDataElements(0, 0, mat.cols(),mat.rows(), data);
//        return image;
//    }
//            
//	void allocateSpace(Mat mat) {
//		int w = mat.cols(), h = mat.rows();
//		if (data == null || data.length != w * h * nByte)
//			data = new byte[w * h * nByte];
//
//		if (image == null || image.getWidth() != w || image.getHeight() != h) {
//
//			if (nByte == 3)
//				image = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
//			if (nByte == 1)
//				image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
//		}
//	}
}
//C:\Users\typedef\Downloads\opencv_extract\opencv\build\java\x64\opencv_java341.dll
//-Djava.library.path="C:\Users\typedef\Downloads\opencv_extract\opencv\build\java\x64\opencv_java341.dll"
