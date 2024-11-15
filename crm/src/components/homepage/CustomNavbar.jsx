import { Col, Container, Row } from "react-bootstrap";
import { FaFileInvoice } from "react-icons/fa";
import { FaIndustry } from "react-icons/fa";
import { FaUser } from "react-icons/fa";
import { Link } from "react-router-dom";


const CustomNavbar = () => {

    return (
        <>
            <Container fluid>
                <Row className="py-2">
                    <Col lg={4} className="d-flex align-items-center justify-content-center">
                        <Link to="fatture" className="text-decoration-none">
                            <div className="icons px-2">
                                <div style={{ fontSize: "25px" }}>
                                    <FaFileInvoice />
                                </div>
                                <div>
                                    <p> Fatture</p>
                                </div>
                            </div>
                        </Link>
                    </Col>
                    <Col lg={4} className="d-flex align-items-center justify-content-center">
                        <Link to="clienti" className="text-decoration-none">
                            <div className="icons px-2">
                                <div style={{ fontSize: "25px" }}>
                                    <FaIndustry />
                                </div>
                                <div>
                                    <p> Clienti</p>
                                </div>
                            </div>
                        </Link>
                    </Col>
                    <Col lg={4} className="d-flex align-items-center justify-content-center">
                        <div className="icons px-2">
                            <div style={{ fontSize: "25px" }}>
                                <FaUser />
                            </div>
                            <div>
                                <p> Profilo</p>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CustomNavbar;