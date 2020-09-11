package com.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.java.http.SimpleHttpRequest;

public class SimpleHttpClient {
	
	class Worker  extends SimpleHttpRequest implements Runnable{
		private String reqURL="http://localhost:8443/msgstatus";
		private OutputStream os=null;
		private BufferedReader reader=null;
		private URL url =null;
		private long startTime =0;
		private long cnt =0;
		private String tName="";
		
		public Worker(String name) {
			tName=name;
		}

		
		@Override
		public void run() {
			startTime=System.currentTimeMillis();
			while(true) {
				if((System.currentTimeMillis()-startTime) >24*60*60*1000) {
					break;
				}
				try {
					this.url =new URL(this.reqURL);
				
					String payload=this.generateDummyJson().toString();
					
					this.reqHeaderMap.put("Content-Type", "application/json; utf-8");
					this.reqHeaderMap.put("Content-Length", ""+payload.getBytes().length);
					this.reqHeaderMap.put("Accept", "application/json");
					
					String resposeData=this.doRequest(this.url, this.reqHeaderMap, payload, os, reader, "POST");
					cnt++;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println(tName+"["+cnt+"], ["+System.currentTimeMillis()+"], "+(System.currentTimeMillis()-startTime));
		}

		@Override
		public JSONObject generateDummyJson() throws ParseException {
			JSONObject _reqJsonData=new JSONObject();
			JSONObject _commonData=new JSONObject();
			JSONObject _rcsData=new JSONObject();
			JSONObject _bodyData=new JSONObject();
			JSONArray _bntList=new JSONArray();
			
			String dateTime=getNowDateTime();
			
			// common Data Setting
			String msgId=dateTime.replaceAll("\\D", "");
			_commonData.put("msgId", msgId);
			_commonData.put("userContact", "01098468940");//KT:01098468940 LG:01083118940 SKT:01090488940
			_commonData.put("scheduleType", "0");
			_commonData.put("msgGroupId", msgId);
			_commonData.put("msgServiceType", "rcs");
			
			//rcs Data Setting
			_rcsData.put("chatbotId", "15776825");
			_rcsData.put("agencyId", "ktrcsdev");//ktrcsdev, ktbizrcs
			_rcsData.put("messagebaseId", "UBR.D93Lnzo1wL-GG000F");
			_rcsData.put("serviceType", "RCSTMPL");
//			_rcsData.put("messagebaseId", "SS000000");
//			_rcsData.put("serviceType", "RCSSMS");
			_rcsData.put("expiryOption", 1);
			_rcsData.put("header", "0");
			_rcsData.put("footer", "");
			_rcsData.put("copyAllowed", false);
			
			//Body Data Setting
			_bodyData.put("description", "["+msgId+"], RCSTMPL");
			
			_rcsData.put("body", _bodyData);
			//_rcsData.put("buttons", _bntList);
			
			_reqJsonData.put("common", _commonData);
			_reqJsonData.put("rcs", _rcsData);
			return _reqJsonData;
		}

		@Override
		public JSONObject generateDummyJson(String v1) throws ParseException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		SimpleHttpClient sc=new SimpleHttpClient();
		for(int i=0;i<5;i++) {
			Worker a=sc.new Worker(""+i+":Thead");
			Thread t=new Thread(a);
			t.start();
		}
		
	}
}
