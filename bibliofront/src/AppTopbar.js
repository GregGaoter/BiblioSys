import React from "react";
import classNames from "classnames";
import AppBreadcrumb from "./AppBreadcrumb";

const AppTopbar = (props) => {
  const notificationsItemClassName = classNames("notifications-item", {
    "active-menuitem": props.topbarNotificationMenuActive,
  });
  const profileItemClassName = classNames("profile-item", { "active-menuitem fadeInDown": props.topbarUserMenuActive });

  return (
    <div className="layout-topbar">
      <div className="topbar-left">
        <button type="button" className="menu-button p-link" onClick={props.onMenuButtonClick}>
          <i className="pi pi-chevron-left"></i>
        </button>
        <span className="topbar-separator"></span>

        <div className="layout-breadcrumb viewname" style={{ textTransform: "uppercase" }}>
          <AppBreadcrumb routers={props.routers} />
        </div>

        <img id="logo-mobile" className="mobile-logo" src="assets/layout/images/logo-dark.svg" alt="diamond-layout" />
      </div>

      <div className="topbar-right">
        <ul className="topbar-menu">
          {/* <li className="search-item">
            <button type="button" className="p-link" onClick={props.onSearchClick}>
              <i className="pi pi-search"></i>
            </button>
          </li> */}
          <li className={notificationsItemClassName}>
            <button type="button" className="p-link" onClick={props.onTopbarNotification}>
              <i className="pi pi-bell"></i>
              <span className="topbar-badge">2</span>
            </button>
            <ul className="notifications-menu fade-in-up">
              <li role="menuitem">
                <button type="button" className="p-link" tabIndex="0">
                  <i className="pi pi-envelope"></i>
                  <div className="notification-item">
                    <div className="notification-summary">Relance</div>
                    <div className="notification-detail">
                      Le délai de votre emprunt est dépassé.
                    </div>
                  </div>
                </button>
              </li>
              <li role="menuitem">
                <button type="button" className="p-link">
                  <i className="pi pi-envelope"></i>
                  <div className="notification-item">
                    <div className="notification-summary">Prolongation</div>
                    <div className="notification-detail">Votre prolongation est accepté.</div>
                  </div>
                </button>
              </li>
            </ul>
          </li>

          <li className={profileItemClassName}>
            <button type="button" className="p-link" onClick={props.onTopbarUserMenu}>
              <img src="assets/demo/images/avatar/profile.jpg" alt="diamond-layout" className="profile-image" />
              <span className="profile-name">Amelia Stone</span>
            </button>
            <ul className="profile-menu fade-in-up">
              <li>
                <button type="button" className="p-link">
                  <i className="pi pi-user"></i>
                  <span>Profile</span>
                </button>
              </li>
              <li>
                <button type="button" className="p-link">
                  <i className="pi pi-cog"></i>
                  <span>Mes emprunts</span>
                </button>
              </li>
              <li>
                <button type="button" className="p-link">
                  <i className="pi pi-power-off"></i>
                  <span>Déconnexion</span>
                </button>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default AppTopbar;
