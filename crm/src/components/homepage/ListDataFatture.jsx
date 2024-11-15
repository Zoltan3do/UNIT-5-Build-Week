import { useEffect } from "react";
import { Col, Container, Row } from "react-bootstrap";

const ListDataFatture = ({ dataFatture, setDataFatture, setLoadingFatture }) => {

    const fetchDataFatture = async () => {
        const url = `http://localhost:3001/fatture`;

        try {
            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': "Bearer " + localStorage.getItem("Access Token")
                }
            });

            if (!response.ok) {
                throw new Error("Errore nella fetch!");
            }

            const results = await response.json();
            setDataFatture(results.content);
        } catch (err) {
            console.log("Errore durante la fetch", err);
        } finally {
            setLoadingFatture(false);
        }
    };

    useEffect(() => {
        fetchDataFatture();
    }, []);


    return (
        <>
            <Container fluid className="list-data py-2">
                <Row>
                    <Col className="text-center">
                        <p> Numero</p>
                    </Col>
                    <Col className="text-center">
                        <p> Data Fattura</p>
                    </Col>
                    <Col className="text-center">
                        <p> Importo</p>
                    </Col>
                    <Col className="text-center">
                        <p> Cliente </p>
                    </Col>
                </Row>
                <Row className="py-3">
                    <Col lg={12}>
                        {dataFatture.length > 0 ? (
                            dataFatture.map((fattura, index) => (
                                <Row key={index}>
                                    <Col className="text-center">
                                        <p> {fattura.numero}</p>
                                    </Col>
                                    <Col className="text-center">
                                        <p>  {fattura.data}</p>
                                    </Col>
                                    <Col className="text-center">
                                        <p> {fattura.importo}</p>
                                    </Col>
                                    <Col className="text-center">
                                        <p> {fattura.cliente.ragioneSociale}</p>
                                    </Col>
                                </Row>
                            ))
                        ) : (
                            <p>Non ci sono dati per questo.</p>
                        )}
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default ListDataFatture;