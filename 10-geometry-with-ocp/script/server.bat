
@echo off

set JAVA_HOME=C:\Programmi\Java\jdk1.6.0_16
set ANT_HOME=..\apache-ant-1.7.1

call ..\apache-ant-1.7.1\bin\ant war

if not exist target/geometry.war (
  goto end
)

java -jar lib/winstone-0.9.10.jar --warfile target/geometry.war

:end
