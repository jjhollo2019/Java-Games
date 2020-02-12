default:
	javac components/TitleLabel.java
	javac mytimer/MyTimer.java
	javac mycommonmethods/FileIO.java
 
 run:
	java mytimer/MyTimer
 
 clean:
	rm components/*.class
	rm mytimer/*.class
	rm mycommonmethods/*.class
