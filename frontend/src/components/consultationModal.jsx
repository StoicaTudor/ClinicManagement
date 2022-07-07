import React, { Component } from "react";
import { Modal, Button } from "react-bootstrap";
import ConsultationModalTable from "./consultationModalTable";
import BackendUrlFactory from "./../mappings/backendUrlFactory";

class ConsultationModal extends Component {
  state = {
    consultationsHaveFetched: false,
    modalIsOpen: false,
    patientId: 1,
    patientName: "Gheorghe",
    consultations: [],
  };

  removeEmptyConsultations() {
    const consultations = this.state.consultations.filter(
      (consultation) => consultation.date && consultation.details
    );

    this.setState({ consultations });
  }

  saveUpdatedConsultationsList() {
    this.removeEmptyConsultations();

    this.state.consultations.forEach((consultation) => {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          id: consultation.id,
          date: consultation.date,
          details: consultation.details,
          doctorId: consultation.doctor.id,
          patientId: consultation.patient.id,
        }),
      };
      fetch(
        BackendUrlFactory.ROOT_URL +
          BackendUrlFactory.SECRETARY_MODULE.MODULE +
          BackendUrlFactory.SECRETARY_MODULE.POST_UPDATE_CONSULTATION,
        requestOptions
      );
    });
  }

  fetchConsultations() {
    if (this.state.consultationsHaveFetched === false) {
      fetch(
        "http://localhost:8080/clinic/secretary/getConsultationsByPatientId?patientId=1"
      ).then((response) => {
        response.json().then((value) => {
          this.setState({ consultations: value });
        });
      });

      this.setState({ consultationsHaveFetched: true });
    }
  }

  handleConsultationDateChange = (consultation, newTextField) => {
    this.handleConsultationTextFieldChange(
      consultation,
      newTextField,
      this.updateDate
    );
  };

  handleConsultationDetailsChange = (consultation, newTextField) => {
    this.handleConsultationTextFieldChange(
      consultation,
      newTextField,
      this.updateDetails
    );
  };

  handleConsultationTextFieldChange = (
    consultation,
    newTextField,
    consultationsAfterFieldChange
  ) => {
    const consultations = [...this.state.consultations];
    const index = consultations.indexOf(consultation);
    consultations[index] = { ...consultation };
    const updatedConsultationsList = consultationsAfterFieldChange(
      consultations,
      index,
      newTextField
    );
    this.setState({ consultations: updatedConsultationsList });
  };

  updateDate(consultations, index, newTextField) {
    consultations[index].date = newTextField;
    return consultations;
  }

  updateDetails(consultations, index, newTextField) {
    consultations[index].details = newTextField;
    return consultations;
  }

  createEmptyConsultation() {
    const consultations = [...this.state.consultations];
    consultations.push({
      id: "",
      date: "",
      doctor: {},
      patient: {},
      details: "",
    });

    this.setState({ consultations });
  }

  render() {
    this.fetchConsultations();
    return (
      <Modal show={this.props.modalIsOpen} onHide={this.props.closeModal}>
        <Modal.Header>
          <Modal.Title>{this.state.patientName}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ConsultationModalTable
            consultations={this.state.consultations}
            handleConsultationDateChange={this.handleConsultationDateChange}
            handleConsultationDetailsChange={
              this.handleConsultationDetailsChange
            }
          />
        </Modal.Body>
        <Modal.Footer>
          <button
            className="btn btn-primary btn-sm m-2"
            onClick={() => {
              this.saveUpdatedConsultationsList();
            }}
          >
            Save Changes
          </button>

          <button
            className="btn btn-primary btn-sm m-2"
            onClick={() => {
              this.createEmptyConsultation();
            }}
          >
            Create consultation
          </button>

          <Button variant="secondary" onClick={this.props.closeModal}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    );
  }
}

export default ConsultationModal;
