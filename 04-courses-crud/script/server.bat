
set JAVA_HOME=C:\Programmi\Java\jdk1.6.0_12
set ANT_HOME=..\apache-ant-1.7.1

..\apache-ant-1.7.1\bin\ant war

if not exist target/courses.war (
  echo "fail"
  exit
)

java -jar lib/winstone-0.9.10.jar --warfile target/courses.war
