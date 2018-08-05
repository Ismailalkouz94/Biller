import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

import { PageTitleService } from '../../core/page-title/page-title.service';

import { Http , Headers  } from '@angular/http';
import 'rxjs/add/operator/map';

import { HttpRequestsService } from '../../core/http-request-api/http-requests.service';


@Component({
  selector: 'ms-component',
  templateUrl: './component.component.html',
  styleUrls: ['../../../assets/scss/myStyle.scss']

})

export class ComponentComponent implements OnInit {

  public form: FormGroup;
  constructor(private fb: FormBuilder,private pageTitleService: PageTitleService, public http: Http){ }

  ngOnInit() { }


}
