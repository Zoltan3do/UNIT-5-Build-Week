import React, { useState } from "react";
import { Card, Button, Row, Col, Container, Form } from "react-bootstrap";
import { Link } from "react-router-dom";

const MyProfile = ({ user = {} }) => {
  const {
    username = "",
    email = "",
    nome = "",
    cognome = "",
    avatar = "",
  } = user;
  const [formData, setFormData] = useState({
    username,
    email,
    nome,
    cognome,
    avatar,
    password: "",
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(
        `http://localhost:3001/utenti/me/${user.id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );
      if (response.ok) {
        alert("Profilo aggiornato con successo!");
      } else {
        alert("Errore nell'aggiornamento del profilo.");
      }
    } catch (error) {
      console.error("Errore:", error);
      alert("Errore nel fare la richiesta.");
    }
  };
  return (
    <Container
      fluid
      className="vh-100 d-flex align-items-center justify-content-center"
    >
      <Row className="w-100 d-flex justify-content-center">
        <Col lg={2} className="card-login">
          <img
            src="https://picsum.photos/200/300"
            alt="User Avatar"
            className="img-fluid rounded w-100"
          />
        </Col>
        <Col lg={6} className="card-login">
          <div className="d-flex align-items-center justify-content-center py-4">
            <h4 style={{ fontWeight: "300" }} className="text-light m-0">
              Profilo
            </h4>
          </div>
          <div className="d-flex align-items-center justify-content-center">
            <p style={{ fontWeight: "300" }} className="text-light">
              Modifica profilo.
            </p>
          </div>
          <form className="py-3">
            <div data-mdb-input-init class="py-2 px-3">
              <input
                placeholder="Nome"
                type="text"
                id="form2Example1"
                value={formData.nome}
                onChange={handleChange}
                class="form-input"
              />
            </div>
            <div data-mdb-input-init class="py-2 px-3">
              <input
                placeholder="Cognome"
                type="text"
                id="form2Example1"
                value={formData.cognome}
                onChange={handleChange}
                class="form-input"
              />
            </div>
            <div data-mdb-input-init class="py-2 px-3">
              <input
                placeholder="Username"
                type="text"
                id="form2Example1"
                value={formData.username}
                onChange={handleChange}
                class="form-input"
              />
            </div>
            <div data-mdb-input-init class="py-2 px-3">
              <input
                placeholder="Email"
                type="text"
                id="form2Example1"
                value={formData.email}
                onChange={handleChange}
                class="form-input"
              />
            </div>
            <div data-mdb-input-init class="py-2 px-3">
              <input
                placeholder="Password"
                type="password"
                id="form2Example2"
                value={formData.password}
                onChange={handleChange}
                class="form-input"
              />
            </div>
            <div className="py-3 d-flex align-items-center justify-content-center">
              <div className="px-3">
                <button
                  data-mdb-button-init
                  data-mdb-ripple-init
                  class="custom-button"
                  type="submit"
                  onSubmit={handleSubmit}
                >
                  Salva
                </button>
              </div>
            </div>
          </form>
        </Col>
      </Row>
    </Container>
  );
};
export default MyProfile;
