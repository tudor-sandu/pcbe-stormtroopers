import React, { Component } from "react";
import * as API_CALLS from "../../../apiCalls";
import StockTable from "../elements/StockTable";
class Buy extends Component {
  constructor(props) {
    super(props);

    this.state = {
      availableStocks: []
    };
  }

  componentWillMount() {
    API_CALLS.getItemsToBuy().then(stocks => {
      console.log(stocks);
      this.setState({ availableStocks: stocks });
    });
  }

  render() {
    return (
      <div>
        {this.state.availableStocks && (
          <StockTable stocks={this.state.availableStocks} />
        )}
      </div>
    );
  }
}

export default Buy;
