import {
  Component,
  OnInit,
  ViewEncapsulation,
  Injectable
} from "@angular/core";
import 'assets/login-animation.js';
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
import { NotificationsService } from '../notifications/notifications.service';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
// import { ViewContainerRef } from '@angular/core';
import { ANIMATION_TYPES } from 'ngx-loading';

@Component({
  selector: "ms-signup-session",
  templateUrl: "./signup-component.html",
  styleUrls: ["./signup-component.scss"],
  encapsulation: ViewEncapsulation.None,

  host: {
    "[@fadeInAnimation]": "true"
  },
  animations: [fadeInAnimation]
})
@Injectable()
export class SignupComponent {
 
  
}
