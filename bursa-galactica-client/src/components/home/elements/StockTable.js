import React from "react";

function StockTable(props) {
  return (
    <div className="stocksContainer">
      <div className="stockItem stockItemHeader stockTitle">{props.title}</div>
      <div className="stockItem stockItemHeader">
        <div className="stockColumn stockName">Stock Name</div>
        <div className="stockColumn stockValue">Stock Value</div>
        <div className="stockColumn stockVolume">Stock Volume</div>
      </div>
      {props.stocks.map(stock => {
        return (
          <div className="stockItem">
            <div className="stockColumn stockName">{stock.name}</div>
            <div className="stockColumn stockValue">{stock.value}$</div>
            <div className="stockColumn stockVolume">{stock.volume}</div>
          </div>
        );
      })}
    </div>
  );
}

export default StockTable;
