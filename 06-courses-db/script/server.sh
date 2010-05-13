
WINSTONE=lib/winstone-0.9.10.jar

ant war || exit 1
java \
  -jar $WINSTONE \
  --commonLibFolder=lib/db \
  --useJNDI=true \
  --jndi.resource.jdbc/courses_database=javax.sql.DataSource \
  '--jndi.param.jdbc/courses_database.url=jdbc:hsqldb:file:./db/databases/courses_development;shutdown=true' \
  --jndi.param.jdbc/courses_database.driverClassName=org.hsqldb.jdbcDriver \
  --jndi.param.jdbc/courses_database.username=sa \
  --jndi.param.jdbc/courses_database.password= \
  --warfile target/*.war \
  $*
