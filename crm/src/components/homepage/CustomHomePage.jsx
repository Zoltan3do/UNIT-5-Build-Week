import { Col, Container, Row } from "react-bootstrap";
import CustomNavbar from "./CustomNavbar";
import Dashboard from "./Dashboard";
import ListData from "./ListData";
import { useState } from "react";


const CustomHomePage = () => {

    const [queryParams, setQueryParams] = useState("")

    return (
        <>
            <Container fluid>
                <Row className="d-flex align-items-center flex-column justify-content-center">
                    <Col lg={6}>
                        <CustomNavbar queryParams={queryParams} setQueryParams={setQueryParams} />
                    </Col>
                    <Col lg={6} className="py-5">
                        <Dashboard />
                    </Col>
                    <Col lg={6} className="py-2">
                        <ListData queryParams={queryParams} setQueryParams={setQueryParams} />
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CustomHomePage;