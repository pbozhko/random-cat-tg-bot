import React from 'react'
import {Container, Jumbotron, Nav, Navbar} from 'react-bootstrap'
import {Redirect, Route, Switch, useLocation} from 'react-router-dom'

import LoginPage from '../login/LoginPage'
import PhotosView from '../PhotosView'
import PrivateRoute from './PrivateRoute'
import TablePage from './TablePage'
import AuthorizedLink from '../components/AuthorizedLink'
import Home from './Home'
import PublicLink from '../components/PublicLink'
import UnauthorizedLink from '../components/UnauthorizedLink'
import NotFoundView from './NotFoundView'

const App = () => {

    const { pathname } = useLocation()

    return (
        <Container>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <PublicLink to="/">Главная</PublicLink>
                        <AuthorizedLink to="/photos">Фотографии</AuthorizedLink>
                        <AuthorizedLink to="/accounts">Аккаунты</AuthorizedLink>
                        <AuthorizedLink to="/table">Табличка</AuthorizedLink>
                    </Nav>
                    <Nav>
                        <UnauthorizedLink to="/login">Войти</UnauthorizedLink>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
            <Jumbotron fluid>
                <Switch>
                    <Redirect from="/:url*(/+)" to={pathname.slice(0, -1)}/>
                    <Route exact path="/" component={Home}/>
                    <Route exact path="/login" component={LoginPage}/>
                    <Route exact path="/photos" component={PhotosView}/>
                    <PrivateRoute exact path="/table" component={TablePage}/>
                    <Route component={NotFoundView}/>
                </Switch>
            </Jumbotron>
        </Container>
    )
}

export default App
