import React, {Component} from 'react';
import { connect } from "react-redux";
import { Formik, Field, Form } from 'formik';
import {fetchUser} from "../redux/actions/UserAction"
import {Button} from "react-bootstrap";
import {Redirect} from "react-router-dom";

class LoginPage extends Component {
    constructor(props) {
        super(props);
    };
        render() {
            if(this.props.user.user.length!==0){
                return <Redirect to={"/"}/>;
                window.location.reload();
            }
                return (
                    <div className="login__content">
                        <Formik initialValues={{
                            email: '',
                            password: ''
                        }}
                                onSubmit={
                                    (e) => {
                                        this.props.fetchUser(e)
                                    }
                                }
                        >
                            {({
                                  values,
                                  handleChange,
                                  /* and other goodies */
                              }) => (
                                <Form className="form__login">
                                    <a>Email</a>
                                    <input
                                        id="email"
                                        name="email"
                                        placeholder="jane@acme.com"
                                        type="email"
                                        onChange={handleChange}
                                        value={values.email}
                                    />
                                    <a>Password</a>
                                    <input
                                        id="password"
                                        name="password"
                                        placeholder="password"
                                        type="password"
                                        onChange={handleChange}
                                        value={values.password}
                                    />
                                    <Button className="button__submit" variant="primary" type="submit">Submit</Button>
                                </Form>
                            )}
                        </Formik>
                    </div>
                );
            }


    }
const mapStateToProps = (state) => ({
    user: state.user,
    redirect: state.redirect
});

const mapDispatchToProps = {
    fetchUser,
};
export default connect(mapStateToProps,mapDispatchToProps)(LoginPage);