import React, {Component} from "react";
import {Redirect, Route, useLocation} from "react-router-dom";
import {fakeAuth} from './Login';

const PrivateRoute = ({ component: Component, ...rest }) => {

    return (
        <Route {...rest}>
            {
                fakeAuth.isAuthenticated === true ?
                    <Component/> :
                    <Redirect to={{ pathname: "/login", state: { from: useLocation() } }}/>
            }
        </Route>
    );
};

export default PrivateRoute;
