import React, { useEffect } from "react";
import { Route, Switch, useLocation, withRouter } from "react-router-dom";
import App from "./App";
import { Bibliotheques } from "./components/Bibliotheques";
import { Contact } from "./components/Contact";
import { Gallery } from "./components/Gallery";
import { Header } from "./components/Header";
import { Horaires } from "./components/Horaires";
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
    <Switch>
      <Route
        exact
        path="/"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Header />
          </div>
        )}
      />
      <Route
        exact
        path="/bibliotheques"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Bibliotheques />
          </div>
        )}
      />
      <Route
        exact
        path="/services"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Services />
          </div>
        )}
      />
      <Route
        exact
        path="/gallerie"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Gallery />
          </div>
        )}
      />
      <Route
        exact
        path="/equipe"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Team />
          </div>
        )}
      />
      <Route
        exact
        path="/horaires"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Horaires />
          </div>
        )}
      />
      <Route
        exact
        path="/contact"
        component={() => (
          <div className="p-d-flex p-flex-column">
            <Navigation />
            <Contact />
          </div>
        )}
      />
      <Route exact path="/login" component={Login} />
      <Route exact path="/error" component={Error} />
      <Route exact path="/notfound" component={NotFound} />
      <Route exact path="/access" component={Access} />
      <Route component={App} />
    </Switch>
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
