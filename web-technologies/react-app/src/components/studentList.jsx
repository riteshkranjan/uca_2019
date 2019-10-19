import React, { Component } from 'react';
import './student.css';
import { Link } from 'react-router-dom'
class StudentList extends Component {
    state = {
        count: 20,
        studentList: []
    }

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        // TODO
        console.log("Prop in studentsListis: ", this.props)
    }

    sendStudent(student) {
        console.log(this.props);
        console.table(`The student is : ${student.name}`)
        this.props.sendStudent(student)
    }

    deleteStudent(student) {
        this.props.deleteStudent(student)
    }

    render() {
        return (
            <div className="m-4">
                <table className="table col">
                    <thead className="thead-dark">
                        <tr>
                            <th>Name</th>
                            <th>GPA</th>
                            <th>Email</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.props.studentList.map(student =>
                            <tr key={student._id}>
                                <td>{student.name}</td>
                                <td>{student.gpa}</td>
                                <td>{student.email}</td>
                                <td>
                                    <div className="row">
                                        <div className="col-2">
                                            <h5 onClick={() => this.sendStudent(student)}><Link to='/edit'>Edit</Link></h5>
                                        </div>
                                        <div className="col">
                                            <h5 onClick={() => this.deleteStudent(student)}><Link to=''>Delete</Link></h5>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default StudentList;