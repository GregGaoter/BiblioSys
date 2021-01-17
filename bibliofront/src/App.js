import React, { useState, useEffect } from "react";
import classNames from "classnames";
import { Route } from "react-router-dom";

import AppTopBar from "./AppTopbar";
import AppFooter from "./AppFooter";
import AppConfig from "./AppConfig";
import AppMenu from "./AppMenu";
import AppSearch from "./AppSearch";
import AppRightMenu from "./AppRightMenu";

import { Dashboard } from "./components/Dashboard";
import { FormLayoutDemo } from "./components/FormLayoutDemo";
import { InputDemo } from "./components/InputDemo";
import { FloatLabelDemo } from "./components/FloatLabelDemo";
import { ButtonDemo } from "./components/ButtonDemo";
import { TableDemo } from "./components/TableDemo";
import { ListDemo } from "./components/ListDemo";
import { TreeDemo } from "./components/TreeDemo";
import { PanelDemo } from "./components/PanelDemo";
import { OverlayDemo } from "./components/OverlayDemo";
import { MediaDemo } from "./components/MediaDemo";
import { MenuDemo } from "./components/MenuDemo";
import { MessagesDemo } from "./components/MessagesDemo";
import { FileDemo } from "./components/FileDemo";
import { ChartDemo } from "./components/ChartDemo";
import { MiscDemo } from "./components/MiscDemo";
import { Documentation } from "./components/Documentation";
import { IconsDemo } from "./utilities/IconsDemo";
import { Widgets } from "./utilities/Widgets";
import { GridDemo } from "./utilities/GridDemo";
import { SpacingDemo } from "./utilities/SpacingDemo";
import { ElevationDemo } from "./utilities/ElevationDemo";
import { TextDemo } from "./utilities/TextDemo";
import { TypographyDemo } from "./utilities/TypographyDemo";
import { DisplayDemo } from "./utilities/DisplayDemo";
import { FlexBoxDemo } from "./utilities/FlexBoxDemo";
import { CrudDemo } from "./pages/CrudDemo";
import { CalendarDemo } from "./pages/CalendarDemo";
import { Invoice } from "./pages/Invoice";
import { Help } from "./pages/Help";
import { EmptyPage } from "./pages/EmptyPage";

import PrimeReact from "primereact/utils";

import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";
import "./App.scss";

const App = () => {
  const [menuActive, setMenuActive] = useState(false);
  const [menuMode, setMenuMode] = useState("static");
  const [colorScheme, setColorScheme] = useState("dim");
  const [menuTheme, setMenuTheme] = useState("layout-sidebar-darkgray");
  const [overlayMenuActive, setOverlayMenuActive] = useState(false);
  const [staticMenuDesktopInactive, setStaticMenuDesktopInactive] = useState(false);
  const [staticMenuMobileActive, setStaticMenuMobileActive] = useState(false);
  const [searchActive, setSearchActive] = useState(false);
  const [topbarUserMenuActive, setTopbarUserMenuActive] = useState(false);
  const [topbarNotificationMenuActive, setTopbarNotificationMenuActive] = useState(false);
  const [rightMenuActive, setRightMenuActive] = useState(false);
  const [configActive, setConfigActive] = useState(false);
  const [inputStyle, setInputStyle] = useState("outlined");
  const [ripple, setRipple] = useState(true);

  let menuClick = false;
  let searchClick = false;
  let userMenuClick = false;
  let notificationMenuClick = false;
  let rightMenuClick = false;
  let configClick = false;

  const menu = [
    {
      label: "Accueil",
      icon: "pi pi-fw pi-home",
      items: [{ label: "Recherche", icon: "pi pi-search", to: "/empty" }],
    },
    { separator: true },
    {
      label: "Genres",
      icon: "pi pi-fw pi-id-card",
      items: [
        { label: "Actualité médiatique et politique", icon: "pi pi-eye", to: "/empty" },
        { label: "Afrique", icon: "pi pi-eye", to: "/empty" },
        { label: "Amériques", icon: "pi pi-eye", to: "/empty" },
        { label: "Animaux", icon: "pi pi-eye", to: "/empty" },
        { label: "Art décoratif", icon: "pi pi-eye", to: "/empty" },
        { label: "Asie", icon: "pi pi-eye", to: "/empty" },
        { label: "Bande dessinée", icon: "pi pi-eye", to: "/empty" },
        { label: "Beaux arts", icon: "pi pi-eye", to: "/empty" },
        { label: "Bricolage", icon: "pi pi-eye", to: "/empty" },
        { label: "BTS", icon: "pi pi-eye", to: "/empty" },
        { label: "BTS et parascolaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Carte plan atlas", icon: "pi pi-eye", to: "/empty" },
        { label: "Chasse et pêche", icon: "pi pi-eye", to: "/empty" },
        { label: "Cinéma et télévision", icon: "pi pi-eye", to: "/empty" },
        { label: "Collège et parascolaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Comic", icon: "pi pi-eye", to: "/empty" },
        { label: "Comptabilité", icon: "pi pi-eye", to: "/empty" },
        { label: "Cuisine", icon: "pi pi-eye", to: "/empty" },
        { label: "Développement personnel", icon: "pi pi-eye", to: "/empty" },
        { label: "Diététique et beauté", icon: "pi pi-eye", to: "/empty" },
        { label: "Droit", icon: "pi pi-eye", to: "/empty" },
        { label: "Ecologie", icon: "pi pi-eye", to: "/empty" },
        { label: "Economie", icon: "pi pi-eye", to: "/empty" },
        { label: "Enseignement primaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Enseignement primaire et parascolaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Enseignement professionnel", icon: "pi pi-eye", to: "/empty" },
        { label: "Enseignement secondaire 1er cycle", icon: "pi pi-eye", to: "/empty" },
        { label: "Enseignement secondaire 2ème cycle", icon: "pi pi-eye", to: "/empty" },
        { label: "Environnement", icon: "pi pi-eye", to: "/empty" },
        { label: "Esotérisme et art divinatoire", icon: "pi pi-eye", to: "/empty" },
        { label: "Ethnologie et anthropologie", icon: "pi pi-eye", to: "/empty" },
        { label: "Europe", icon: "pi pi-eye", to: "/empty" },
        { label: "Géographie", icon: "pi pi-eye", to: "/empty" },
        { label: "Gestion", icon: "pi pi-eye", to: "/empty" },
        { label: "Histoire de france", icon: "pi pi-eye", to: "/empty" },
        { label: "Histoire de suisse", icon: "pi pi-eye", to: "/empty" },
        { label: "Histoire internationale", icon: "pi pi-eye", to: "/empty" },
        { label: "Humour", icon: "pi pi-eye", to: "/empty" },
        { label: "Informatique et entreprise", icon: "pi pi-eye", to: "/empty" },
        { label: "Intelligence artificielle", icon: "pi pi-eye", to: "/empty" },
        { label: "Internet", icon: "pi pi-eye", to: "/empty" },
        { label: "Jardinage", icon: "pi pi-eye", to: "/empty" },
        { label: "Jeux", icon: "pi pi-eye", to: "/empty" },
        { label: "Langage informatique", icon: "pi pi-eye", to: "/empty" },
        { label: "Lycée et parascolaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Manga", icon: "pi pi-eye", to: "/empty" },
        { label: "Maternelle et parascolaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Médecine", icon: "pi pi-eye", to: "/empty" },
        { label: "Mer", icon: "pi pi-eye", to: "/empty" },
        { label: "Mode", icon: "pi pi-eye", to: "/empty" },
        { label: "Montagne", icon: "pi pi-eye", to: "/empty" },
        { label: "Multimédia", icon: "pi pi-eye", to: "/empty" },
        { label: "Nature", icon: "pi pi-eye", to: "/empty" },
        { label: "Océanie et pacifique", icon: "pi pi-eye", to: "/empty" },
        { label: "Pédagogie", icon: "pi pi-eye", to: "/empty" },
        { label: "Philosophie", icon: "pi pi-eye", to: "/empty" },
        { label: "Photographie et vidéo", icon: "pi pi-eye", to: "/empty" },
        { label: "Poésie", icon: "pi pi-eye", to: "/empty" },
        { label: "Proche et moyen orient", icon: "pi pi-eye", to: "/empty" },
        { label: "Progiciel", icon: "pi pi-eye", to: "/empty" },
        { label: "Programmation", icon: "pi pi-eye", to: "/empty" },
        { label: "Psychanalyse", icon: "pi pi-eye", to: "/empty" },
        { label: "Psychologie", icon: "pi pi-eye", to: "/empty" },
        { label: "Récit de voyage", icon: "pi pi-eye", to: "/empty" },
        { label: "Religion et spiritualité", icon: "pi pi-eye", to: "/empty" },
        { label: "Réseaux et télécommunication", icon: "pi pi-eye", to: "/empty" },
        { label: "Roman historique", icon: "pi pi-eye", to: "/empty" },
        { label: "Roman policier", icon: "pi pi-eye", to: "/empty" },
        { label: "Roman sentimental", icon: "pi pi-eye", to: "/empty" },
        { label: "Santé", icon: "pi pi-eye", to: "/empty" },
        { label: "Science fiction", icon: "pi pi-eye", to: "/empty" },
        { label: "Sciences", icon: "pi pi-eye", to: "/empty" },
        { label: "Sciences politiques et géopolitiques", icon: "pi pi-eye", to: "/empty" },
        { label: "Société et politique", icon: "pi pi-eye", to: "/empty" },
        { label: "Sociologie", icon: "pi pi-eye", to: "/empty" },
        { label: "Spectacle et musique", icon: "pi pi-eye", to: "/empty" },
        { label: "Sport", icon: "pi pi-eye", to: "/empty" },
        { label: "Système d'exploitation", icon: "pi pi-eye", to: "/empty" },
        { label: "Technique et parascolaire", icon: "pi pi-eye", to: "/empty" },
        { label: "Théatre", icon: "pi pi-eye", to: "/empty" },
        { label: "Vin alcool boisson", icon: "pi pi-eye", to: "/empty" },
      ],
    },
  ];

  const routers = [
    {
      path: "/",
      component: Dashboard,
      exact: true,
      meta: { breadcrumb: [{ parent: "Dashboard", label: "Dashboard" }] },
    },
    {
      path: "/formlayout",
      component: FormLayoutDemo,
      meta: { breadcrumb: [{ parent: "UI Kit", label: "Form Layout" }] },
    },
    { path: "/input", component: InputDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Input" }] } },
    {
      path: "/floatlabel",
      component: FloatLabelDemo,
      meta: { breadcrumb: [{ parent: "UI Kit", label: "Float Label" }] },
    },
    { path: "/button", component: ButtonDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Button" }] } },
    { path: "/table", component: TableDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Table" }] } },
    { path: "/list", component: ListDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "List" }] } },
    { path: "/tree", component: TreeDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Tree" }] } },
    { path: "/panel", component: PanelDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Panel" }] } },
    { path: "/overlay", component: OverlayDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Overlay" }] } },
    { path: "/media", component: MediaDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Media" }] } },
    { path: "/menu", component: MenuDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Menu" }] } },
    { path: "/messages", component: MessagesDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Messages" }] } },
    { path: "/file", component: FileDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "File" }] } },
    { path: "/chart", component: ChartDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Charts" }] } },
    { path: "/misc", component: MiscDemo, meta: { breadcrumb: [{ parent: "UI Kit", label: "Misc" }] } },
    { path: "/icons", component: IconsDemo, meta: { breadcrumb: [{ parent: "Utilities", label: "Icons" }] } },
    { path: "/widgets", component: Widgets, meta: { breadcrumb: [{ parent: "Utilities", label: "Widgets" }] } },
    { path: "/grid", component: GridDemo, meta: { breadcrumb: [{ parent: "Utilities", label: "Grid System" }] } },
    { path: "/spacing", component: SpacingDemo, meta: { breadcrumb: [{ parent: "Utilities", label: "Spacing" }] } },
    {
      path: "/elevation",
      component: ElevationDemo,
      meta: { breadcrumb: [{ parent: "Utilities", label: "Elevation" }] },
    },
    {
      path: "/typography",
      component: TypographyDemo,
      meta: { breadcrumb: [{ parent: "Utilities", label: "Typography" }] },
    },
    { path: "/display", component: DisplayDemo, meta: { breadcrumb: [{ parent: "Utilities", label: "Display" }] } },
    { path: "/flexbox", component: FlexBoxDemo, meta: { breadcrumb: [{ parent: "Utilities", label: "Flexbox" }] } },
    { path: "/text", component: TextDemo, meta: { breadcrumb: [{ parent: "Utilities", label: "Text" }] } },
    { path: "/crud", component: CrudDemo, meta: { breadcrumb: [{ parent: "Pages", label: "Crud" }] } },
    { path: "/calendar", component: CalendarDemo, meta: { breadcrumb: [{ parent: "Pages", label: "Calendar" }] } },
    { path: "/invoice", component: Invoice, meta: { breadcrumb: [{ parent: "Pages", label: "Invoice" }] } },
    { path: "/help", component: Help, meta: { breadcrumb: [{ parent: "Pages", label: "Help" }] } },
    { path: "/empty", component: EmptyPage, meta: { breadcrumb: [{ parent: "Pages", label: "Empty Page" }] } },
    {
      path: "/documentation",
      component: Documentation,
      meta: { breadcrumb: [{ parent: "Pages", label: "Documentation" }] },
    },
  ];

  useEffect(() => {
    if (staticMenuMobileActive) {
      blockBodyScroll();
    } else {
      unblockBodyScroll();
    }
  }, [staticMenuMobileActive]);

  const onInputStyleChange = (inputStyle) => {
    setInputStyle(inputStyle);
  };

  const onRippleChange = (e) => {
    PrimeReact.ripple = e.value;
    setRipple(e.value);
  };

  const onDocumentClick = () => {
    if (!searchClick && searchActive) {
      onSearchHide();
    }

    if (!userMenuClick) {
      setTopbarUserMenuActive(false);
    }

    if (!notificationMenuClick) {
      setTopbarNotificationMenuActive(false);
    }

    if (!rightMenuClick) {
      setRightMenuActive(false);
    }

    if (!menuClick) {
      if (isSlim()) {
        setMenuActive(false);
      }

      if (overlayMenuActive || staticMenuMobileActive) {
        hideOverlayMenu();
      }

      unblockBodyScroll();
    }

    if (configActive && !configClick) {
      setConfigActive(false);
    }

    searchClick = false;
    configClick = false;
    userMenuClick = false;
    rightMenuClick = false;
    notificationMenuClick = false;
    menuClick = false;
  };

  const onMenuClick = () => {
    menuClick = true;
  };

  const onMenuButtonClick = (event) => {
    menuClick = true;
    setTopbarUserMenuActive(false);
    setTopbarNotificationMenuActive(false);
    setRightMenuActive(false);

    if (isOverlay()) {
      setOverlayMenuActive((prevOverlayMenuActive) => !prevOverlayMenuActive);
    }

    if (isDesktop()) {
      setStaticMenuDesktopInactive((prevStaticMenuDesktopInactive) => !prevStaticMenuDesktopInactive);
    } else {
      setStaticMenuMobileActive((prevStaticMenuMobileActive) => !prevStaticMenuMobileActive);
    }

    event.preventDefault();
  };

  const onMenuitemClick = (event) => {
    if (!event.item.items) {
      hideOverlayMenu();

      if (isSlim()) {
        setMenuActive(false);
      }
    }
  };

  const onRootMenuitemClick = () => {
    setMenuActive((prevMenuActive) => !prevMenuActive);
  };

  const onMenuThemeChange = (name) => {
    setMenuTheme("layout-sidebar-" + name);
  };

  const onMenuModeChange = (e) => {
    setMenuMode(e.value);
  };

  const onColorSchemeChange = (e) => {
    setColorScheme(e.value);
  };

  const onTopbarUserMenuButtonClick = (event) => {
    userMenuClick = true;
    setTopbarUserMenuActive((prevTopbarUserMenuActive) => !prevTopbarUserMenuActive);

    hideOverlayMenu();

    event.preventDefault();
  };

  const onTopbarNotificationMenuButtonClick = (event) => {
    notificationMenuClick = true;
    setTopbarNotificationMenuActive((prevTopbarNotificationMenuActive) => !prevTopbarNotificationMenuActive);

    hideOverlayMenu();

    event.preventDefault();
  };

  const toggleSearch = () => {
    setSearchActive((prevSearchActive) => !prevSearchActive);
    searchClick = true;
  };

  const onSearchClick = () => {
    searchClick = true;
  };

  const onSearchHide = () => {
    setSearchActive(false);
    searchClick = false;
  };

  const onRightMenuClick = () => {
    rightMenuClick = true;
  };

  const onRightMenuButtonClick = (event) => {
    rightMenuClick = true;
    setRightMenuActive((prevRightMenuActive) => !prevRightMenuActive);
    hideOverlayMenu();
    event.preventDefault();
  };

  const onConfigClick = () => {
    configClick = true;
  };

  const onConfigButtonClick = () => {
    setConfigActive((prevConfigActive) => !prevConfigActive);
    configClick = true;
  };

  const hideOverlayMenu = () => {
    setOverlayMenuActive(false);
    setStaticMenuMobileActive(false);
    unblockBodyScroll();
  };

  const blockBodyScroll = () => {
    if (document.body.classList) {
      document.body.classList.add("blocked-scroll");
    } else {
      document.body.className += " blocked-scroll";
    }
  };

  const unblockBodyScroll = () => {
    if (document.body.classList) {
      document.body.classList.remove("blocked-scroll");
    } else {
      document.body.className = document.body.className.replace(
        new RegExp("(^|\\b)" + "blocked-scroll".split(" ").join("|") + "(\\b|$)", "gi"),
        " "
      );
    }
  };

  const isSlim = () => {
    return menuMode === "slim";
  };

  const isOverlay = () => {
    return menuMode === "overlay";
  };

  const isDesktop = () => {
    return window.innerWidth > 991;
  };

  const containerClassName = classNames(
    "layout-wrapper",
    {
      "layout-overlay": menuMode === "overlay",
      "layout-static": menuMode === "static",
      "layout-slim": menuMode === "slim",
      "layout-sidebar-dim": colorScheme === "dim",
      "layout-sidebar-dark": colorScheme === "dark",
      "layout-overlay-active": overlayMenuActive,
      "layout-mobile-active": staticMenuMobileActive,
      "layout-static-inactive": staticMenuDesktopInactive && menuMode === "static",
      "p-input-filled": inputStyle === "filled",
      "p-ripple-disabled": !ripple,
    },
    colorScheme === "light" ? menuTheme : ""
  );

  return (
    <div className={containerClassName} data-theme={colorScheme} onClick={onDocumentClick}>
      <div className="layout-content-wrapper">
        <AppTopBar
          routers={routers}
          topbarNotificationMenuActive={topbarNotificationMenuActive}
          topbarUserMenuActive={topbarUserMenuActive}
          onMenuButtonClick={onMenuButtonClick}
          onSearchClick={toggleSearch}
          onTopbarNotification={onTopbarNotificationMenuButtonClick}
          onTopbarUserMenu={onTopbarUserMenuButtonClick}
          onRightMenuClick={onRightMenuButtonClick}
          onRightMenuButtonClick={onRightMenuButtonClick}
        ></AppTopBar>

        <div className="layout-content">
          {routers.map((router, index) => {
            if (router.exact) {
              return <Route key={`router${index}`} path={router.path} exact component={router.component} />;
            }

            return <Route key={`router${index}`} path={router.path} component={router.component} />;
          })}
        </div>

        <AppFooter />
      </div>

      <AppMenu
        model={menu}
        menuMode={menuMode}
        active={menuActive}
        mobileMenuActive={staticMenuMobileActive}
        onMenuClick={onMenuClick}
        onMenuitemClick={onMenuitemClick}
        onRootMenuitemClick={onRootMenuitemClick}
      ></AppMenu>

      {/* <AppRightMenu rightMenuActive={rightMenuActive} onRightMenuClick={onRightMenuClick}></AppRightMenu> */}

      <AppConfig
        configActive={configActive}
        menuMode={menuMode}
        onMenuModeChange={onMenuModeChange}
        menuTheme={menuTheme}
        onMenuThemeChange={onMenuThemeChange}
        colorScheme={colorScheme}
        onColorSchemeChange={onColorSchemeChange}
        onConfigClick={onConfigClick}
        onConfigButtonClick={onConfigButtonClick}
        rippleActive={ripple}
        onRippleChange={onRippleChange}
        inputStyle={inputStyle}
        onInputStyleChange={onInputStyleChange}
      ></AppConfig>

      <AppSearch searchActive={searchActive} onSearchClick={onSearchClick} onSearchHide={onSearchHide} />

      <div className="layout-mask modal-in"></div>
    </div>
  );
};

export default App;
