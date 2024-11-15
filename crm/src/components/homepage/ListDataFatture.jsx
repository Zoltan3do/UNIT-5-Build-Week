import { useEffect } from "react";
import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const ListDataFatture = ({ dataFatture, setDataFatture, setLoadingFatture }) => {

    const [selectedFilter, setSelectedFilter] = useState("");
    const [valueFilter, setValueFilter] = useState("")
    const [valueRangeMin, setValueRangeMin] = useState("")
    const [valueRangeMax, setValueRangeMax] = useState("")

    const fetchDataFatture = async () => {
      

        const rangeUrl = `http://localhost:3001/fatture?minImporto=${valueRangeMin}&maxImporto${valueRangeMax}`;
        const genericUrl = "http://localhost:3001/fatture";
        let url = `http://localhost:3001/fatture?${selectedFilter}=${valueFilter}`;

        if (selectedFilter === "" && valueFilter === "") {
            url = genericUrl;
        }

        if(selectedFilter === "rangeDiPrezzo") {
            url = rangeUrl
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

            <Container fluid className="text-light py-2">
                <Row>
                    <Col>
                        <div className="d-flex align-items-center justify-content-between">
                            <div className="d-flex align-items-center">
                                <div>
                                    <label for="filter"> Filtra per: </label>
                                </div>
                                <div className="ms-2">
                                    <select id="filter" className="rounded-2" onChange={(e) => setSelectedFilter(e.target.value)}>
                                        <option value=""> - </option>
                                        <option value="cliente"> Cliente</option>
                                        <option value="statoFattura"> Stato Fattura </option>
                                        <option value="data"> Data Inserimento</option>
                                        <option value="anno"> Anno </option>
                                        <option value="rangeDiPrezzo"> Range Di Prezzo</option>
                                    </select>
                                </div>
                            </div>
                            {
                                selectedFilter === "rangeDiPrezzo" ?
                                    (
                                        <>
                                            <div>
                                                <input placeholder="Min" style={{width: "80px"}} className="rounded-2" onChange={(e) => setValueRangeMin(e.target.value)} />
                                            </div>
                                            <div>
                                                <input placeholder="Max" style={{width: "80px"}} className="rounded-2" onChange={(e) => setValueRangeMax(e.target.value)} />
                                            </div>
                                        </>

                                    )
                                    :
                                    (
                                        <div>
                                            <input className="rounded-2" onChange={(e) => setValueFilter(e.target.value)} />
                                        </div>

                                    )
                            }
                            <div>
                                <button style={{ borderRadius: "8px" }} onClick={fetchDataFatture}> Applica</button>
                            </div>
                        </div>
                    </Col>
                </Row>
            </Container >


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
                        <p> Stato </p>
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
                                        <p>  {fattura.statoFattura.tipo}</p>
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