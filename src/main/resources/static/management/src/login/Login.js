import React, {useState} from 'react';
import {GoogleReCaptcha, GoogleReCaptchaProvider} from 'react-google-recaptcha-v3';
import {Button, Col, Container, Form, Image, Row} from 'react-bootstrap';

import LOGO from '../../assets/img/logo.png';

const Login = () => {

    const [isRecaptchaPassed, passRecaptcha] = useState(false);

    return (
        <Container style={{ "padding-top": "10vh" }} fluid>
            <Row>
                <Col lg={{ span: 2, offset: 5 }} md={{ span: 4, offset: 4 }} xs={{ span: 6, offset: 3 }}>
                    <Image src={LOGO} fluid/>
                </Col>
            </Row>
            <Row>
                <Col lg={{ span: 4, offset: 4 }} md={{ span: 8, offset: 2 }}>
                    <Form>
                        <Form.Group>
                            <Form.Label>Login</Form.Label>
                            <Form.Control type="text" required/>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" required/>
                        </Form.Group>

                        <Form.Group>
                            <GoogleReCaptchaProvider
                                reCaptchaKey="6LeCatwZAAAAAKHgoGNHXrVcfWBAJyjqMJH65xhQ"
                                useRecaptchaNet="true"
                            >
                                <GoogleReCaptcha onVerify={passRecaptcha}/>
                            </GoogleReCaptchaProvider>
                        </Form.Group>

                        <Button block variant="success" type="submit" size="lg"
                                disabled={!isRecaptchaPassed}>Submit</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default Login;
