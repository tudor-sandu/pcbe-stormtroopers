import React, { PureComponent } from "react";
import * as API_CALLS from "../../../apiCalls";
import StockTable from "../elements/StockTable";
class Sell extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      availableStocks: []
    };
  }

  componentWillMount() {
    API_CALLS.getItemsToSell().then(stocks => {
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

export default Sell;
