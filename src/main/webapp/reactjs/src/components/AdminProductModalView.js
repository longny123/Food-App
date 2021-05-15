import {Dropdown, DropdownButton, Pagination, Table} from 'react-bootstrap'
import {getListProduct} from "../redux/actions/UserAction";
import {connect} from "react-redux";
import {useEffect} from "react";

const AdminProductModalView = (props) => {

    useEffect(() => {
        props.getListProduct().then(r => {
            console.log(r);
        })
    }, []);


    console.log(props)
    return (
        <div>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Start date</th>
                        <th>Status</th>
                        <th>Filling</th>
                        <th>Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                    {props.list?.map((item, key) => (
                        <tr key={key}>
                            <td>{item.id}</td>
                            <td>{item.startDate}</td>
                            <td>{item.status}</td>
                            <td>
                                {item.burgers.map(burger =>{
                                    return(
                                        <DropdownButton title="Burger">
                                            {burger.filling.map(fill =>{
                                                return(
                                                    <Dropdown.Item>{fill.filling.name}</Dropdown.Item>
                                                )
                                            })}
                                        </DropdownButton>)
                                })}
                            </td>

                            <td> {item.burgers.map(burger =>{
                                return(
                                    <DropdownButton title="Quantity">
                                        {burger.filling.map(fill =>{
                                            return(
                                                <Dropdown.Item>{fill.quantity}</Dropdown.Item>
                                            )
                                        })}
                                    </DropdownButton>)
                            })}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
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
    list: state.user.products
});

const mapDispatchToProps = {
    getListProduct,
};
export default connect(mapStateToProps, mapDispatchToProps)(AdminProductModalView)