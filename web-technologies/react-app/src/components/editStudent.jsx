import React, { Component } from 'react';

class EditStudent extends Component {
    state = {
        name: '',
        gpa: '',
        email: '',
        _id: ''
    }
    constructor() {
        super();
        console.info("Add student constructor called");
    }

    componentDidMount() {
        this.setState({
            name: this.props.student.name,
            gpa: this.props.student.gpa,
            email: this.props.student.email,
            _id: this.props.student._id
        })
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

    editStudent = (event) => {
        event.preventDefault();
        console.log(`The new student is: ${JSON.stringify(this.state)}`)
        this.props.editStudent(this.state);
    }

    render() {
        return (
            <div className="container">
                <form onSubmit={this.editStudent}>
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputEmail4">Name</label>
                            <input type="text" value={this.state.name} className="form-control" placeholder="Name" onChange={this.handleNameChange} />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputPassword4">GPA</label>
                            <input type="text" value={this.state.gpa} className="form-control" placeholder="GPA" onChange={this.handleGPAChange} />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-8">
                            <label for="inputAddress">Email</label>
                            <input type="text" value={this.state.email} className="form-control" placeholder="Email" onChange={this.handleEMailChange} />
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>


                </form>
            </div>
        )
    }
}

export default EditStudent;