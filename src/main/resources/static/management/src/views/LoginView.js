import React, {useState} from 'react'
import {Redirect, useLocation} from 'react-router-dom'

import {Button, Col, Container, Form, Image, Row} from 'react-bootstrap'

import LOGO from '../../assets/img/logo.png'
import {fakeAuth} from '../auth'

const LoginView = () => {

    const { state } = useLocation()
    const { from } = state || { from: { pathname: "/" } }
    const [redirectToReferrer, setRedirectToReferrer] = useState(false)

    const login = (e) => {
        fakeAuth.authenticate(() => {
            setRedirectToReferrer(true)
        })
    }

    if (redirectToReferrer) {

        return <Redirect to={from} />
    }

    return (
        <Container fluid>
            <Row>
                <Col lg={{ span: 2, offset: 5 }} md={{ span: 4, offset: 4 }} xs={{ span: 6, offset: 3 }}>
                    <Image src={LOGO} fluid/>
                </Col>
            </Row>
            <Row>
                <Col lg={{ span: 4, offset: 4 }} md={{ span: 8, offset: 2 }}>
                    <Form>
                        <Form.Group>
                            <Form.Label>Логин</Form.Label>
                            <Form.Control type="text" required/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Пароль</Form.Label>
                            <Form.Control type="password" required/>
                        </Form.Group>

                        <Button block variant="success" type="button" size="lg" onClick={login}>Войти</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    )
}

export default LoginView
