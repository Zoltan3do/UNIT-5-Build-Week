import { Col, Container, Row } from "react-bootstrap";
import CustomNavbar from "./CustomNavbar";


const CustomHomePage = () => {
    return (
        <>
            <Container fluid>
                <Row className="d-flex align-items-center justify-content-center">
                    <Col lg={6}>
                        <CustomNavbar />
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CustomHomePage;