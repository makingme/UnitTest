package com.api.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

//import com.aspose.barcode.BarCodeBuilder;


public class QRCodeGenerator {
	private static final String QR_CODE_IMAGE_PATH = "A:/jar_repository/aspose-barcode-18.7-java/image/"+ "QRBarcode.png";
	public QRCodeGenerator() {
		
	}
	public static void main(String[] args) throws WriterException, IOException {
		/*QRCodeWriter qrCodeWriter =new QRCodeWriter();
		BitMatrix bitMatrix= qrCodeWriter.encode("", BarcodeFormat.QR_CODE, 100, 100);
		Path path=FileSystems.getDefault().getPath("");
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);*/
		 try {
	            generateQRCodeImage1("9\r\n" + 
	            		"IE0xX0xPRz0gLi9ob3N0LmVudicgKSI+x/bA57yzwaQ8L2E+PGJyLz4KIApNMSDA2sO8IL3DxPa9\r\n" + 
	            		"usWwuKYgw8qx4sitx9W0z7TZKL/uv7XB38D9tOsguq+w5rHdwfYhISkKIDxkaXYgY2xhc3M9ImNv\r\n" + 
	            		"ZGUtd3JhcHBlciI+PHByZSBjbGFzcz0iY29kZS1ibG9jayBjb2RlLXVuc3BlY2lmaWVkIj5saWIv\r\n" + 
	            		"c21zLnNlcSA8L3ByZT4gIDxhIGhyZWY9ImphdmFzY3JpcHQ6cnVuc2hlbGwoJ2NhdCAuLi9saWIv\r\n" + 
	            		"c21zLnNlcScgKSI+urix4jwvYT4gPC9kaXY+Cgo8aHIvPgoKPGJyLz48aDI+taXAzMXNuqPAzL26\r\n" + 
	            		"IL+ssOE8L2gyPgogICRNMV9IT01FL2xpYi9tMV91bml4LnByb3BlcnRpZXMgv6EgREK/rLDhvLPB\r\n" + 
	            		"pMC7IMfVtM+02S48YnIvPgogIGpkYmNVUkwsIHVzZXJpZCwgcGFzc3dvcmS4piC8s8Gkx9W0z7TZ\r\n" + 
	            		"Ljxici8+CiA8ZGl2IGNsYXNzPSJjb2RlLXdyYXBwZXIiPjxwcmUgY2xhc3M9ImNvZGUtYmxvY2sg\r\n" + 
	            		"Y29kZS11bnNwZWNpZmllZCI+bGliL20xX3VuaXgucHJvcGVydGllcyA8L3ByZT4gIDxhIGhyZWY9\r\n" + 
	            		"ImphdmFzY3JpcHQ6ZWRpdCgnJHtNMV9IT01FICsgIi9saWIvbTFfdW5peC5wcm9wZXJ0aWVzIn0n\r\n" + 
	            		"KSI+xu3B/TwvYT4gPGEgaHJlZj0iamF2YXNjcmlwdDpydW5zaGVsbCgnZ3JlcCBTTVNDb25uZWN0\r\n" + 
	            		"aW9uRmFjdG9yeSAuLi9saWIvbTFfdW5peC5wcm9wZXJ0aWVzJyApIj7H9sDnvLPBpDwvYT48L2Rp\r\n" + 
	            		"dj4KICAKICC/rLDhs7u/68C7IL7PyKPIrcfPsO0gwNogx8+0wiCw5r/sv6G0wiC+xrehv80gsLDA\r\n" + 
	            		"zCC9x8fgx9W0z7TZLiAKPGRpdiBjbGFzcz0iY29kZS13cmFwcGVyIj48cHJlIGNsYXNzPSJjb2Rl\r\n" + 
	            		"LWJsb2NrIGNvZGUtdW5zcGVjaWZpZWQiPiBlbmNyeXB0LnNoIFu+z8ijyK3H0rmuwNq/rV0gPC9w\r\n" + 
	            		"cmU+ICA8YSBocmVmPSJqYXZhc2NyaXB0OnJ1bnNoZWxsKCdlbmNyeXB0LnNoICcgKyB3aW5kb3cu\r\n" + 
	            		"cHJvbXB0KCe+z8ijyK2067vzua7A2r+tJywndXNlcmlkL3Bhc3N3b3JkJykgKSI+vcfH4DwvYT48\r\n" + 
	            		"L2Rpdj4KIAogILyzwaTAzCC1x776tNm46SC02cC9wLsgvcfH4MfPv6kgv6yw4cC7ILDLu+fH1bTP\r\n" + 
	            		"tNkuPGJyLz4KICAKPGhyLz4KCjxici8+PGgyPsWlu/28ui/AzLinwfbBpC+48LTPxc0gx6W9wyC8\r\n" + 
	            		"s8GkPC9oMj4KIElQQ8WlsKEgu/28urXGtMLB9iDIrsDOx9W0z7TZLiDAr8D6uO3AxyC8vLi2xve+\r\n" + 
	            		"7r/NILjeuPC4rrChIMDWtMLB9iC9w726xdu47bfJIGlwY3O3ziCwy7vnx9W0z7TZLgogIDxkaXYg\r\n" + 
	            		"Y2xhc3M9ImNvZGUtd3JhcHBlciI+PHByZSBjbGFzcz0iY29kZS1ibG9jayBjb2RlLXVuc3BlY2lm\r\n" + 
	            		"aWVkIj5pcGNzIDwvcHJlPiAgPGEgaHJlZj0iamF2YXNjcmlwdDpydW5zaGVsbCgnaXBjcycgKSI+\r\n" + 
	            		"vcfH4DwvYT48L2Rpdj4KCiBJUEPFpbimILv9vLrH1bTPtNkuCiAgPGRpdiBjbGFzcz0iY29kZS13\r\n" + 
	            		"cmFwcGVyIj48cHJlIGNsYXNzPSJjb2RlLWJsb2NrIGNvZGUtdW5zcGVjaWZpZWQiPnNtc3FtLnNo\r\n" + 
	            		"IGluc3RhbGwgPC9wcmU+ICA8YSBocmVmPSJqYXZhc2NyaXB0OnJ1bnNoZWxsKCdzbXNxbS5zaCBp\r\n" + 
	            		"bnN0YWxsJyApIj69x8fgPC9hPjwvZGl2PgoKILbHtMIgIElQQ8WluKYgu+jBpsfVtM+02S4gwdbA\r\n" + 
	            		"xyEgwOW+1iDAr7nfILChtMm8uiDA1sC9LgogICA8ZGl2IGNsYXNzPSJjb2RlLXdyYXBwZXIiPjxw\r\n" + 
	            		"cmUgY2xhc3M9ImNvZGUtYmxvY2sgY29kZS11bnNwZWNpZmllZCI+c21zcW0uc2ggMCBkZWwgPC9w\r\n" + 
	            		"cmU+ICA8YSBocmVmPSJqYXZhc2NyaXB0OnJ1bnNoZWxsKCdzbXNxbS5zaCAwIGRlbCcgKSI+vcfH\r\n" + 
	            		"4DwvYT48L2Rpdj4KCgoKIMWlILv9vLogwMzIxL+htMIgxaXAxyDAzLinwLsguq+w5sfPtMIgYmlu\r\n" + 
	            		"L3JlbmFtZS5zaCDAuyDG7cH9x8+/qSC9x8fgx9W0z7TZLgogPGRpdiBjbGFzcz0iY29kZS13cmFw\r\n" + 
	            		"cGVyIj48cHJlIGNsYXNzPSJjb2RlLWJsb2NrIGNvZGUtdW5zcGVjaWZpZWQiPnJlbmFtZS5zaCA8\r\n" + 
	            		"L3ByZT48YSBocmVmPSJqYXZhc2NyaXB0OmVkaXQoJyR7TTFfSE9NRSArICIvYmluL3JlbmFtZS5z\r\n" + 
	            		"aCJ9JykiPsbtwf08L2E+ICA8YSBocmVmPSJqYXZhc2NyaXB0OnJ1bnNoZWxsKCdyZW5hbWUuc2gn", 100, 100, QR_CODE_IMAGE_PATH);
	        } catch (WriterException e) {
	            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
	        } catch (IOException e) {
	            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
	        }
		 
		 System.out.println("END");
		
	}
	

    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
    	Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Image img=null;
        //qrCodeWriter.encode(contents, format, width, height, hints)
        
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        
        
        /*int width1 = matrix.getWidth();
    	int height1 = matrix.getHeight();
    	int[] pixels = new int[width1 * height1];
    	for (int y = 0; y < height1; y++) {
    		for (int x = 0; x < width1; x++) {
    			if (matrix.get(x, y)) {
    				pixels[y * width1 + x] = 0xff000000;
    			}
    		}
    	}

    	BinaryBitmap bitmap = BinaryBitmap.createBitmap(width1, height1, BinaryBitmap..Config.ARGB_8888);*/
    }
    
    private static void generateQRCodeImage1(String qrCodeText, int width, int height, String filePath)
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
    
	
	
/*	public static void main(String[] args) throws IOException {
		BarCodeBuilder b =new BarCodeBuilder();
		
		b.setEncodeType(com.aspose.barcode.EncodeTypes.QR);
		//b.setCodeText("1234567890\r\n1234567890\r\n1234567890\r\n1234567890\r\n");
		b.setCodeText("1234567890");
		b.save("A:\\jar_repository\\aspose-barcode-18.7-java\\image\\" + "QRBarcode.bmp", BarCodeImageFormat.Bmp);
		System.out.println("END");
	}*/
}
