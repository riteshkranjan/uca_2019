import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class Navbar extends Component {
    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <h4 className="navbar-brand"><Link to='/'>Students Portal</Link></h4>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <h5 className="nav-item nav-link"><Link to='/add'>Add Student</Link></h5>
                        <h5 className="nav-item nav-link">Total No. of students: <span className="badge badge-primary">{this.props.count}</span> </h5>
                    </div>
                </div>
            </nav>
        )
    }
}

export default Navbar;