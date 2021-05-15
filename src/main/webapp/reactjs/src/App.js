import ButtonSection from './components/ButtonSection'
import OrderComp from './components/Order'
import OrderList from './components/OrdersList'
import React from "react";
import { BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import AdminPage from "./pages/AdminPage";
import Order from "./components/Order";
import NavBar from "./components/NavBar";

function App() {
  return (
      <BrowserRouter>
          <Switch>
                  <Route component={HomePage} exact path="/" />
                  <Route component={LoginPage} path="/login" />
                  <Route component={OrderComp} path="/order" />
                  <Route component={OrderList} path="/order/list" />
                  <Route component={AdminPage} path="/admin" />
              </Switch>
      </BrowserRouter>
  );
}

export default App;