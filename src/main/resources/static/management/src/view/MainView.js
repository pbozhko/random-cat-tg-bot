import React from 'react';
import {Container, Nav, Navbar} from 'react-bootstrap';

import LoginPage from '../login/LoginPage';

const MainView = () => {

    return (
        <Container>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href="#photos">Photos</Nav.Link>
                        <Nav.Link href="#accounts">Accounts</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
            <LoginPage/>
        </Container>
    );
};

export default MainView;
