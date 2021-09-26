import React, { useEffect } from "react";
import { Route, Switch, useLocation, withRouter } from "react-router-dom";
import App from "./App";
import { Bibliotheques } from "./components/Bibliotheques";
import { Gallery } from "./components/Gallery";
import { Header } from "./components/Header";
import { Navigation } from "./components/Navigation";
import { Services } from "./components/Services";
import { Team } from "./components/Team";
import Access from "./pages/Access";
import Error from "./pages/Error";
import Login from "./pages/Login";
import NotFound from "./pages/NotFound";

const AppWrapper = () => {
  let location = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [location]);

  return (
    <div className="p-d-flex p-flex-column">
      <Navigation />
      <Switch>
        <Route exact path="/" component={Header} />
        <Route exact path="/bibliotheques" component={Bibliotheques} />
        <Route exact path="/services" component={Services} />
        <Route exact path="/gallerie" component={Gallery} />
        <Route exact path="/equipe" component={Team} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/error" component={Error} />
        <Route exact path="/notfound" component={NotFound} />
        <Route exact path="/access" component={Access} />
        <Route component={App} />
      </Switch>
    </div>
  );

  // switch (location.pathname) {
  //   case "/":
  //     return <Route path="/" component={Header} />;
  //   case "/bibliotheques":
  //     return <Route path="/bibliotheques" component={Bibliotheques} />;
  //   case "/login":
  //     return <Route path="/login" component={Login} />;
  //   case "/error":
  //     return <Route path="/error" component={Error} />;
  //   case "/notfound":
  //     return <Route path="/notfound" component={NotFound} />;
  //   case "/access":
  //     return <Route path="/access" component={Access} />;
  //   default:
  //     return <App />;
  // }
};

export default withRouter(AppWrapper);
