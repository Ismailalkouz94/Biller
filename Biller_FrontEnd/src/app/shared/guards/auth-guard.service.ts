import { Injectable } from "@angular/core";
import { CanActivate, CanActivateChild, Router } from "@angular/router";
import { UsersService } from "../../shared/service/login.service";
import { Http, Headers, RequestOptions } from "@angular/http";
import { appConfig } from "../../app.config";

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {
  /**
   *  Check of the user is logged in so you check that by request an empty login request
   *  that return the data if logged in else you will route the user to the login page
   * but before you make the request check if the user already logged in and the data is saved in UserService
   * by subscribing to the cast object and check if it have a data else you will hit the request and continue with the process
   */
  parentMenuList;
  getData() {}
  constructor(
    public http: Http,
    private userService: UsersService,
    private router: Router
  ) {}
  canActivate() {
    console.log("I am checking to see if you are logged in");
    return true;
  }

  canActivateChild() {
    if (
      localStorage.getItem("partyId") === "" ||
      localStorage.getItem("partyId") === "undefined" ||
      localStorage.getItem("partyId") === null ||
      (localStorage.getItem("menuesList") === "" ||
        localStorage.getItem("menuesList") === "undefined" ||
        localStorage.getItem("menuesList") === null)
    ) {
      sessionStorage.clear();
      localStorage.clear();
      this.router.navigate(["/login"]);
      return false;
    } else {
      return true;
    }
  }
}
