import React, { PureComponent } from "react";
import StockTable from "../elements/StockTable";
import { Fab, Tooltip, Modal, TextField, Button } from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import * as API_CALLS from "../../../apiCalls";

class Sell extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      availableStocks: [],
      modalOpened: false,
      stockName: "",
      stockValue: ""
    };
  }

  sellStock() {
    console.log(this.state.stockName);
    console.log(this.state.stockValue);
    console.log(this.state.stockQuantity);

    let requestString = [
      this.state.stockName,
      this.state.stockQuantity,
      this.state.stockValue
    ].join("-");
    console.log(requestString);

    API_CALLS.sellStock(requestString).then(() => {
      API_CALLS.getItemsToSell();
    });
  }

  openModal() {
    this.setState({ modalOpened: true });
  }

  closeModal() {
    this.setState({ modalOpened: false });
  }

  componentWillMount() {
    API_CALLS.getItemsToSell().then(stocks => {
      console.log(stocks);
      this.setState({ availableStocks: stocks });
    });
  }

  handleChange(e, type) {
    this.setState({ [type]: e.target.value });
  }

  render() {
    return (
      <div>
        {this.state.availableStocks && (
          <StockTable
            stocks={this.state.availableStocks}
            title="The Stocks you are selling"
          />
        )}

        <Tooltip title="Sell new stock">
          <Fab
            color="primary"
            aria-label="add"
            className="sellFabButton"
            onClick={() => this.openModal()}
          >
            <AddIcon />
          </Fab>
        </Tooltip>

        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={this.state.modalOpened}
          onClose={() => this.closeModal()}
        >
          <div className="modalContainer">
            <h2 className="modalTitle">Sell stocks</h2>
            <p className="modalDescription">
              Please insert the name, value and quantity of the stock you with
              to add.
            </p>

            <TextField
              className="loginInput"
              id="standard-basic"
              label="Name"
              margin="normal"
              onChange={e => this.handleChange(e, "stockName")}
              value={this.state.stockName}
            />

            <TextField
              className="loginInput"
              id="standard-basic"
              label="Value"
              margin="normal"
              onChange={e => this.handleChange(e, "stockValue")}
              value={this.state.stockValue}
            />

            <TextField
              className="loginInput"
              id="standard-basic"
              label="Quantity"
              margin="normal"
              onChange={e => this.handleChange(e, "stockQuantity")}
              value={this.state.stockQuantity}
            />
            <br />
            <br />

            <Button
              style={{ backgroundColor: "green", color: "white" }}
              onClick={() => this.sellStock()}
            >
              Sell stock
            </Button>
          </div>
        </Modal>
      </div>
    );
  }
}

export default Sell;
