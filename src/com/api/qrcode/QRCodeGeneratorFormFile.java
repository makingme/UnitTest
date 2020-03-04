package com.api.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGeneratorFormFile {

	public QRCodeGeneratorFormFile() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException, WriterException, InterruptedException {
		QRCodeGeneratorFormFile qr=new QRCodeGeneratorFormFile();
    	Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
       // hints.put(EncodeHintType.CHARACTER_SET, "");
      //  hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// args[0] base64 인코딩 파일(위치+파일명)
    	// args[1] QRCode 생성 위치(directory) 
		BufferedReader bReader =new BufferedReader(new FileReader(new File(args[0])));
		StringBuilder sb=new StringBuilder();
		String line=null;
	
		int cycle=0;
		int index=1;
		while((line=bReader.readLine())!=null) {
			if(cycle==0)line=index+"\n"+line;
			sb.append(line+"\n");
			cycle+=1;
			line=null;
			
			if(cycle==35) {
				//System.out.println(index+"=="+sb.toString().length());
/*				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println(sb.toString());

/*				if(index==10028||index==10087||index==12557||index==13025) {
					System.out.println(index);
					System.out.println(sb.toString());
				}*/
				//sb.append("                                                                            \n");
				qr.generateQRCodeImage(sb.toString(), 200, 200, args[1]+"/"+(index)+"_QRBarcode.png",hints);
/*				if(index==9) {
					break;
				}*/
				cycle=0;
				sb.setLength(0);
				index+=1;

			}
		}
		if(sb.toString().length()>0) {
			//sb.append("@\n");
			qr.generateQRCodeImage(sb.toString(), 200, 200, args[1]+"/"+(index)+"_QRBarcode.png",hints);
		}
			
		bReader.close();
	}
    private  void generateQRCodeImage(String text, int width, int height, String filePath,Hashtable<EncodeHintType, Object> hints)
            throws WriterException, IOException, InterruptedException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
   /*     bitMatrix.clear();
        qrCodeWriter=null;*/
     /*   Thread.sleep(500);
        try {
		 	
	    	BufferedImage bufferedImage =ImageIO.read(new File(filePath)); 
	    	LuminanceSource source= new BufferedImageLuminanceSource(bufferedImage);
	    	BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	    	
				Result result=new MultiFormatReader().decode(bitmap);
				if(!text.equals(result.getText())){
					System.out.println("input");
					System.out.println(text);
					System.out.println("output");
					System.out.println(result.getText());
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}*/
	 }
    
    private  void generateQRCodeImage1(String qrCodeText, int width, int height, String filePath)
            throws WriterException, IOException {
    	Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, "png", new File(filePath));
	
    }
        


}
