import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

const Dashboard = () => {

  const [statsClienti, setStatsClienti] = useState(0);
  const [statsFatture, setStatsFatture] = useState(0);


  const fetchClienti = async () => {
    try {
      const response = await fetch("http://localhost:3001/clienti", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + localStorage.getItem("Access Token"),
        },
      });

      if (!response.ok) {
        throw new Error("Errore nella fetch degli utenti");
      }

      const result = await response.json();

      setStatsClienti(result.content.length || 0); 
    } catch (err) {
      console.error("Errore durante la fetch degli utenti:", err);
    }
  };


  const fetchFatture = async () => {
    try {
      const response = await fetch("http://localhost:3001/fatture", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + localStorage.getItem("Access Token"),
        },
      });

      if (!response.ok) {
        throw new Error("Errore nella fetch delle fatture");
      }

      const result = await response.json();

      setStatsFatture(result.content.length || 0);
    } catch (err) {
      console.error("Errore durante la fetch delle fatture:", err);
    }
  };


  useEffect(() => {
    fetchClienti();
    fetchFatture();
  }, []);

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
                <p> {statsFatture} </p>
              </div>
              <div style={{ fontWeight: "300" }}>
                <p> Fatture</p>
              </div>
            </div>
          </Col>
          <Col lg={6}>
            <div className="text-center">
              <div style={{ fontSize: "40px" }}>
                <p> {statsClienti} </p>
              </div>
              <div style={{ fontWeight: "300" }}>
                <p> Clienti</p>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Dashboard;
