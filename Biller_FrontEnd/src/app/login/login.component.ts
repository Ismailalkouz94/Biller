import {
  Component,
  OnInit,
  ViewEncapsulation,
  Injectable
} from "@angular/core";
import { Router } from "@angular/router";
import { NgModel } from "@angular/forms";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { Http, Headers, RequestOptions } from "@angular/http";
import { appConfig } from "../app.config";
import { fadeInAnimation } from "../core/route-animation/route.animation";
import { UsersService } from "../shared/service/login.service";
import { NotificationsService } from '../notifications/notifications.service';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
// import { ViewContainerRef } from '@angular/core';
import { ANIMATION_TYPES } from 'ngx-loading';

declare var $: any;
@Component({
  selector: "ms-login-session",
  templateUrl: "./login-component.html",
  styleUrls: ["./login-component.scss"],
  encapsulation: ViewEncapsulation.None,

  host: {
    "[@fadeInAnimation]": "true"
  },
  animations: [fadeInAnimation]
})
@Injectable()
export class LoginComponent {
  result: any;
  parentMenuList;
  email: string;
  loading: boolean = false; 
  password;
  public ANIMATION_TYPES = ANIMATION_TYPES;
  public formdata: FormGroup;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    public http: Http,
    public toastr: ToastsManager,
    // vcr: ViewContainerRef,
    private userService: UsersService,
    private notificationsService: NotificationsService,
    
  ) {
    // this.toastr.setRootViewContainerRef(vcr);
  
  }
  

  showError(message) {
    this.toastr.error(message, "Oops!");
  }

  userName: string;
  ngOnInit() {
    this.formdata = this.fb.group({
      userName: [null, Validators.required],
      partyId: [null, Validators.required],
      password: [null, Validators.required]
    });

    var panelOne = $('.form-panel.two').height(),
    panelTwo = $('.form-panel.two')[0].scrollHeight,
    panelThree = $('.form-panel.three')[0].scrollHeight;
    
  
	$('.form-panel.two').not('.form-panel.two.active').on('click', function(e) {
	  e.preventDefault();
  
	  $('.form-toggle').addClass('visible');
    $('.form-panel.one').addClass('hidden');
    $('.form-panel.three').addClass('hidden');
	  $('.form-panel.two').addClass('active');
	  $('.form').animate({
		'height': panelTwo
	  }, 200);
  });
  $('.form-panel.three').not('.form-panel.three.active').on('click', function(e) {
	  e.preventDefault();
  
	  $('.form-toggle2').addClass('visible');
    $('.form-panel.one').addClass('hidden');
    $('.form-panel.two').addClass('hidden');
	  $('.form-panel.three').addClass('active');
	  $('.form').animate({
		'height': panelThree
	  }, 200);
	});
  
	$('.form-toggle').on('click', function(e) {
	  e.preventDefault();
	  $(this).removeClass('visible');
    $('.form-panel.one').removeClass('hidden');
    $('.form-panel.three').removeClass('hidden');
	  $('.form-panel.two').removeClass('active');
	  $('.form').animate({
		'height': panelOne
	  }, 200);
  });
  $('.form-toggle2').on('click', function(e) {
	  e.preventDefault();
	  $(this).removeClass('visible');
    $('.form-panel.one').removeClass('hidden');
    $('.form-panel.three').removeClass('active');
	  $('.form-panel.two').removeClass('hidden');
	  $('.form').animate({
		'height': panelOne
	  }, 200);
	});
  }

  login(data) {
    this.loading = true;
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    var link = appConfig.apiUrl + "userLogin/checkLogin";
    var body = JSON.stringify(this.formdata.value);
    this.http.post(link, body, { headers }).map(res => res.json()).subscribe(
      res => {
        this.loading = false;
        if (res.isError == false) {
          sessionStorage.setItem("userLoginId", res.data.userLoginId);
          sessionStorage.setItem("partyId", this.formdata.get('partyId').value);
          localStorage.setItem("userLoginId", res.data.userLoginId);
          localStorage.setItem("partyId", this.formdata.get('partyId').value);
          this.getUserPrefData();
        } else {
          // this.notificationsService.test();
          this.loading = false;
          // this.showError(res.messageAR);
          alert(res.messageAR);
        }

      },
      error => {
        // this.showError("يرجى التأكد من اسم المستخدم والرقم السري");
        alert("يرجى التأكد من اسم المستخدم والرقم السري");
        this.loading = false;

      }
    );
  }

  getUserLoginData(userName, partyId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({
      headers: headers,
      params: { userName: userName, partyId: partyId }
    });
    this.http
      .get(appConfig.apiUrl + "userLogin/findByUserName", options)
      .map(res => res.json())
      .subscribe(data => {
        sessionStorage.setItem("userName", data.userName);
        sessionStorage.setItem("partyId", data.party.partyId);
        sessionStorage.setItem("userLoginId", data.userLoginId);
      });
  }



  getMenus(userName, partyId, password) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    var link = appConfig.apiUrl + 'userLogin/getMenusSys';

    var body = {
      userName: userName,
      password: password,
      partyId: partyId
    }
    this.http.post(link, body, { headers }).map(res => res.json()).subscribe((parentMenuList) => {
      this.parentMenuList = parentMenuList;
      localStorage.setItem("menuesList", JSON.stringify(parentMenuList));
     
      this.router.navigate([parentMenuList[0].state]);
    },
      error => {
      }
    );
  }

  getUserPrefData() {  
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { userLoginId: localStorage.getItem("userLoginId") } });
    this.http.get(appConfig.apiUrl + 'userPref/findByUserLoginId', options).map(res => res.json()).subscribe((data) => {

      for (let item in data) {
       
        switch (data[item].key) {
          
          case 'themeSidebarSkinColor':
            if (data[item].value != "") {
            
              localStorage.setItem("themeSidebarSkinColor", data[item].value);
              break;
            }
          case 'themeHeaderSkinColor':
            if (data[item].value != "") {
              localStorage.setItem("themeHeaderSkinColor", data[item].value);
              break;
            }
            case 'dir':
            if (data[item].value != "") {
              if (data[item].value == "rtl") {
                localStorage.setItem("dir", "rtl");
                break;
              } else {
                localStorage.setItem("dir", "ltr");
                break;
              }
            }
          case 'currentLang':
          localStorage.setItem("currentLang", data[item].value);
              break;

              case 'horizontalMenu':
            if (data[item].value != "") {
              if (data[item].value == "true") {
                localStorage.setItem("horizontalMenu", "true");
                break;
              } else {
                localStorage.setItem("horizontalMenu", "false");
                break;
              }
            }
          case 'dark':
            if (data[item].value != "") {
              if (data[item].value == "true") {
                localStorage.setItem("dark", "true");
                break;
              } else {
                localStorage.setItem("dark", "false");
                break;
              }

            }
          case 'boxed':
            if (data[item].value != "") {
              if (data[item].value == "true") {
                localStorage.setItem("boxed", "true");
                break;
              } else {
                localStorage.setItem("boxed", "false");
                break;
              }

            }
          case 'collapseSidebar':
            if (data[item].value != "") {
              if (data[item].value == "true") {
                localStorage.setItem("collapseSidebar", "true");
                break;
              } else {
                localStorage.setItem("collapseSidebar", "false");
                break;
              }

            }

            case 'img':
            if (data[item].value != "") {
            
                localStorage.setItem("img", data[item].value);
                break;


            }

        }
      }
      this.getMenus(this.formdata.get('userName').value, this.formdata.get('partyId').value, this.formdata.get("password").value);

    });


  }


 

  
}
