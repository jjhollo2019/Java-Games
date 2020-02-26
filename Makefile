default:
	javac components/*.java
	javac mytimer/MyTimer.java
	javac mycommonmethods/*.java
	javac speedwords/*.java
 
 timer:
	java mytimer/MyTimer

 speed:
	java speedwords/SpeedWords
 
 clean:
	rm components/*.class
	rm mytimer/*.class
	rm mycommonmethods/*.class
	rm speedwords/*.class
