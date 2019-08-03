# steps to setup virtual environment and start poker app in sqlite mode
$ sudo apt-get update
$ sudo apt-get -y install python3-pip
$ pip3 install virtualenv
$ cd ~
$ python3 -m virtualenv env
checkout code 
$ source env/bin/activate
(env)$ cd to poker 
comment out psycopg2
(env)$ pip3 install -r requirement.txt
launch server on sqlite mode and test

# steps to setup postgres
(env)$ sudo apt-get -qqy install postgresql python-psycopg2
(env)$ sudo apt-get install libpq-dev 
(env)$ sudo apt-get install python-dev
(env)$ pip3 install psycopg2
(env)$ deactivate

# create user and db (if needed) 
$ sudo su postgres
$ createuser -dRS  <username>
$ sudo su <usernae>
$ createdb poker

# set password for postgres user
$ psql 
postgres=> \password 
set password 

# finally start the server with app.py updated to use postgresql not sqlite
$ source env/bin/activate
now start server	
