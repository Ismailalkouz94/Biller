import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";

@Component({
  selector: 'ms-enquiry',
  templateUrl: './enquiry.component.html',
  styleUrls: ['../../assets/scss/myStyle.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    "[@fadeInAnimation]": 'true'
  },
  animations: [ fadeInAnimation ]
})

export class EnquiryComponent  implements OnInit{

  	selectedIndex: number = 0;

    constructor(private pageTitleService: PageTitleService) {

 }

    ngOnInit() {

    }


  	nextStep() {
    	this.selectedIndex += 1;
  	}

  	previousStep() {
    	this.selectedIndex -= 1;
  	}   
	fetch(cb) {
	  const req = new XMLHttpRequest();
	  req.open('GET', `assets/data/company.json`);

	  req.onload = () => {
	    cb(JSON.parse(req.response));
	  };

	  req.send();
	}

}

