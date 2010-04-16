#!/bin/bash

# define key information
src=sql
dbname=courses_development
dbname_test=courses_test
dbuser=courses
dbpassword=courses

# no customization needed beyond this line
if [ ! -d "$src" ]; then
  echo "Run this script from the main directory of the project"
  exit 1
fi
read -s -p "mysql root password? (type return for no password) " MYSQL_ROOT_PASSWORD

if [ "$MYSQL_ROOT_PASSWORD" != "" ]; then
    MYSQL_ROOT_PASSWORD=-p$MYSQL_ROOT_PASSWORD
fi

mysqladmin -uroot $MYSQL_ROOT_PASSWORD drop $dbname
mysqladmin -uroot $MYSQL_ROOT_PASSWORD --force drop $dbname_test
mysqladmin -uroot $MYSQL_ROOT_PASSWORD create $dbname
mysqladmin -uroot $MYSQL_ROOT_PASSWORD create $dbname_test
echo "$dbname created"
echo "grant all on $dbname.* to '$dbuser'@localhost identified by '$dbpassword';" \
     | mysql -uroot $MYSQL_ROOT_PASSWORD $dbname
echo "grant all on $dbname_test.* to '$dbuser'@localhost identified by '$dbpassword';" \
      | mysql -uroot $MYSQL_ROOT_PASSWORD $dbname_test
echo "$dbuser authorized"
cat $src/???_*.sql | mysql -u$dbuser -p$dbpassword $dbname 
cat $src/???_*.sql | mysql -u$dbuser -p$dbpassword $dbname_test 
echo "schema loaded"

