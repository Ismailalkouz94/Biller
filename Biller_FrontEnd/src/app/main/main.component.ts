import {
  Component,
  OnInit,
  OnDestroy,
  ViewChild,
  HostListener,
  ViewEncapsulation
} from "@angular/core";
import { MenuItems } from "../core/menu/menu-items/menu-items";
import { LoginComponent } from "../login/login.component";
import { HorizontalMenuItems } from "../core/menu/menu-items/horizontal-menu-items";
import { PageTitleService } from "../core/page-title/page-title.service";
import { TranslateService } from "ng2-translate/ng2-translate";
import { Router, NavigationEnd } from "@angular/router";
import { Subscription } from "rxjs/Subscription";
import { MediaChange, ObservableMedia } from "@angular/flex-layout";
import { TourService } from "ngx-tour-ng-bootstrap";
import PerfectScrollbar from "perfect-scrollbar";
import { UsersService } from "../shared/service/login.service";
import { appConfig } from "../app.config";
import { Http, Headers, RequestOptions } from "@angular/http";

import {
  PerfectScrollbarConfigInterface,
  PerfectScrollbarComponent,
  PerfectScrollbarDirective
} from "ngx-perfect-scrollbar";
const screenfull = require("screenfull");

import { ConfirmationService } from "../shared/service/api";
import { Message } from "../shared/service/api";

@Component({
  selector: "silk-layout",
  templateUrl: "./main-material.html",
  styleUrls: ["./main-material.scss"],
  encapsulation: ViewEncapsulation.None,
  providers: [ConfirmationService]
})
export class MainComponent implements OnInit, OnDestroy {
  changelang: string;
  lang: string;
  private _router: Subscription;
  header: string;
  currentLang: string = "ar";
  url: string;
  showSettings = false;
  dark: boolean;
  boxed: boolean;
  horizontalMenu: boolean;
  collapseSidebar: boolean;
  compactSidebar: boolean;
  customizerIn: boolean = false;
  headerGreen: boolean = false;
  headerRed: boolean = false;
  headerYellow: boolean = false;
  sideness: boolean;
  root: string = "rtl";
  dir: string = "rtl";
  chatpanelOpen: boolean = false;
  themeHeaderSkinColor: any = "header-default";
  themeSidebarSkinColor: any = "sidebar-default";

  browserLang: string;
    url2: string = 'assets/img/no-gambar.jpg';
  private _mediaSubscription: Subscription;
  sidenavOpen: boolean = true;
  sidenavMode: string = "side";
  isMobile: boolean = false;
  private _routerEventsSubscription: Subscription;

  @ViewChild("sidenav") sidenav;
  public config: PerfectScrollbarConfigInterface = {};
  constructor(
    private confirmationService: ConfirmationService,
    public tourService: TourService,
    public menuItems: MenuItems,
    public userLoginList: LoginComponent,
    public horizontalMenuItems: HorizontalMenuItems,
    private pageTitleService: PageTitleService,
    public translate: TranslateService,
    private router: Router,
    private media: ObservableMedia,
    private userService: UsersService,
    public http: Http
  ) {}

  menuesList;
  ngOnInit() {
    // this.translate.use("ar");
    
    this.getUserPrefData2();
   
    if(localStorage.getItem("menuesList") !== "" && localStorage.getItem("menuesList") !== 'undefined'){
    this.menuesList = JSON.parse(localStorage.getItem("menuesList")); }
    // this._mediaSubscription = this.userService.cast.subscribe(res => {
    //     this.posts = res;
    // });

    this.pageTitleService.title.subscribe((val: string) => {
      this.header = val;
    });

    this._router = this.router.events
      .filter(event => event instanceof NavigationEnd)
      .subscribe((event: NavigationEnd) => {
        this.url = event.url;
      });

    if (
      this.url != "/session/login" &&
      this.url != "/session/register" &&
      this.url != "/session/forgot-password" &&
      this.url != "/session/lockscreen"
    ) {
      const elemSidebar = <HTMLElement>document.querySelector(
        ".sidebar-container "
      );

      if (window.matchMedia(`(min-width: 960px)`).matches) {
        // Ps.initialize(elemSidebar, { wheelSpeed: 2, suppressScrollX: true });
        const ps = new PerfectScrollbar(elemSidebar, {
          wheelSpeed: 2,
          suppressScrollX: true
        });
      }
    }
    if (this.media.isActive("xs") || this.media.isActive("sm")) {
      this.sidenavMode = "over";
      this.sidenavOpen = false;
    }
    this._mediaSubscription = this.media
      .asObservable()
      .subscribe((change: MediaChange) => {
        let isMobile = change.mqAlias == "xs" || change.mqAlias == "sm";

        this.isMobile = isMobile;
        this.sidenavMode = isMobile ? "over" : "side";
        this.sidenavOpen = !isMobile;
      });

    this._routerEventsSubscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd && this.isMobile) {
        this.sidenav.close();
      }
    });
  }

  

    getUserPrefData2() {
        
        this.themeSidebarSkinColor = localStorage.getItem('themeSidebarSkinColor');

        this.themeHeaderSkinColor = localStorage.getItem('themeHeaderSkinColor');
        
        if (localStorage.getItem('dir') == "rtl") {
            this.dir = "rtl";
            this.sideness = true;
            this.root = "rtl";
        } else {
            this.dir = "ltr";
            this.sideness = false;
            this.root = "ltr";
        }
    
    
            this.url2 = localStorage.getItem("img");  

            this.translate.use(localStorage.getItem('currentLang'));
            this.tourService.events$.subscribe(console.log);
            this.tourService.start();


        if (localStorage.getItem('horizontalMenu') == "true") {
            this.horizontalMenu = true;
        } else {
            this.horizontalMenu = false;
        }


        if (localStorage.getItem('dark') == "true") {
            this.dark = true;
        } else {
            this.dark = false;
        }

        if (localStorage.getItem('boxed') == "true") {
            this.boxed = true;
        } else {
            this.boxed = false;
        }

        if (localStorage.getItem('collapseSidebar') == "true") {
            this.collapseSidebar = true;
        } else {
            this.collapseSidebar = false;
        }

    }


  ngOnDestroy() {
    this._router.unsubscribe();
    this._mediaSubscription.unsubscribe();
  }

  isFullscreen: boolean = false;

  menuMouseOver(): void {
    if (
      window.matchMedia(`(min-width: 960px)`).matches &&
      this.collapseSidebar
    ) {
      this.sidenav.mode = "over";
    }
  }

  menuMouseOut(): void {
    if (
      window.matchMedia(`(min-width: 960px)`).matches &&
      this.collapseSidebar
    ) {
      this.sidenav.mode = "side";
    }
  }

  toggleFullscreen() {
    if (screenfull.enabled) {
      screenfull.toggle();
      this.isFullscreen = !this.isFullscreen;
    }
  }

  customizerFunction() {
    this.customizerIn = !this.customizerIn;
  }
  headerSkinColorFunction(color) {
    this.themeHeaderSkinColor = color;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "themeHeaderSkinColor");
    urlSearchParams.append("value", color);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("themeHeaderSkinColor", data.value);
          this.themeHeaderSkinColor = localStorage.getItem(
            "themeHeaderSkinColor"
          );
        },
        error => {}
      );
  }
  sidebarSkinColorFunction(color) {
    this.themeSidebarSkinColor = color;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "themeSidebarSkinColor");
    urlSearchParams.append("value", color);
    let body = urlSearchParams.toString();
    // { userLoginId: sessionStorage.getItem("userLoginId"), key: 'themeSidebarSkinColor', value: color }
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("themeSidebarSkinColor", data.value);
          this.themeSidebarSkinColor = localStorage.getItem(
            "themeSidebarSkinColor"
          );
        },
        error => {}
      );
  }
  //  addMenuItem(): void {
  //   this.menuItems.add({
  //     state: 'pages',
  //     name: 'SILK MENU',
  //    type: 'sub',
  //   icon: 'trending_flat',
  //  children: [
  //     { state: 'blank', name: 'SUB MENU1' },
  //     { state: 'blank', name: 'SUB MENU2' }
  //    ]
  //  });
  //  }

  onActivate(e, scrollContainer) {
    scrollContainer.scrollTop = 0;
  }




  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]);
      console.log(event.target.files[0].name);

      reader.onload = event => {
        this.url2 = event.target["result"];

        let url = appConfig.apiUrl + "userPref/add";
        var headers = new Headers();
        headers.append("Content-Type", "application/x-www-form-urlencoded");
        let urlSearchParams = new URLSearchParams();
        urlSearchParams.append(
          "userLoginId",
          sessionStorage.getItem("userLoginId")
        );
        urlSearchParams.append("key", "img");
        urlSearchParams.append("value", this.url2);
        let body = urlSearchParams.toString();
        this.http
          .post(url, body, { headers: headers })
          .map(res => res.json())
          .subscribe(
            data => {
              localStorage.setItem("img", data.value);
              this.url2 = localStorage.getItem("img");
            },
            error => {}
          );
      };
    }

  }


  horMenu(horizontalMenu) {
    this.horizontalMenu = horizontalMenu;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "horizontalMenu");
    urlSearchParams.append("value", horizontalMenu);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("horizontalMenu", data.value);
          if (localStorage.getItem("horizontalMenu") == "true") {
            this.horizontalMenu = true;
          } else {
            this.horizontalMenu = false;
          }
        },
        error => {}
      );
  }
  darkTheme(dark) {
    this.dark = dark;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "dark");
    urlSearchParams.append("value", dark);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("dark", data.value);
          if (localStorage.getItem("dark") == "true") {
            this.dark = true;
          } else {
            this.dark = false;
          }
        },
        error => {}
      );
  }
  Boxed(boxed) {
    this.boxed = boxed;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "boxed");
    urlSearchParams.append("value", boxed);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("boxed", data.value);
          if (localStorage.getItem("boxed") == "true") {
            this.boxed = true;
          } else {
            this.boxed = false;
          }
        },
        error => {}
      );
  }
  rtlSupports(dir) {
    this.dir = dir;
    this.root = dir;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "dir");
    urlSearchParams.append("value", this.dir);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("dir", data.value);
          if (localStorage.getItem('dir') == "rtl") {
            this.dir = "rtl";
            this.sideness = true;
            this.root = "rtl";
        } else {
            this.dir = "ltr";
            this.sideness = false;
            this.root = "ltr";
        }
        },
        error => {}
      );
  }
  compSidebar(collapseSidebar) {
    this.collapseSidebar = collapseSidebar;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "collapseSidebar");
    urlSearchParams.append("value", collapseSidebar);
    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("collapseSidebar", data.value);
          if (localStorage.getItem("collapseSidebar") == "true") {
            this.collapseSidebar = true;
          } else {
            this.collapseSidebar = false;
          }
        },
        error => {}
      );
  }
  logout() {
    this.confirmationService.confirm({
        message: "هل انت متأكد ؟",
        header: "رسالة تأكيد",
        icon: "fa-question-circle",
        accept: () => {
          sessionStorage.clear();
          localStorage.clear();
          this.router.navigate(["login"]);
        },
        reject: () => {
          this.msgs = [
            { severity: "info", summary: "Rejected", detail: "You have rejected" }
          ];
        }
      });
  }

  changeLanguage(currentLang) {
    this.currentLang = currentLang;
    let url = appConfig.apiUrl + "userPref/add";
    var headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append(
      "userLoginId",
      sessionStorage.getItem("userLoginId")
    );
    urlSearchParams.append("key", "currentLang");
    urlSearchParams.append("value", this.currentLang);

    let body = urlSearchParams.toString();
    this.http
      .post(url, body, { headers: headers })
      .map(res => res.json())
      .subscribe(
        data => {
          localStorage.setItem("currentLang", data.value);
          if (localStorage.getItem("currentLang") == "ar") {
            this.translate.use(localStorage.getItem("currentLang"));
            this.tourService.events$.subscribe(console.log);
            this.tourService.start();
          } else {
            this.translate.use(localStorage.getItem("currentLang"));
            this.tourService.events$.subscribe(console.log);
            this.tourService.start();
          }
        },
        error => {}
      );
  }

  msgs: Message[] = [];


}
