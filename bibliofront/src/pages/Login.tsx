import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import React from "react";
import { useHistory } from "react-router-dom";
import logo from "../ressources/images/logo-128-128.png";

const Login = () => {
  const history = useHistory();

  const handleSignIn = () => {
    history.push("/");
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
            <InputText id="email" type="email" placeholder="Entrer votre e-mail" />
          </div>
          <div className="p-field">
            <label htmlFor="password">Mot de passe</label>
            <InputText id="password" type="password" placeholder="Entrer votre mot de passe" />
          </div>
        </div>
      </Card>
    </div>
  );
};

export default Login;
