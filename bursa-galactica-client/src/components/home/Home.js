import React, { Component } from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";
import AccountCircle from "@material-ui/icons/AccountCircle";
import TemporaryDrawer from "./elements/Drawer";
import { Tooltip } from "@material-ui/core";
import Buy from "./sections/Buy";
import Sell from "./sections/Sell";
import Profile from "./sections/Profile";
import "./Home.css";
const buy = 0;
const sell = 1;
const profile = 2;
const logout = 3;

class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      auth: "",
      anchorEl: "",
      drawerOpen: false,
      selectedSection: buy
    };
  }

  closeDrawer() {
    this.setState({ drawerOpen: false });
  }

  openDrawer() {
    this.setState({ drawerOpen: true });
  }

  handleChange(event) {
    this.setState({ auth: event.target.checked });
  }

  handleMenu(event) {
    this.setState({ anchorEl: event.currentTarget });
  }

  setSelectedSection(section) {
    if (section == logout) {
      this.props.logout();
    }
    this.setState({ selectedSection: section });
  }

  componentWillMount() {
    if (this.props.user.length === 0) {
      this.props.history.push("/login");
    }
  }

  render() {
    return (
      <div>
        <TemporaryDrawer
          setSelectedSection={selectedSection =>
            this.setSelectedSection(selectedSection)
          }
          open={this.state.drawerOpen}
          closeDrawer={() => this.closeDrawer()}
        />
        <AppBar position="static">
          <Toolbar style={{ backgroundColor: "green", color: "white" }}>
            <IconButton
              edge="start"
              color="inherit"
              aria-label="menu"
              onClick={() => this.openDrawer()}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6">Bursa Galactica</Typography>
            <div style={{ margin: "auto" }}></div>
            <div>
              <Tooltip
                title={this.props.user ? this.props.user : "Log in please"}
              >
                <IconButton
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  color="inherit"
                >
                  <AccountCircle />
                </IconButton>
              </Tooltip>
            </div>
          </Toolbar>
        </AppBar>

        {this.state.selectedSection === buy && <Buy />}
        {this.state.selectedSection === sell && <Sell />}
        {this.state.selectedSection === profile && <Profile />}
      </div>
    );
  }
}

export default Home;
