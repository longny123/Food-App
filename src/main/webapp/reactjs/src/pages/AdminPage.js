import React, {useEffect} from 'react'
import {Nav, Navbar} from "react-bootstrap";
import AdminNavBar from "../components/AdminNavBar";
import AdminUserModalView from "../components/AdminUserModalView";
import AdminProductModalView from "../components/AdminProductModalView";
import {getUser} from "../redux/actions/UserAction";
import {connect} from "react-redux";
import AdminHomeModalView from "../components/AdminHomeModalView";

const AdminPage = (props) => {
    useEffect(() =>{
        props.getUser().then(r => {
            console.log(r)});

    },[])
    const { user } = props;
    return(
        <div className="admin--container">
            <header>
                <Navbar bg="primary" variant="dark">
                    <Navbar.Brand href="/admin">ADMIN PAGE</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                    </Nav>
                    <Nav.Item>
                        <Nav.Link eventKey="disabled" disabled className="label--userAdmin">
                            Hello {user.firstName}
                        </Nav.Link>
                    </Nav.Item>
                </Navbar>

            </header>
            <main>
                <AdminNavBar/>
                {(localStorage.getItem("switchPage") === "User") ? (
                    <AdminUserModalView/>
                ) : (localStorage.getItem("switchPage") === "Product") ?(
                    <AdminProductModalView/>
                ) : (localStorage.getItem("switchPage") === "Home") ? (
                    <AdminHomeModalView/>
                ) :(
                    <a>Data not found</a>
                )}
            </main>
        </div>
    )
}

const mapStateToProps = (state) => ({
    user: state.user.user,
    listUser: state.user.listUser
});

const mapDispatchToProps = {
    getUser,
};
export default connect(mapStateToProps,mapDispatchToProps)(AdminPage)