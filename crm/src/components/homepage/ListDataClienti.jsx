import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const ListDataClienti = ({ dataClienti, setDataClienti, setLoadingClienti }) => {

    const [selectedFilter, setSelectedFilter] = useState("");
    const [valueFilter, setValueFilter] = useState("")

   

    const fetchDataClienti = async () => {

        console.log(valueFilter, selectedFilter)


        const genericUrl = "http://localhost:3001/clienti";


        let url = `http://localhost:3001/clienti?${selectedFilter}=${valueFilter}`;

        if(selectedFilter === "" && valueFilter === "") {
            url = genericUrl;
        } 

        const token = localStorage.getItem('token');

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
            setDataClienti(results.content);
        } catch (err) {
            console.log("Errore durante la fetch", err);
        } finally {
            setLoadingClienti(false);
        }
    };

    useEffect(() => {
        fetchDataClienti();
    }, []);

    return (
        <>

            <Container fluid className="text-light py-2">
                <Row>
                    <Col>
                        <div className="d-flex justify-content-between">
                            <div className="d-flex">
                                <div>
                                    <label for="filter"> Filtra per: </label>
                                </div>
                                <div className="ms-2">
                                    <select id="filter" className="rounded-2" onChange={(e) => setSelectedFilter(e.target.value)}>
                                        <option value=""> - </option>
                                        <option value="fatturatoMinimo"> Fatturato Minimo</option>
                                        <option value="dataUltimoContatto"> Data Ultimo Contatto </option>
                                        <option value="dataInserimento"> Data Inserimento</option>
                                        <option value="parteDelNome"> Parte del Nome</option>
                                        <option value="nomeContatto"> Nome Contatto</option>
                                        <option value="cognomeContatto"> Cognome Contatto</option>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <input className="rounded-2" onChange={(e) => setValueFilter(e.target.value)} />
                            </div>
                            <div>
                                <button style={{borderRadius: "8px"}} onClick={fetchDataClienti}> Applica</button>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container>

            <Container fluid className="list-data py-2">
                <Row>
                    <Col className="text-center">
                        <p> Nome</p>
                    </Col>
                    <Col className="text-center">
                        <p> Cognome </p>
                    </Col>
                    <Col className="text-center">
                        <p> Email</p>
                    </Col>
                    <Col className="text-center">
                        <p> Partita Iva </p>
                    </Col>
                </Row>
                <Row className="py-3">
                    <Col lg={12}>
                        {dataClienti.length > 0 ? (
                            dataClienti.map((cliente, index) => (
                                <Row key={index}>
                                    <Col className="text-center">
                                        <p> {cliente.nomeContatto}</p>
                                    </Col>
                                    <Col className="text-center">
                                        <p>  {cliente.cognomeContatto}</p>
                                    </Col>
                                    <Col className="text-center">
                                        <p> {cliente.email}</p>
                                    </Col>
                                    <Col className="text-center">
                                        <p> {cliente.partitaIva}</p>
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

export default ListDataClienti;