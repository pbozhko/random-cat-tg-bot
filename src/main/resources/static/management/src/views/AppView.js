import React from 'react'
import {Container, Jumbotron, Nav, Navbar} from 'react-bootstrap'
import {Redirect, Route, Switch, useLocation} from 'react-router-dom'

import LoginView from './LoginView'
import PhotosView from './PhotosView'
import PrivateRoute from '../components/PrivateRoute'
import TableView from './TableView'
import AuthorizedNavLink from '../components/AuthorizedNavLink'
import HomeView from './HomeView'
import PublicNavLink from '../components/PublicNavLink'
import UnauthorizedNavLink from '../components/UnauthorizedNavLink'
import NotFoundView from './NotFoundView'
import AccountsView from './AccountsView'
import LogoutView from './LogoutView'

const AppView = () => {

    const { pathname } = useLocation()

    return (
        <Container>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <PublicNavLink to="/">Главная</PublicNavLink>
                        <AuthorizedNavLink to="/photos">Фотографии</AuthorizedNavLink>
                        <AuthorizedNavLink to="/accounts">Аккаунты</AuthorizedNavLink>
                        <AuthorizedNavLink to="/table">Табличка</AuthorizedNavLink>
                    </Nav>
                    <Nav>
                        <UnauthorizedNavLink to="/login">Войти</UnauthorizedNavLink>
                        <AuthorizedNavLink to="/logout">Выйти</AuthorizedNavLink>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
            <Jumbotron fluid>
                <Switch>
                    <Redirect from="/:url*(/+)" to={pathname.slice(0, -1)}/>
                    <Route exact path="/" component={HomeView}/>
                    <Route exact path="/login" component={LoginView}/>
                    <Route exact path="/photos" component={PhotosView}/>
                    <Route exact path="/accounts" component={AccountsView}/>
                    <PrivateRoute exact path="/table" component={TableView}/>
                    <PrivateRoute exact path="/logout" component={LogoutView}/>
                    <Route component={NotFoundView}/>
                </Switch>
            </Jumbotron>
        </Container>
    )
}

export default AppView
