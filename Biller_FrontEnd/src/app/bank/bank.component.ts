import { Component, OnInit ,Input,ViewEncapsulation ,Output, HostListener, EventEmitter, Directive, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";
import {Http} from "@angular/http";
import { TranslateService } from 'ng2-translate/ng2-translate';
const password = new FormControl('', Validators.required);
const confirmPassword = new FormControl('', CustomValidators.equalTo(password));


@Component({
    selector: 'ms-bank',
    templateUrl: './bank.component.html',
    styleUrls: ['../../assets/scss/myStyle.scss'],
    encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ],
})
export class BankComponent implements OnInit {
  date1: Date;
 rangeDates: Date[];
    
    minDate: Date;
    test:any ="test";
    
    maxDate: Date;
    
    invalidDates: Array<Date>;
    
    es: any;
      public form: FormGroup;
  	constructor(public translate: TranslateService,private fb: FormBuilder, private pageTitleService: PageTitleService,private http: Http) {}
  	ngOnInit() {
        this.translate.use("ar");
    	this.form = this.fb.group({
      	fname: [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])],
      	email: [null, Validators.compose([Validators.required, CustomValidators.email])],
      	range: [null, Validators.compose([Validators.required, CustomValidators.range([5, 9])])],
      	url: [null, Validators.compose([Validators.required, CustomValidators.url])],
      	date: [null, Validators.compose([Validators.required, CustomValidators.date])],
      	creditCard: [null, Validators.compose([Validators.required, CustomValidators.creditCard])],
      	phone: [null, Validators.compose([Validators.required, CustomValidators.phone('en-US')])],
      	gender: [null, Validators.required],
      	password: password,
      	confirmPassword: confirmPassword
    	});

        this.es = {
        	firstDayOfWeek: 1,
        	dayNames: [ "domingo","lunes","martes","miércoles","jueves","viernes","sábado" ],
        	dayNamesShort: [ "dom","lun","mar","mié","jue","vie","sáb" ],
        	dayNamesMin: [ "D","L","M","X","J","V","S" ],
            monthNames: [ "enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre" ],
        	monthNamesShort: [ "ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic" ],
            today: 'Hoy',
            clear: 'Borrar'
        };
        
        let today = new Date();
        let month = today.getMonth();
        let year = today.getFullYear();
        let prevMonth = (month === 0) ? 11 : month -1;
        let prevYear = (prevMonth === 11) ? year - 1 : year;
        let nextMonth = (month === 11) ? 0 : month + 1;
        let nextYear = (nextMonth === 0) ? year + 1 : year;
        this.minDate = new Date();
        this.minDate.setMonth(prevMonth);
        this.minDate.setFullYear(prevYear);
        this.maxDate = new Date();
        this.maxDate.setMonth(nextMonth);
        this.maxDate.setFullYear(nextYear);
        
        let invalidDate = new Date();
        invalidDate.setDate(today.getDate() - 1);
        this.invalidDates = [today,invalidDate];
  	} 

      public show:boolean = false;
      public buttonName:any = 'Show';
      public arraw:any = 'arrow_drop_down';
    
  
    
      toggle() {
        this.show = !this.show;
    
        // CHANGE THE NAME OF THE BUTTON.
        if(this.show){
          this.buttonName = "Hide";
          this.arraw = "arrow_drop_up";
        }
        else{
          this.buttonName = "Show";
          this.arraw = "arrow_drop_down";
        }
      }


}





