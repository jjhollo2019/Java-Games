default:
	javac components/TitleLabel.java
	javac mytimer/MyTimer.java
 
 run:
	java mytimer/MyTimer
 
 clean:
	rm components/*.class
	rm mytimer/*.class
