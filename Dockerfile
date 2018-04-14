FROM tomcat
MAINTAINER ssinghi

ADD . target/hotelapp.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

