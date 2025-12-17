mvn clean
mvn package
cp "target/hw3-template-1.0-SNAPSHOT-jar-with-dependencies.jar" "main.jar"
java -Xms10g -Xmx10g -XX:+UseSerialGC -Xlog:gc -jar main.jar > output.txt 2>&1
