import React, {useEffect, useState} from 'react'
import {Button, Card, Col, Container, Pagination, Row} from 'react-bootstrap'
import Moment from 'react-moment'
import {useDispatch, useSelector} from 'react-redux'
import {loadAllPhotos} from '../actions/photosActions'
import paginator from '../util/paginator'

const PhotosView = () => {

    const [page, setPage] = useState(1)
    const [pageSize, setPageSize] = useState(3)

    const dispatch = useDispatch()
    const allPhotos = useSelector(state => paginator(state.photos.items, page, pageSize).items)

    useEffect(() => dispatch(loadAllPhotos()), [dispatch])

    const changePage = e => {
        const page = e.target.offsetParent.getAttribute("data-page")
            ? e.target.offsetParent.getAttribute("data-page")
            : e.target.getAttribute("data-page")
        setPage(page)
    }

    return (
        <Container>
            <Pagination onClick={changePage}>
                <Pagination.First key={0} data-page={1}/>
                <Pagination.Prev key={1} data-page={+page - 1}/>
                <Pagination.Item key={2} data-page={+page - 1}>{+page - 1}</Pagination.Item>
                <Pagination.Item active key={3} data-page={page}>{+page}</Pagination.Item>
                <Pagination.Item key={4} data-page={+page + 1}>{+page + 1}</Pagination.Item>
                <Pagination.Next key={5} data-page={+page + 1}/>
                <Pagination.Last key={6} data-page={allPhotos.length / pageSize}/>
            </Pagination>
            <Row>
                {
                    allPhotos.map((photo, key) =>
                        <Col md={4} key={key}>
                            <Card>
                                <Card.Img variant="top" src={window.env.API_DOMAIN_ADDR + photo.url}/>
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
    )
}

export default PhotosView
