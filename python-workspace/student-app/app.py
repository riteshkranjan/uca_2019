from flask import Flask,jsonify
from flask_sqlalchemy  import SQLAlchemy
app = Flask(__name__)
db = SQLAlchemy(app)

@app.route("/")
def welcome():
    return "hello"

@app.route("/addstudent/<name>/<branch>/<cgpa>")
def add_student(name, branch, cgpa):
    s = Student()
    s.name = name
    s.cgpa = cgpa
    s.branch = branch
    db.session.add(s)
    db.session.commit()
    return "data added successfully"

@app.route("/updatestudent/<id>/<newname>/<newbranch>/<newcgpa>")
def update_student(id, newname, newbranch, newcgpa):
    s = Student.query.filter_by(id=id).first()
    s.name = newname
    s.cgpa = newcgpa
    s.branch = newbranch
    db.session.add(s)
    db.session.commit()
    return "data updated successfully"

@app.route("/deletestudent/<id>")
def delete_student(id):
    s = Student.query.filter_by(id=id).first()
    db.session.delete(s)
    db.session.commit()
    return "data deleted successfully"

@app.route("/getstudent/<id>")
def get_student(id):
    s = Student.query.filter_by(id=id).first()
    if s is None:
        return "None"
    return get_studentjson(s)

def get_studentjson(s):
    return jsonify({'name':s.name,'cgpa':s.cgpa, 'branch':s.branch})

class Student(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String)
    cgpa = db.Column(db.Float)
    branch = db.Column(db.String)

class Teacher(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String)
    faculty = db.Column(db.String)

if __name__ == "__main__":
    app.config['DEBUG'] = True
    app.config['SQLALCHEMY_DATABASE_URI'] = "postgresql://postgres:password@localhost:5432/college"
    #'sqlite:///test.db'
    db.create_all()
    app.run(port=8080)