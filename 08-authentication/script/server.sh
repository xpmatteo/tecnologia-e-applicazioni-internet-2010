
WINSTONE=./lib/winstone-0.9.10.jar

ant -emacs war || exit 1
java \
  -Xbootclasspath/a:config \
  -jar $WINSTONE \
  --warfile target/*.war \
  $*
