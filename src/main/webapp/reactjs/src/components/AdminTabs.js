import {Table} from "react-bootstrap";

const AdminTabs = (props) =>{
    console.log(props)
    return(
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
                {/*{props.listUser?.map((item, key) => (*/}
                {/*    <tr key={key}>*/}
                {/*        <td>{item.id}</td>*/}
                {/*        <td>{item.firstName}</td>*/}
                {/*        <td>{item.lastName}</td>*/}
                {/*        <td>{item.email}</td>*/}
                {/*        <td>{item.role}</td>*/}
                {/*    </tr>*/}
                {/*))}*/}
            </tbody>
        </Table>
    )
}
export default AdminTabs();