import React, {useEffect, useState} from 'react';
import {Card, Col, Container, Nav, Navbar, Row} from 'react-bootstrap';
import axios from 'axios';

const HelloWorld = () => {

    const [allPhotos, setData] = useState([]);

    useEffect(() => {

        const fetchData = async () => {
            const response = await axios.get('https://random-cat-tg-bot.herokuapp.com/api/management/v1/photos').then(response => setData(response.data)).catch(error => {
            });
        }
        fetchData();
    }, []);

    return (
        <Container>
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href="#accounts">Accounts</Nav.Link>
                        <Nav.Link href="#photos">Photos</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
            <Row>
                {
                    allPhotos.map(photo =>
                        <Col md={4}>
                            <Card>
                                <Card.Img variant="top" src={photo.url} />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the bulk of
                                        the card's content.
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    )
                }
            </Row>
        </Container>
    );
};

export default HelloWorld;
