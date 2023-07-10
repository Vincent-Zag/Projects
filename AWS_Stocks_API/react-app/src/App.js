import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Navigation from "./components/Navigation";
import NavBar from "./components/Navigation/NavBar";
import AllStocks from "./components/Navigation/Stock/AllStocks"
import StockIndex from "./components/Navigation/Stock/StockIndex"

function App() {
  const dispatch = useDispatch();
  const [isLoaded, setIsLoaded] = useState(false);

  return (
    <>
      <BrowserRouter>
        <Switch>
          <Route path="/"  exact={true}>
            <NavBar />
            <AllStocks />
          </Route>
          <Route path='/stocks/:stockId' exact={true}>
            <NavBar />
            <StockIndex />
          </Route>
        </Switch>
      </BrowserRouter>
    </>
  );
}

export default App;
