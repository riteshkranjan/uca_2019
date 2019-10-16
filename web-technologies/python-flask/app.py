from flask import Flask, render_template, redirect, request
from datetime import datetime
from flask_sqlalchemy import SQLAlchemy 

app = Flask(__name__)

db = SQLAlchemy(app)

class Student(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String)
    email = db.Column(db.String)
    gpa = db.Column(db.Float)
    date_inserted = db.Column(db.DateTime, default=datetime.now())
    def __init__(self, name, email, gpa):
        self.name = name
        self.email = email
        self.gpa = gpa

@app.route("/")
def welcome():
    s = db.session.query(Student).all()
    return render_template('index.html',students = s)

@app.route("/",methods=['POST'])
def add_student():
    s = Student(request.form.get("name"), request.form.get("email"),request.form.get("gpa"))
    db.session.add(s)
    db.session.commit()
    return redirect("/")

@app.route("/delete",methods=['POST'])
def delete_student():
    s = db.session.query(Student).get(request.form.get("id"))
    db.session.delete(s)
    db.session.commit()
    return redirect("/")

@app.route("/update",methods=['POST'])
def update_student():
    s = db.session.query(Student).get(request.form.get("id"))
    s.name = request.form.get("name")
    s.email = request.form.get("email")
    s.gpa = request.form.get("gpa")
    db.session.add(s)
    db.session.commit()
    return redirect("/")

if __name__=="__main__":
   app.config['DEBUG'] = True
   app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///student.db'
   db.create_all()
   app.run(port=3001)