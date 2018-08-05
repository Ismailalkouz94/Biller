import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../core/route-animation/route.animation";
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Http , Headers  } from '@angular/http';
import 'rxjs/add/operator/map';
import {ConfirmationService} from '../../shared/guards/tree/api';
import {Message} from '../../shared/guards/tree/api';

import { HttpRequestsService } from '../../core/http-request-api/http-requests.service';

const password = new FormControl('', Validators.required);
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));

@Component({
  selector: 'ms-billers',
  templateUrl: './billers.component.html',
  styleUrls: ['../../../assets/scss/myStyle.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    "[@fadeInAnimation]": 'true'
  },
  animations: [fadeInAnimation,
    trigger('dialog', [
      transition('void => *', [
        style({ transform: 'scale3d(.3, .3, .3)' }),
        animate(100)
      ]),
      transition('* => void', [
        animate(100, style({ transform: 'scale3d(.0, .0, .0)' }))
      ])
    ])],
    providers: [ConfirmationService]
})

export class BillersComponent implements OnInit {

  constructor(private confirmationService: ConfirmationService){}
  ngOnInit() {

  }
    msgs: Message[] = [];
    buttonDisabled: boolean;
    buttonDisabled2: boolean;

  confirm1() {
        this.confirmationService.confirm({
            message: 'هل انت متأكد؟',
            header: 'تأكيد',
            icon: 'fa-question-circle',
            accept: () => {
                this.buttonDisabled = true;
            },
            reject: () => {
                 this.buttonDisabled = false;
            }
        });
    }
  confirm2() {
        this.confirmationService.confirm({
            message: 'هل انت متأكد؟',
            header: 'تأكيد',
            icon: 'fa-question-circle',
            accept: () => {
                this.buttonDisabled = true;
            },
            reject: () => {
                 this.buttonDisabled = false;
            }
        });
    }
    

}
