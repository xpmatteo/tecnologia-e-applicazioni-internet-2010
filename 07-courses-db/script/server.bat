
@echo off

set JAVA_HOME=C:\Programmi\Java\jdk1.6.0_16
set ANT_HOME=..\apache-ant-1.7.1

call ..\apache-ant-1.7.1\bin\ant war

if not exist target/courses.war (
  goto end
)

java -Xbootclasspath/a:config -jar lib/winstone-0.9.10.jar --warfile target/courses.war

:end
