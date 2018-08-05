import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";

import { Http , Headers  } from '@angular/http';
import 'rxjs/add/operator/map';

import { HttpRequestsService } from '../core/http-request-api/http-requests.service';

@Component({
  selector: 'ms-party',
  templateUrl: './party.component.html',
  styleUrls: ['../../assets/scss/myStyle.scss'],
    encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },

})

export class PartyComponent implements OnInit {
  public form: FormGroup;
  constructor(private fb: FormBuilder,private pageTitleService: PageTitleService, public http: Http){ }


   	ngOnInit() {
   	}

 

}
