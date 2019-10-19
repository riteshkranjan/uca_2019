import React, { Component } from 'react';

class AddStudent extends Component {
    state = {
        name: '',
        gpa: '',
        email: ''
    }
    constructor() {
        super();
        console.info("Add student constructor called");
    }

    handleNameChange = (event) => {
        this.setState({ name: event.target.value })
        console.log(`Curent state while typing: ${this.state}`)
    }

    handleGPAChange = (event) => {
        this.setState({ gpa: event.target.value })
        console.log(`Curent state while typing: ${this.state}`)
    }

    handleEMailChange = (event) => {
        this.setState({ email: event.target.value })
        console.log(`Curent state while typing: ${this.state}`)
    }

    addStudent = (event) => {
        event.preventDefault();
        console.log(`The new student is: ${JSON.stringify(this.state)}`)
        this.props.addStudent(this.state);
    }

    render() {
        return (
            <div className="container">
                <form onSubmit={this.addStudent}>
                    <div className="form-row">
                        <div className="form-group col-md-8">
                            <label>Name</label>
                            <input type="text" value={this.state.name} className="form-control" placeholder="Name" onChange={this.handleNameChange} />
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-8">
                            <label>GPA</label>
                            <input type="text" value={this.state.gpa} className="form-control" placeholder="GPA" onChange={this.handleGPAChange} />
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-8">
                            <label>Email</label>
                            <input type="text" value={this.state.email} className="form-control" placeholder="Email" onChange={this.handleEMailChange} />
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        )
    }
}

export default AddStudent;