import React, { Component } from "react";
import ConsultationRow from "./consultationRow";

class ConsultationModalTable extends Component {
  state = {};

  render() {
    return (
      <React.Fragment>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Date</th>
              <th scope="col">Details</th>
              <th scope="col">Doctor</th>
            </tr>
          </thead>
          <tbody>
            {console.log(this.props.consultations)}
            {console.log("xxxxxxxxxxx")}
            {this.props.consultations.map((consultation) => (
              <ConsultationRow
                consultation={consultation}
                onConsultationDateChange={
                  this.props.handleConsultationDateChange
                }
                onConsultationDetailsChange={
                  this.props.handleConsultationDetailsChange
                }
              ></ConsultationRow>
            ))}
          </tbody>
        </table>
      </React.Fragment>
    );
  }
}

export default ConsultationModalTable;
