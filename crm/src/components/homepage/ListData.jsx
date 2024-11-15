import { Col, Container, Row } from "react-bootstrap";

const ListData = () => {
    return (
        <>
            <Container fluid className="list-data py-2">
                <Row>
                    <Col className="text-center">
                        <h2 style={{ fontWeight: "300" }}> Benvenuto, utente. </h2>
                    </Col>
                </Row>
                <Row className="py-3">
                    <Col lg={6}>
                        <div className="text-center">
                            <div style={{ fontSize: "40px" }}>
                                <p> 12</p>
                            </div>
                            <div style={{ fontWeight: "300" }}>
                                <p> Fatture</p>
                            </div>
                        </div>
                    </Col>
                    <Col lg={6}>
                        <div className="text-center">
                            <div style={{ fontSize: "40px" }}>
                                <p> 22</p>
                            </div>
                            <div style={{ fontWeight: "300" }}>
                                <p> Clienti</p>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default ListData;