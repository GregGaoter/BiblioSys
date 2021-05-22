import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "primeflex/primeflex.css";
import "primeicons/primeicons.css";
import PrimeReact from "primereact/api";
import "primereact/resources/primereact.min.css";
import "primereact/resources/themes/saga-blue/theme.css";
import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import { store } from "./app/store/store";
import AppWrapper from "./AppWrapper";
import "./index.css";

PrimeReact.ripple = true;

// export const api = axios.create({
//   baseURL: 'http://localhost:8080',
//   headers:{
//     'Content-Type': 'application/json'
//   }
// });

axios.interceptors.request.use(
  (config) => {
    config.baseURL = 'http://localhost:8080';
    config.headers['Content-Type'] = 'application/json';
    const jwtToken = localStorage.getItem("BIBLIOSYS-AUTHORIZATION");
    if (jwtToken) {
      config.headers["BIBLIOSYS-AUTHORIZATION"] = "Bearer " + jwtToken;
    }
    return config;
  },
  (err) => Promise.reject(err)
);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <AppWrapper />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>,
  document.getElementById("root")
);
