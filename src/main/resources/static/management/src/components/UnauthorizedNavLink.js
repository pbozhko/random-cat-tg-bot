import React from "react"
import {Link} from "react-router-dom"
import {fakeAuth} from '../auth'
import {Nav} from 'react-bootstrap'

const UnauthorizedNavLink = ({ ...rest }) => {

    return (
        fakeAuth.isAuthenticated !== true ?
            <Nav.Link as={Link} {...rest}/> :
            <React.Fragment/>
    )
}

export default UnauthorizedNavLink
