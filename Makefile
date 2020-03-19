default:
	javac components/*.java
	javac mytimer/MyTimer.java
	javac mycommonmethods/*.java
	javac speedwords/*.java
	javac matchthree/*.java
 
 timer:
	java mytimer/MyTimer

 speed:
	java speedwords/SpeedWords
 
 match:
	java matchthree/MatchThree

 clean:
	rm components/*.class
	rm mytimer/*.class
	rm mycommonmethods/*.class
	rm speedwords/*.class
	rm matchthree/*.class