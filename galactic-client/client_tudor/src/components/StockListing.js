import React from "react";
import { List, Button } from "semantic-ui-react";
import Modal from "../components/Modal";
import "./styles.css"
export default function StockListing({ stockName, stockPrice, stockVolume }) {
  return (
    <List.Item className="stockItem">
      <List.Header>{stockName}</List.Header>
      <List.Description>
        {stockPrice} - {stockVolume}
      </List.Description>
      <Modal
      modalTrigger={<Button circular icon="dollar"/>}
      modalIcon='dollar'
      modalTitle='Warning'
      modalMsg={`Are you sure you want to buy ${stockName} stock`}
      />
        
    </List.Item>
  );
}
