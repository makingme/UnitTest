package com.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JackSonJsonToMap {
//https://www.baeldung.com/jackson-object-mapper-tutorial
//https://mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/
//https://www.lesstif.com/java/java-json-library-jackson-24445183.html
//https://github.com/FasterXML/jackson/wiki/Jackson-Release-2.5.5
	private String color;
	private String type;
	SimpleModule aa=null;
	public JackSonJsonToMap(String color, String type) {
		this.color=color;
		this.type=type;
	}


	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper obMapper = new ObjectMapper();
		JackSonJsonToMap car = new JackSonJsonToMap("yellow","renault");
		String carJsonString=obMapper.writeValueAsString(car);
		System.out.println(carJsonString);
		carJsonString=obMapper.writerWithDefaultPrettyPrinter().writeValueAsString(car);
		System.out.println(carJsonString);
	}
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
}
