/**
 * com.java.web.RunInScriptEx1.getNum()
 * print("hello" )
var cl = Java.type('com.java.web.RunInScriptEx1');
var result = cl.getNum();
print(result);
 */


var MyJavaClass = Java.type('com.java.web.RunInScriptEx1');
// call the static method
var greetingResult = MyJavaClass.getNum();
print(greetingResult);
// create a new intance of MyJavaClass

var myClass = new MyJavaClass();
print(myClass.a);
print("=============");
print(test.a);
print(test.getNum10());
print("=============");
print(map.get("name1"));
map.put("new","data");
print("=============");
print(jsonData);
var jsonObj = JSON.parse(jsonData);
print(jsonObj.key);
print(jsonObject.get("1"));
