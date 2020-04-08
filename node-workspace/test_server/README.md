## Load balancing using Nginx 

## sudo apt-get install nginx 
## go to /etc/nginx/site-enabled/
## replace default file with below content:
'

#ip_hash least_conn
# round-robin (default) weight=4
upstream backend  {
       server 127.0.0.1:8081;
       server 127.0.0.1:8082;
       server 127.0.0.1:8083;
       server 127.0.0.1:8084;
       server 127.0.0.1:8085;
}

server {
        index index.html index.htm index.nginx-debian.html;

        server_name _;

        location / {
                proxy_pass  http://backend;
        }

}

'
## restart nginx or reload setting using : sudo service nginx restart/reload
## Launch web application on port 8081,8082,8083,8084,8085 and test using curl http://localhost

## docker commands:
### sudo docker build -t <imagename> .
### sudo docker push  (optional)
### sudo docker run -d -p 8081:8080 <imagename>
### curl localhost:8081
### repeat last 2 steps with different port numbers

### sudo docker ps  (view running containers)
### sudo docker kill <containedId>  (stop container)
