import React from "react"
import {Link} from "react-router-dom"
import {Nav} from 'react-bootstrap'
import {fakeAuth} from '../view/Login'

const AuthorizedLink = ({ ...rest }) => {

    return (
        fakeAuth.isAuthenticated === true ?
            <Nav.Link as={Link} {...rest}/> :
            <React.Fragment/>
    )
}

export default AuthorizedLink
