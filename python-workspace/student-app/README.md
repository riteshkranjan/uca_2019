for sqllite (in memory database):
app.config['SQLALCHEMY_DATABASE_URI'] = "sqlite:///college.db"

for postgre (windows):
go to location where postgres is installed (in c:/program files....)
go to bin folder
run: psql -U postgres 
enter password: 
you are now in postgres shell: postgres=#
type \l to list databases 
postgres=# \l
postgres=# create database college  
postgres=# \l   => will not show college db in the list
postgres=# \c college  => connect to college db
college=# \d   => prints all tables in it. currently empty
run app.py now in other terminal. come back here and do \d again. student and teacher tables should be here now

Below are end points (all are http get method for now)
* add a new student: http://localhost:8080/addstudent/ritesh/cs/4.0
* update student with id 1 : http://localhost:8080/updatestudent/1/ritesh/cs/4.0
* delete a student by id : http://localhost:8080/deletestudent/3
* get a student by id : http://localhost:8080/getstudent/2

