import {Nav} from "react-bootstrap";
import {getListUser} from "../redux/actions/UserAction";
import {connect} from "react-redux";

const handlerSelect = (eventKey) => {
    localStorage.setItem("switchPage", eventKey)
    window.location.reload();
}

const AdminNavBar = () =>{
    return(
        <Nav fill variant="tabs" defaultActiveKey="/home" onSelect={handlerSelect} >
            <Nav.Item>
                <Nav.Link eventKey="Home" >Home Page</Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link eventKey="User">User</Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link eventKey="Product">Product</Nav.Link>
            </Nav.Item>
            <Nav.Item>
                <Nav.Link eventKey="disabled" disabled>
                    Coming soon
                </Nav.Link>
            </Nav.Item>
        </Nav>
    )
}
const mapStateToProps = (state) => ({
    list: state.user.listUser
});

const mapDispatchToProps = {
    getListUser,
};
export default connect(mapStateToProps, mapDispatchToProps)(AdminNavBar)