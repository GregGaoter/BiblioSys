import React from "react";

const AppFooter = () => {
  return (
    <div className="layout-footer">
      <div className="footer-logo-container">
        <img id="logo-footer" src="assets/layout/images/logo-footer.png" alt="logo-layout" />
        <span className="app-name">BiblioSys</span>
      </div>
      <span className="copyright">&#169; DSI - {new Date().getFullYear()}</span>
    </div>
  );
};

export default AppFooter;
