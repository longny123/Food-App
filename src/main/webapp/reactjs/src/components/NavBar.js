import React, {useEffect} from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav'
import {Link} from "react-router-dom";
import {NavDropdown} from "react-bootstrap";
import {getUser} from "../redux/actions/UserAction";
import {connect} from "react-redux";


function NavBar(props){

    useEffect(() =>{
        props.getUser().then(r => {
            console.log(r)});

    },[])

    const logoutAction=()=>{
        localStorage.removeItem("User");
        window.location.reload();
    }

    const { user } = props;
    console.log(user)
        return(
            <div className="nav--main">
                <Navbar bg="dark" variant="dark">
                    <Navbar.Brand href="/">Food App</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Link className="nav-link" to="/">Home</Link>
                        {user.length === 0 ? (
                            <Link className="nav-link" to="/login">Log In</Link>
                        ):(
                            <NavDropdown title={user.firstName} id="basic-nav-dropdown">
                            <NavDropdown.Item ><Link className="link--navDropDown" to="/order/list">Order List</Link></NavDropdown.Item>
                            <NavDropdown.Item ><Link className="link--navDropDown" to="/order">Order</Link></NavDropdown.Item>
                            <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item onClick={()=>logoutAction()}>Log Out</NavDropdown.Item>
                            </NavDropdown>
                        )}

                        <Link className="nav-link" to="/admin">Admin</Link>
                    </Nav>
                </Navbar>
            </div>
        )
}

const mapStateToProps = (state) => ({
    user: state.user.user
});

const mapDispatchToProps = {
    getUser,
};
export default connect(mapStateToProps,mapDispatchToProps)(NavBar)