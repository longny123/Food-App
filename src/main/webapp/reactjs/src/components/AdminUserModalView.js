import {Pagination, Table, Modal, Button, Form, Card} from 'react-bootstrap'
import {createNewUser, deleteUserWithId, editUser, getListUser, updateUser} from "../redux/actions/UserAction";
import {connect} from "react-redux";
import {useEffect, useState} from "react";
import {Formik} from "formik"
import Swal from "sweetalert2"

const AdminUserModalView = (props) => {
    const [showEdit, setShowEdit] = useState({show: false, data: []})
    // const [showDelete, setShowDelete] = useState({show: false, data: []})
    const [showCreate, setShowCreate] = useState({show: false})
    useEffect(() => {
        props.getListUser().then(r => {
            console.log(r);
        })
    }, []);
    return (
        <div>
            <Card>
                <Card.Header>
                   <strong> User manager </strong>
                   <Button variant="primary" className="button--add-user" onClick={()=> setShowCreate({show: true})}>Add user<i className="fas fa-user-plus" /></Button>
                </Card.Header>
                <Card.Body>
                    <Card.Title>Total Of User</Card.Title>
                    <Table striped bordered hover>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>User ID</th>
                            <th>Option</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.list?.map((item, key) => (
                            <tr key={key}>
                                <td>{item.id}</td>
                                <td>{item.firstName}</td>
                                <td>{item.lastName}</td>
                                <td>{item.email}</td>
                                <td>{item.role}</td>
                                <td>{item.userId}</td>
                                <td>
                                    <i className="fas fa-user-edit" onClick={()=> setShowEdit({show: true, data: item})}/>
                                    <i className="fas fa-trash" onClick={()=> Swal.fire({
                                        title: 'Are you sure?',
                                        text: "You won't be able to revert this!",
                                        icon: 'warning',
                                        showCancelButton: true,
                                        confirmButtonColor: '#3085d6',
                                        cancelButtonColor: '#d33',
                                        confirmButtonText: 'Yes, delete it!'
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            props.deleteUserWithId(item.userId)
                                            Swal.fire(
                                                'Deleted!',
                                                'Your file has been deleted.',
                                                'success'
                                            )
                                        }
                                    })}/>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                        <Modal show={showEdit.show} onHide={() => setShowEdit({...showEdit,show : false})}>
                            <Modal.Header closeButton>
                                <Modal.Title>Update User</Modal.Title>
                            </Modal.Header>
                            <Formik initialValues={{
                                firstName: showEdit.data.firstName,
                                lastName: showEdit.data.lastName,
                                userId: showEdit.data.userId
                            }}
                                    validate={values => {
                                        const err = {};
                                        if (!values.firstName) {
                                            err.firstName = 'Required'
                                        }
                                        if (!values.lastName){
                                            err.lastName = 'Required'
                                        }
                                        return err;
                                    }}
                                    onSubmit = {value => {
                                        props.updateUser(value.firstName, value.lastName, value.userId)
                                        window.location.reload();
                                    }}
                            >
                                {({values,errors,handleChange,handleBlur,touched,handleSubmit})=>(
                                    <Form onSubmit={handleSubmit}>
                                        <Modal.Body>
                                            <Form.Group >
                                                <Form.Label>First Name</Form.Label>
                                                <Form.Control type="text" defaultValue={values.firstName} value={values.firstName} name="firstName" onChange={handleChange} onBlur={handleBlur} placeholder="Enter First Name"  isInvalid={!!errors.firstName && touched.firstName}/>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors.firstName}
                                                </Form.Control.Feedback>
                                            </Form.Group>

                                            <Form.Group>
                                                <Form.Label>Last Name</Form.Label>
                                                <Form.Control type="text" defaultValue={values.lastName} value={values.lastName} name="lastName" onChange={handleChange} onBlur={handleBlur}  placeholder="Enter Last Name" isInvalid={!!errors.lastName && touched.lastName}/>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors.lastName}
                                                </Form.Control.Feedback>
                                            </Form.Group>


                                        </Modal.Body>
                                        <Modal.Footer>
                                            <Button variant="secondary" onClick={() => setShowEdit({...showEdit,show : false})}>
                                                Close
                                            </Button>
                                            <Button variant="primary" type="submit">
                                                Save Changes
                                            </Button>
                                        </Modal.Footer>
                                    </Form>
                                )}
                            </Formik>
                        </Modal>

                        {/*<Modal show={showDelete.show} onHide={() => setShowDelete({...showDelete,show : false})}>*/}
                        {/*    <Modal.Header closeButton>*/}
                        {/*        <Modal.Title>Confirm</Modal.Title>*/}
                        {/*    </Modal.Header>*/}
                        {/*    <Modal.Body>*/}
                        {/*        <p>Are you want to delete this user?</p>*/}
                        {/*    </Modal.Body>*/}

                        {/*    <Modal.Footer>*/}
                        {/*        <Formik*/}
                        {/*            initialValues={{ userId: showDelete.data.userId}}*/}
                        {/*            onSubmit = {value => {*/}
                        {/*                props.deleteUserWithId(value.userId)*/}
                        {/*                window.location.reload()*/}
                        {/*            }}*/}
                        {/*        >*/}
                        {/*            {({handleSubmit}) => (*/}
                        {/*                <Form onSubmit={handleSubmit}>*/}
                        {/*                    {console.log(showDelete.data)}*/}
                        {/*                    <Button variant="secondary" onClick={() => setShowDelete({...showEdit,show : false})}>*/}
                        {/*                        Close*/}
                        {/*                    </Button>*/}
                        {/*                    <Button variant="primary" type="submit" >Save changes</Button>*/}
                        {/*                </Form>*/}
                        {/*            )}*/}
                        {/*        </Formik>*/}
                        {/*    </Modal.Footer>*/}
                        {/*</Modal>*/}
                    </Table>
                </Card.Body>
            </Card>
                <Modal show={showCreate.show} onHide={() => setShowCreate({...showCreate,show : false})}>
                    <Modal.Header closeButton>
                        <Modal.Title>Create User</Modal.Title>
                    </Modal.Header>
                    <Formik initialValues={{
                        firstName: "",
                        lastName: "",
                        email: "",
                        password: ""
                    }}
                            validate={values => {
                                const err = {};
                                if (!values.firstName) {
                                    err.firstName = 'Required'
                                }
                                if (!values.lastName){
                                    err.lastName = 'Required'
                                }
                                if(!values.email){
                                    err.email = 'Required'
                                } else if (
                                    !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
                                ) {
                                    err.email = 'Invalid email address';
                                }
                                if(!values.password){
                                    err.password = 'Required'
                                }
                                return err;
                            }}
                            onSubmit = {value => {
                                props.createNewUser(value.firstName, value.lastName, value.email, value.password)
                                window.location.reload()
                            }}
                    >
                        {({values,errors,handleChange,handleBlur,touched,handleSubmit})=>(
                            <Form onSubmit={handleSubmit}>
                                <Modal.Body>
                                    <Form.Group >
                                        <Form.Label>First Name</Form.Label>
                                        <Form.Control type="text" defaultValue={values.firstName} value={values.firstName} name="firstName" onChange={handleChange} onBlur={handleBlur} placeholder="Enter First Name"  isInvalid={!!errors.firstName && touched.firstName}/>
                                        <Form.Control.Feedback type="invalid">
                                            {errors.firstName}
                                        </Form.Control.Feedback>
                                    </Form.Group>

                                    <Form.Group>
                                        <Form.Label>Last Name</Form.Label>
                                        <Form.Control type="text" defaultValue={values.lastName} value={values.lastName} name="lastName" onChange={handleChange} onBlur={handleBlur}  placeholder="Enter Last Name" isInvalid={!!errors.lastName && touched.lastName}/>
                                        <Form.Control.Feedback type="invalid">
                                            {errors.lastName}
                                        </Form.Control.Feedback>
                                    </Form.Group>

                                    <Form.Group>
                                        <Form.Label>Email</Form.Label>
                                        <Form.Control type="email" value={values.email} name="email" onChange={handleChange} onBlur={handleBlur}  placeholder="Enter Email" isInvalid={!!errors.email && touched.email}/>
                                        <Form.Control.Feedback type="invalid">
                                            {errors.email}
                                        </Form.Control.Feedback>
                                    </Form.Group>

                                    <Form.Group>
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control type="password"  value={values.password} name="password" onChange={handleChange} onBlur={handleBlur}  placeholder="Enter Password" isInvalid={!!errors.password && touched.password}/>
                                        <Form.Control.Feedback type="invalid">
                                            {errors.password}
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button variant="secondary" onClick={() => setShowCreate({...showCreate,show : false})}>
                                        Close
                                    </Button>
                                    <Button variant="primary" type="submit">
                                        Confirm
                                    </Button>
                                </Modal.Footer>
                            </Form>
                        )}
                    </Formik>
                </Modal>
            <Pagination>
                <Pagination.First/>
                <Pagination.Prev/>
                <Pagination.Item active>{1}</Pagination.Item>
                <Pagination.Ellipsis/>

                <Pagination.Item>{10}</Pagination.Item>
                <Pagination.Item>{11}</Pagination.Item>
                <Pagination.Item>{12}</Pagination.Item>
                <Pagination.Item>{13}</Pagination.Item>
                <Pagination.Item>{14}</Pagination.Item>

                <Pagination.Ellipsis/>
                <Pagination.Item>{20}</Pagination.Item>
                <Pagination.Next/>
                <Pagination.Last/>
            </Pagination>
        </div>
    )
}
const mapStateToProps = (state) => ({
    user: state.user.user,
    list: state.user.listUser,
    userDetails: state.user.userDetails
});

const mapDispatchToProps = {
    getListUser,
    updateUser,
    deleteUserWithId,
    createNewUser
};
export default connect(mapStateToProps, mapDispatchToProps)(AdminUserModalView)