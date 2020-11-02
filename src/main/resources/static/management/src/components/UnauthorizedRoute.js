import React, {Component} from "react"
import {Redirect, Route, useLocation} from "react-router-dom"
import {fakeAuth} from '../auth'

const UnauthorizedRoute = ({ component: Component, ...rest }) => {

    return (
        <Route {...rest}>
            {
                fakeAuth.isAuthenticated !== true ?
                    <Component/> :
                    <Redirect to={{ pathname: "/", state: { from: useLocation() } }}/>
            }
        </Route>
    )
}

export default UnauthorizedRoute
