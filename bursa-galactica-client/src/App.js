import React , {Component} from "react";
import "./App.css";

import { BrowserRouter as Router, Route, Redirect } from "react-router-dom";
import Home from "./components/home/Home";
import Login from "./components/login/Login";


class App extends Component {
  constructor(props) {
    super(props)

    this.state = {
      user:""
    }
  }

  setUser(user){
    this.setState({user})
    console.log("i've set user")
    console.log(user)
  }

  render() {
    return (
      <Router>
      <div className="appContainer">
      <Redirect from="/" to="login" />
      <Route path="/login" exact component={(props) => <Login {...props} setUser={(user)=>this.setUser(user)}/>} />
      <Route path="/home" component={(props) => <Home {...props} user={this.state.user}/>} />
      </div>
    </Router>
    )
  }
}



export default App;
