import axios from "axios";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useAppDispatch } from "../app/store/hooks";
import logo from "../ressources/images/logo-128-128.png";
import { getEntity as getCurrentUsagerEntity } from "../app/store/slice/CurrentUsagerSlice";

const Login = () => {
  const history = useHistory();
  const dispatch = useAppDispatch();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSignIn = () => {
    const endpoint = "http://localhost:8080/authentification";
    const credentials = { username: email, password: password };

    axios.post(endpoint, credentials).then((reponse) => {
      localStorage.setItem("BIBLIOSYS-AUTHORIZATION", reponse.data.token);
      dispatch(getCurrentUsagerEntity());
      history.push("/accueil");
    });
  };

  const title = <h3 className="p-d-flex p-jc-center">Bienvenue sur Biblillonie</h3>;

  const header = (
    <img
      alt="logo"
      src={logo}
      style={{
        width: "128px",
        paddingTop: "15px",
        display: "block",
        marginLeft: "auto",
        marginRight: "auto",
      }}
    />
  );

  const footer = (
    <Button
      label="Se connecter"
      icon="pi pi-sign-in"
      style={{ display: "block", width: "100%" }}
      onClick={handleSignIn}
    />
  );

  return (
    <div className="p-d-flex p-jc-center p-mt-6">
      <Card className="p-shadow-6" title={title} header={header} footer={footer} style={{ width: "500px" }}>
        <div className="p-fluid">
          <div className="p-field">
            <label htmlFor="email">E-mail</label>
            <InputText
              id="email"
              type="email"
              placeholder="Entrer votre e-mail"
              onChange={(e) => setEmail(e.currentTarget.value)}
            />
          </div>
          <div className="p-field">
            <label htmlFor="password">Mot de passe</label>
            <InputText
              id="password"
              type="password"
              placeholder="Entrer votre mot de passe"
              onChange={(e) => setPassword(e.currentTarget.value)}
            />
          </div>
        </div>
      </Card>
    </div>
  );
};

export default Login;
