import React, { Component } from "react";
import TextField from "@material-ui/core/TextField";

class ConsultationRow extends Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <tr>
          <td>{this.props.consultation.id}</td>

          <td>
            <TextField
              type="text"
              value={this.props.consultation.date}
              onChange={(e) => {
                this.props.onConsultationDateChange(
                  this.props.consultation,
                  e.target.value
                );
              }}
            ></TextField>
          </td>

          <td>
            <TextField
              type="text"
              value={this.props.consultation.details}
              onChange={(e) => {
                this.props.onConsultationDetailsChange(
                  this.props.consultation,
                  e.target.value
                );
              }}
            ></TextField>
          </td>

          <td>{this.props.consultation.doctor.name}</td>
        </tr>
      </React.Fragment>
    );
  }
}

export default ConsultationRow;
