import { Col, Container, Row } from "react-bootstrap";
import { FaFileInvoice } from "react-icons/fa";
import { FaIndustry } from "react-icons/fa";

const CustomNavbar = () => {
    return (
        <>
            <Container fluid>
                <Row className="py-2">
                    <Col lg={4} className="d-flex align-items-center justify-content-center">
                        <div className="icons px-2">
                            <div style={{ fontSize: "25px" }} className="text-light">
                                <FaFileInvoice />
                            </div>
                            <div>
                                <p className="text-light"> Fatture</p>
                            </div>
                        </div>
                    </Col>
                    <Col lg={4} className="d-flex align-items-center justify-content-center">
                        <div className="icons px-2">
                            <div style={{ fontSize: "25px" }} className="text-light">
                                <FaIndustry />
                            </div>
                            <div>
                                <p className="text-light"> Fatture</p>
                            </div>
                        </div>
                    </Col>
                    <Col lg={4} className="d-flex align-items-center justify-content-center">
                        <div className="icons px-2">
                            <div style={{ fontSize: "25px" }} className="text-light">
                                <FaFileInvoice />
                            </div>
                            <div>
                                <p className="text-light"> Fatture</p>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CustomNavbar;