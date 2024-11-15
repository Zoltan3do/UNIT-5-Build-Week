import { useEffect, useRef, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const Dashboard = ({ dataClienti, dataFatture }) => {

    const [statsClienti, setStatsClienti] = useState(0)
    const [statsFatture, setStatsFatture] = useState(0)
    const hasChanged = useRef(false);

    useEffect(() => {

        if(hasChanged.current) {
            setStatsClienti(dataClienti.length);
            setStatsFatture(dataFatture.length)
        } else {
            hasChanged.current = true;
        }
        
    }, [statsClienti]);

    return (
        <>
            <Container fluid className="dash-board py-2">
                <Row>
                    <Col className="text-center">
                        <h2 style={{ fontWeight: "300" }}> Benvenuto, utente. </h2>
                    </Col>
                </Row>
                <Row className="py-3">
                    <Col lg={6}>
                        <div className="text-center">
                            <div style={{ fontSize: "40px" }}>
                                <p> {statsFatture}</p>
                            </div>
                            <div style={{ fontWeight: "300" }}>
                                <p> Fatture</p>
                            </div>
                        </div>
                    </Col>
                    <Col lg={6}>
                        <div className="text-center">
                            <div style={{ fontSize: "40px" }}>
                                <p> {statsClienti}</p>
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

export default Dashboard;