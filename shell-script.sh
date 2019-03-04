/home/infoobjects/install/tomcat-9.0.14/bin/catalina.sh jpda stop
cd training/Personnel-Management-System/
mvn clean install
google-chrome http://localhost:8080/Personnel-Management-System/
cp target/Personnel-Management-System.war /home/infoobjects/install/tomcat-9.0.14/webapps
/home/infoobjects/install/tomcat-9.0.14/bin/catalina.sh jpda run
tail -100f /home/infoobjects/install/tomcat-9.0.14/logs/catalina.out

