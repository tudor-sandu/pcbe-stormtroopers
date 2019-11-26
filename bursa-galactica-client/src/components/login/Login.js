import React, { Component } from "react";
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import "./Login.css";
class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      name: "",
      pass: ""
    };
  }

  componentWillMount(){
      console.log(this.props)
  }

  handleChange(e, type) {
    this.setState({ [type]: e.target.value });
  }

  setUser = () => {
    this.props.setUser(this.state.name);
    this.props.history.push("/home")

  };

  render() {
    return (
      <div>
        <Paper className="loginFormContainer">
          <div className="loginTitle">Log in</div>
          <TextField
            className="loginInput"
            id="standard-basic"
            label="Nume"
            margin="normal"
            onChange={e => this.handleChange(e, "name")}
            value={this.state.name}
          />
          <TextField
            className="loginInput"
            id="standard-basicqww"
            label="Parola"
            margin="normal"
            type="password"
            onChange={e => this.handleChange(e, "pass")}
            value={this.state.pass}
          />
          <br />
          <br />
          <br />
          <Button
            style={{ backgroundColor: "green", color: "white" }}
            onClick={() => this.setUser()}
          >
            Log in
          </Button>
        </Paper>
      </div>
    );
  }
}

export default Login;
