docker build -t ftp-test-server:0.5 .

docker run -p 2221:2221 -p 45445-45455:45445-45455 --name ftp-test-server-0.5 ftp-test-server:0.5

# put
curl -T anon-upload-1.txt ftp://ftp-server:2221 -p --user anonymous:ok@ok.fi

# list
curl ftp://ftp-server:2221 -p --user anonymous:ok@ok.fi

# get
curl -o anon-download-22.txt ftp://ftp-server:2221 -p --user anonymous:ok@ok.fi