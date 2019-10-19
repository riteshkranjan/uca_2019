import React, { Component } from 'react';
import Navbar from './navbar'
import StudentList from './studentList';
import AddStudent from './addStudent';
import EditStudent from './editStudent';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

class App extends Component {

    state = {
        studentList: []
    }
    constructor() {
        super()
        // TODO 
    }

    componentDidMount() {
        this.fetchStudents();
    }

    fetchStudents() {
        fetch("http://18.224.60.213:3001/student")
            .then(response => response.json())
            .then(data => {
                console.table(data);
                this.setState({ studentList: data })
            })
            .catch(error => {
                console.error("Error fetching data ", error);
            })
    }

    addStudent(student) {
        console.log("Add student called from parent")
        fetch(`http://18.224.60.213:3001/student`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(student)
        })
            .then(response => response.json())
            .then(data => {
                console.table(data);
                window.alert("Student added suucessfully");
            })
            .catch(error => {
                console.error("Error fetching data ", error);
            })
    }

    sendStudent = (student) => {
        this.setState({ selectedStudentForEdit: student });
    }

    editStudent(student) {
        console.log("Esit student called from parent")
        fetch(`http://18.224.60.213:3001/student/${student._id}`, {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(student)
        })
            .then(response => response.json())
            .then(data => {
                console.table(data);
                window.alert("Student updated suucessfully");
            })
            .catch(error => {
                console.error("Error fetching data ", error);
            })
    }

    deleteStudent(student) {
        console.log("Delete student called from parent")
        fetch(`http://18.224.60.213:3001/student/${student._id}`, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                console.table(data);
                window.alert("Student deleted suucessfully");
            })
            .catch(error => {
                console.error("Error fetching data ", error);
            })
    }

    render() {
        return (
            <div className="container-fluid">
                <Router>
                    <Navbar count={this.state.studentList.length} />
                    <Route exact path="/" render={(props) => <StudentList studentList={this.state.studentList} sendStudent={this.sendStudent} deleteStudent={this.deleteStudent} />} />
                    <Route path="/add" render={(props) => <AddStudent addStudent={this.addStudent} />} />
                    <Route path="/edit" render={(props) => <EditStudent student={this.state.selectedStudentForEdit} editStudent={this.editStudent} />} />
                </Router>
            </div>
        )
    }
}

export default App;