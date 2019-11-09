import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Buy from "./pages/Buy/Buy";
import Sell from "./pages/Sell/Sell";
import { Menu } from "semantic-ui-react";
export default function App() {
  const [activeItem, setActiveItem] = useState("buy");

  const handleItemClick = (e, { name }) => setActiveItem(name);

  return (
    <Router>
      <div>
        <nav>
          <Menu>
            <Link to="/buy">
              <Menu.Item
                name="buy"
                active={activeItem === "buy"}
                onClick={handleItemClick}
              >
                Buy
              </Menu.Item>
            </Link>
            <Link to="/sell">
              <Menu.Item
                name="sell"
                active={activeItem === "sell"}
                onClick={handleItemClick}
              >
                Sell
              </Menu.Item>
            </Link>
          </Menu>
        </nav>
        <Switch>
          <Route path="/buy">
            <Buy />
          </Route>
          <Route path="/sell">
            <Sell />
          </Route>
          <Route path="/">
            <Buy />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
