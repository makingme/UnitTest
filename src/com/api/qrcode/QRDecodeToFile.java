package com.api.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class QRDecodeToFile {
	

	public QRDecodeToFile() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
		// args[0]: directory
		// args[1]: ext pattern
		// args[2]: result file(dir+filename)
		File[] fileList=new File(args[0]).listFiles();
		File[] qrImageList=sortFile(fileList,args[1]);
		
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
		
		ArrayList<QRCodeFileInfo> qrFileList= new ArrayList<QRCodeFileInfo>();
		for(int i=0;i<fileList.length;i++) {
			//if(i==30||i==95||i==2839)continue;
			QRCodeFileInfo info=new QRCodeFileInfo();
			info.setFileName(fileList[i].getName());
			info.setLastModified(fileList[i].lastModified());
			String context=decodeQRCode(fileList[i],hints).trim();
			//context=context.replaceAll("@\n", "");
			int firstLowEndPoint=context.indexOf("\n");
			System.out.println(i+"="+firstLowEndPoint);
			if(firstLowEndPoint==-1) {
				System.out.println(context);
			}
			
			info.setIndex(Integer.parseInt(context.substring(0,firstLowEndPoint)));
			info.setContext(context.substring(firstLowEndPoint+1));
			qrFileList.add(info);
		}
		Collections.sort(qrFileList, new Comparator<QRCodeFileInfo>() {
			@Override
	        public int compare(QRCodeFileInfo object1, QRCodeFileInfo object2) {
	         
	         return object1.index-object2.index;

	        }
		});
		//File file = new File("A:/jar_repository/aspose-barcode-18.7-java/file/"+ "result.txt");
		FileWriter fw = new FileWriter(new File(args[2]));
		for(QRCodeFileInfo info:qrFileList) {
			  fw.write(info.getContext());
			  fw.write("\n");
			  
		}
		fw.flush();
	}
	
	 private static String decodeQRCode(File qrCodeImage,Hashtable<DecodeHintType, Object> hints) throws IOException{
	    	BufferedImage bufferedImage =ImageIO.read(qrCodeImage); 
	    	LuminanceSource source= new BufferedImageLuminanceSource(bufferedImage);
	    	BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	    	
	    	try {
				Result result=new MultiFormatReader().decode(bitmap,hints);
				return result.getText();
			} catch (NotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
	    }
	 
	 private static File[] sortFile(File[] fileList,String ext) throws IOException{
		 
			int c=0;
			for(int i=0;i<fileList.length;i++) {
				//System.out.println(fileList[i].getName());
				if(fileList[i].getName().toUpperCase().endsWith(ext.toUpperCase())) {
					c+=1;
				}
			
			}
			//System.out.println(ext+" files count="+c);
			if(c>0) {
				File[] qrFileList=new File[c];
				c=0;
				for(int i=0;i<fileList.length;i++) {
					
					if(fileList[i].getName().toUpperCase().endsWith(ext.toUpperCase())) {
						qrFileList[c]=fileList[i];
						c+=1;
					}
				
				}
				/*Arrays.sort(qrFileList, new Comparator<Object>()
			       {
			        @Override
			        public int compare(Object object1, Object object2) {
			         
			         String s1 = "";
			         String s2 = "";
		     
			          s1 = ((File)object1).getName();
			          s2 = ((File)object2).getName();
			     
			         
			         
			         return s1.compareTo(s2);

			        }
			    });*/
				return qrFileList;
			}else {
				return null;
			}

	    }
	 
}
