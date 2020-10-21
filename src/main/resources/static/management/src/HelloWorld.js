import React, {useEffect, useState} from 'react';
import {Col, Container, Image, Nav, Navbar, Row} from 'react-bootstrap';
import axios from 'axios';

const HelloWorld = () => {

    const [allPhotos, setData] = useState([]);

    useEffect(() => {

        const fetchData = async () => {
            const response = await axios.get('/api/management/v1/photos').then(response => setData(response.data)).catch(error=>{});
        }
        fetchData();
    }, []);

    return (
        <Container>
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
            </Container>
            <Row>
                {
                    allPhotos.map(photo => <Col md={4}> <Image fluid src={photo.url}/></Col>)
                }
            </Row>
        </Container>
    );
};

export default HelloWorld;
