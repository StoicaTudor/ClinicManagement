import tr from "react";
import TextField from "@material-ui/core/TextField";
// import Modal from "react-modal";
import { Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.css";
import React, { Component } from "react";
import ConsultationModal from "./consultationModal";

class Consultation extends Component {
  state = {
    modalIsOpen: false,
    patientId : 1
  };

  handleOpenModal = () => {
    this.setState({ modalIsOpen: true });
  };

  handleCloseModal = () => {
    this.setState({ modalIsOpen: false });
  };

  render() {
    return (
      <div>
        <Button variant="primary" onClick={this.handleOpenModal}>
          Consultations
        </Button>
        <ConsultationModal
          modalIsOpen={this.state.modalIsOpen}
          patientId={this.state.id}
          closeModal={this.handleCloseModal}
        />
      </div>
    );
  }
}

export default Consultation;
