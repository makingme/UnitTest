package com.api.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeReader {

	public QRCodeReader() {
		
	}
	/*public static void main(String[] args) {
		BarCodeReader reader =new  BarCodeReader("A:\\jar_repository\\aspose-barcode-18.7-java\\image\\" + "QRBarcode.bmp",DecodeType.QR);
		//BarCodeReader b =new  BarCodeReader("",BaseDecodeType.);
		//reader.setRecognitionMode(RecognitionMode.MaxQuality);
		
		while (reader.read())
		 {
		       System.out.println("BarCode Type: " + reader.getCodeType());
		       System.out.println("BarCode CodeText: " + reader.getCodeText());
		 }
		System.out.println("END");
		 reader.close();
	}*/
	public static void main(String[] args) {
		try {
			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

            File file = new File("A:\\jar_repository\\aspose-barcode-18.7-java\\image\\QRBarcode.png");
            String decodedText = decodeQRCode(file,hints);
            if(decodedText == null) {
                System.out.println("No QR Code found in the image");
            } else {
                System.out.println( decodedText);
                System.out.println(decodedText.replace("\r\n", "").length());
                System.out.println(decodedText.length());
            }
        } catch (Exception e) {
            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
        }
	}
	
	 private static String decodeQRCode(File qrCodeImage,Hashtable<DecodeHintType, Object> hints) {
		 try {
		 	if(qrCodeImage.exists())System.out.println("Found File");
	    	BufferedImage bufferedImage =ImageIO.read(qrCodeImage); 
	    	LuminanceSource source= new BufferedImageLuminanceSource(bufferedImage);
	    	BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	    	
				Result result=new MultiFormatReader().decode(bitmap);
				return result.getText();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
	    }
}
