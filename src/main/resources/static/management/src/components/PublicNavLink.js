import React from "react"
import {Link} from "react-router-dom"
import {Nav} from 'react-bootstrap'

const PublicNavLink = ({ ...rest }) => {

    return <Nav.Link as={Link} {...rest}/>
}

export default PublicNavLink
