import { Col, Container, Row } from "react-bootstrap";
import CustomNavbar from "./CustomNavbar";
import Dashboard from "./Dashboard";
import { useEffect, useState } from "react";
import ListDataFatture from "./ListDataFatture";
import ListDataClienti from "./ListDataClienti";
import { Route, Routes } from "react-router-dom";


const CustomHomePage = () => {

    const [dataFatture, setDataFatture] = useState([]);
    const [dataClienti, setDataClienti] = useState([]);
    const [loadingFatture, setLoadingFatture] = useState(true);
    const [loadingClienti, setLoadingClienti] = useState(true);
    const [selectedFilter, setSelectedFilter] = useState("");
  

    return (
        <>
            <Container fluid>
                <Row className="d-flex align-items-center flex-column justify-content-center">
                    <Col lg={6}>
                        <CustomNavbar />
                    </Col>
                    <Col lg={6} className="py-5">
                        <Dashboard dataClienti={dataClienti} dataFatture={dataFatture} />
                    </Col>
                    <Col lg={6} className="py-2">
               
                            <Routes>
                                <Route path="fatture" element={<ListDataFatture dataFatture={dataFatture} selectedFilter={selectedFilter} setLoadingFatture={setLoadingFatture} setDataFatture={setDataFatture } />} />
                                <Route path="clienti" element={<ListDataClienti dataClienti={dataClienti} setSelectedFilter={setSelectedFilter} setDataClienti={setDataClienti} setLoadingClienti={setLoadingClienti} />} />
                            </Routes>
                     
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default CustomHomePage;
