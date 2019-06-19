@ECHO OFF
set path="C:\Program Files\Java\jdk1.8.0_201\bin";
TITLE heheheheh
javac -d out2 ./src/com/company\*.java


set CLASSPATH=.\out2
java com.company.Main

PAUSE