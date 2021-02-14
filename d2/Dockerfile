FROM openjdk:13-jdk-alpine

# Build this with:
# docker build -t "apache_mina_ftpserver:1.1.1" .

# Run this with:
# docker run --name ftpd -v /tmp:/apache-ftpserver-1.1.1/res/home/:z -p 2122-2199:2122-2199 -p 21:21 -d apache_mina_ftpserver:1.1.1

# Then connect with
# ftp -p localhost 21
# and log in with admin:admin

RUN wget https://archive.apache.org/dist/mina/ftpserver/1.1.1/dist/apache-ftpserver-1.1.1.zip
RUN unzip -q apache-ftpserver-1.1.1.zip

# configure additional passive ports
RUN sed -i 's#<\/ssl>#<\/ssl><data-connection><passive ports="2122-2199"\/><\/data-connection>#g' apache-ftpserver-1.1.1/res/conf/ftpd-typical.xml
RUN sed -i 's/2121/21/g' apache-ftpserver-1.1.1/res/conf/ftpd-typical.xml

# If you'd like to configure the admin password, remove the comment of below line and change <yourpassword> to your own password. Get your password by using echo -n 'yourpassword' | md5sum
# RUN sed -i 's/21232F297A57A5A743894A0E4A801FC3/<yourpassword>/g' apache-ftpserver-1.1.1/res/conf/users.properties

EXPOSE 21
EXPOSE 2122-2199
CMD ["sh", "-c", "apache-ftpserver-1.1.1/bin/ftpd.sh res/conf/ftpd-typical.xml"]