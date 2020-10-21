import React, {useEffect, useState} from 'react';
import {Button, Card, Col, Container, Nav, Navbar, Row} from 'react-bootstrap';
import axios from 'axios';
import Moment from 'react-moment';

const HelloWorld = () => {

    const [allPhotos, setAllPhotos] = useState([]);

    useEffect(() => {

        const fetchData = async () => {
            await axios.get('http://localhost:8080/api/management/v1/photos');
        };

        const deletePhoto = async id => {
            await axios.delete('http://localhost:8080/api/management/v1/photos/' + id);
        };

        fetchData()
            .then(response => setAllPhotos(response.data))
            .catch(error => true);
    }, []);

    const deletePhoto = id => {
        deletePhoto(id);
        fetchData();
    };

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
            <Row>
                {
                    allPhotos.map((photo, key) =>
                        <Col md={4} key={key}>
                            <Card>
                                <Card.Img variant="top" src={"http://localhost:8080" + photo.url}/>
                                <Card.Body>
                                    <b>Дата создания:</b> <Moment date={photo.createdAt} format="DD.MM.YYYY HH:mm:ss"/>
                                    <Button variant={"danger"} size={"sm"}
                                            onClick={() => deletePhoto(photo.id)}>Удалить</Button>
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
