import React from "react"
import {Link} from "react-router-dom"
import {fakeAuth} from '../view/Login'
import {Nav} from 'react-bootstrap'

const UnauthorizedLink = ({ ...rest }) => {

    return (
        fakeAuth.isAuthenticated !== true ?
            <Nav.Link as={Link} {...rest}/> :
            <React.Fragment/>
    )
}

export default UnauthorizedLink
