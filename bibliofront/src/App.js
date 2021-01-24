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
      label: "Notre réseau",
      items: [
        { label: "Accueil", icon: "pi pi-home", to: "/empty" },
        { label: "Nos bibliothèques", icon: "pi pi-briefcase", to: "/empty" },
        { label: "Nos équipes", icon: "pi pi-users", to: "/empty" },
      ],
    },
    { separator: true },
    {
      label: "Nos livres",
      items: [{ label: "Recherche", icon: "pi pi-search", to: "/empty" }],
    },
    { separator: true },
    {
      label: "Nos rayons",
      items: [
        {
          label: "Histoire, actualité",
          icon: "",
          items: [
            { label: "Actualité médiatique et politique", icon: "", to: "/empty" },
            { label: "Histoire de france", to: "/empty" },
            { label: "Histoire de suisse", icon: "", to: "/empty" },
            { label: "Histoire internationale", icon: "", to: "/empty" },
            { label: "Sciences politiques et géopolitiques", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Tourisme, voyages, géographie",
          icon: "",
          items: [
            { label: "Afrique", icon: "", to: "/empty" },
            { label: "Amériques", icon: "", to: "/empty" },
            { label: "Asie", icon: "", to: "/empty" },
            { label: "Cartes, plans, atlas", icon: "", to: "/empty" },
            { label: "Europe", icon: "", to: "/empty" },
            { label: "Géographie", icon: "", to: "/empty" },
            { label: "Océanie et pacifique", icon: "", to: "/empty" },
            { label: "Proche et moyen orient", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Nature, animaux",
          icon: "",
          items: [
            { label: "Animaux", icon: "", to: "/empty" },
            { label: "Chasse, pêche", icon: "", to: "/empty" },
            { label: "Jardinage", icon: "", to: "/empty" },
            { label: "Mer", icon: "", to: "/empty" },
            { label: "Montagne", icon: "", to: "/empty" },
            { label: "Nature", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Arts, spectacles",
          icon: "",
          items: [
            { label: "Art décoratif", icon: "", to: "/empty" },
            { label: "Beaux arts", icon: "", to: "/empty" },
            { label: "Cinéma, télévision", icon: "", to: "/empty" },
            { label: "Mode", icon: "", to: "/empty" },
            { label: "Photographie, vidéo", icon: "", to: "/empty" },
            { label: "Spectacle, musique", icon: "", to: "/empty" },
            { label: "Théatre", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Bandes dessinées, comics, mangas, humour",
          icon: "",
          items: [
            { label: "Bandes dessinées", icon: "", to: "/empty" },
            { label: "Comics", icon: "", to: "/empty" },
            { label: "Humour", icon: "", to: "/empty" },
            { label: "Mangas", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Loisirs, jeux",
          icon: "",
          items: [
            { label: "Bricolage", icon: "", to: "/empty" },
            { label: "Jeux", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Scolaire, parascolaire",
          icon: "",
          items: [
            { label: "BTS", icon: "", to: "/empty" },
            { label: "BTS, parascolaire", icon: "", to: "/empty" },
            { label: "Collège et parascolaire", icon: "", to: "/empty" },
            { label: "Enseignement primaire", icon: "", to: "/empty" },
            { label: "Enseignement primaire et parascolaire", icon: "", to: "/empty" },
            { label: "Enseignement professionnel", icon: "", to: "/empty" },
            { label: "Enseignement secondaire 1er cycle", icon: "", to: "/empty" },
            { label: "Enseignement secondaire 2ème cycle", icon: "", to: "/empty" },
            { label: "Lycée et parascolaire", icon: "", to: "/empty" },
            { label: "Maternelle et parascolaire", icon: "", to: "/empty" },
            { label: "Technique et parascolaire", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Droit, économie, gestion, comptabilité",
          icon: "",
          items: [
            { label: "Comptabilité", icon: "", to: "/empty" },
            { label: "Droit", icon: "", to: "/empty" },
            { label: "Economie", icon: "", to: "/empty" },
            { label: "Gestion", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Cuisine, vins, boissons",
          icon: "",
          items: [
            { label: "Arts culinaires", icon: "", to: "/empty" },
            { label: "Vin alcool boisson", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Bien-être, dans sa tête et dans son corps",
          icon: "",
          items: [{ label: "Développement personnel", icon: "", to: "/empty" }],
        },
        {
          label: "Santé, diététique, sport",
          icon: "",
          items: [
            { label: "Diététique, beauté", icon: "", to: "/empty" },
            { label: "Santé", icon: "", to: "/empty" },
            { label: "Sport", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Développement durable, écologie",
          icon: "",
          items: [
            { label: "Ecologie", icon: "", to: "/empty" },
            { label: "Environnement", icon: "", to: "/empty" },
            { label: "Société, politique", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Religions, spiritualité, ésotérisme",
          icon: "",
          items: [
            { label: "Esotérisme, art divinatoire", icon: "", to: "/empty" },
            { label: "Religion, spiritualité", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Sciences humaines et sociales",
          icon: "",
          items: [
            { label: "Ethnologie, anthropologie", icon: "", to: "/empty" },
            { label: "Philosophie", icon: "", to: "/empty" },
            { label: "Sociologie", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Informatique, multimédia",
          icon: "",
          items: [
            { label: "Informatique et entreprise", icon: "", to: "/empty" },
            { label: "Intelligence artificielle", icon: "", to: "/empty" },
            { label: "Internet", icon: "", to: "/empty" },
            { label: "Langage informatique", icon: "", to: "/empty" },
            { label: "Multimédia", icon: "", to: "/empty" },
            { label: "Progiciels", icon: "", to: "/empty" },
            { label: "Programmation", icon: "", to: "/empty" },
            { label: "Réseaux, télécommunication", icon: "", to: "/empty" },
            { label: "Systèmes d'exploitation", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Sciences, techniques, médecine",
          icon: "",
          items: [
            { label: "Médecine", icon: "", to: "/empty" },
            { label: "Sciences", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Psychologie, psychanalyse, pédagogie",
          icon: "",
          items: [
            { label: "Pédagogie", icon: "", to: "/empty" },
            { label: "Psychanalyse", icon: "", to: "/empty" },
            { label: "Psychologie", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Littérature, fiction",
          icon: "",
          items: [
            { label: "Poésie", icon: "", to: "/empty" },
            { label: "Récit de voyage", icon: "", to: "/empty" },
            { label: "Romans historiques", icon: "", to: "/empty" },
            { label: "Romans policiers", icon: "", to: "/empty" },
            { label: "Romans sentimentaux", icon: "", to: "/empty" },
          ],
        },
        {
          label: "Policier, Science-fiction",
          icon: "",
          items: [
            { label: "Romans policiers", icon: "", to: "/empty" },
            { label: "Science-fiction", icon: "", to: "/empty" },
          ],
        },
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
