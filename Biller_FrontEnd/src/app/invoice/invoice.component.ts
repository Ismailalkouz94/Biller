import { Component, OnInit ,Input,OnChanges } from '@angular/core';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../core/page-title/page-title.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
    selector: 'ms-invoice',
    templateUrl: './invoice.component.html',
    styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  	constructor() {}
  	ngOnInit() {} 
  url = '';
  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event) => { // called once readAsDataURL is completed
        this.url = event.target["result"];
      }
    }
  }

print(): void {
    let printContents, popupWin;
    printContents = document.getElementById('print-section').innerHTML;
    popupWin = window.open('', '_blank', 'top=0,left=0,height=100%,width=auto');
    popupWin.document.open();
    popupWin.document.write(`
      <html>
        <head>
          <style>
          .slide-down-enter,
.slide-down-leave
{
    -webkit-transition:200ms cubic-bezier(0.250, 0.250, 0.750, 0.750) all;
    -moz-transition:200ms cubic-bezier(0.250, 0.250, 0.750, 0.750) all;
    -ms-transition:200ms cubic-bezier(0.250, 0.250, 0.750, 0.750) all;
    -o-transition:200ms cubic-bezier(0.250, 0.250, 0.750, 0.750) all;
    transition:200ms cubic-bezier(0.250, 0.250, 0.750, 0.750) all;
    display:block;
    overflow:hidden;
    position:relative;
}

.items-table .row {
  border-bottom:1px solid #ddd;
  line-height:3em;
}
.items-table .row:last-child {
  border-bottom:none;
  line-height:3em;
}

.slide-down-enter.slide-down-enter-active,
.slide-down-leave {
    opacity:1;
    height:46px;
}

.slide-down-leave.slide-down-leave-active,
.slide-down-enter {
    opacity:0;
    height:0px;
}


.invoice-number-container * {
  font-weight:bold;
}

.items-table .row:nth-child(even) {
  background:#f9f9f9;
}
.items-table input {
  line-height:1.5em;
}
.actions {
  padding-top:1em;
}
input:focus {
  outline: 0;
}

.heading {
  background-color:#357EBD;
  color:#FFF;
  margin-bottom:1em;
  text-align:center;
  line-height:2.5em;
}
.branding {
  padding-bottom:2em;
  border-bottom:1px solid #ddd;
}
.logo-container {
  text-align:right;
}
.infos .right {
  text-align:right;
}
.infos .right input {
  text-align:right;
}
.infos .input-container {
  padding:3px 0;
}

.header.row {
  font-weight:bold;
  border-bottom:1px solid #ddd;
  border-top:1px solid #ddd;
}

input, textarea{
  border: 1px solid #FFF;
}

.container input:hover, .container textarea:hover,
.table-striped > tbody > tr:nth-child(2n+1) > td input:hover,
.container input:focus, .container textarea:focus,
.table-striped > tbody > tr:nth-child(2n+1) > td input:focus{
  border: 1px solid #CCC;
}

.table-striped > tbody > tr:nth-child(2n+1) > td input{
    background-color: #F9F9F9;
    border: 1px solid #F9F9F9;
}



@media print {
    .noPrint {
        display:none;
    }
}

body{
  padding:20px;
}

.infos input{
  width: 300px;
}

.align-right input{
  text-align:right;
  width: 300px;
}

div.container{
  width: 800px;
}

#imgInp{
  display: none;
}

.copy {
  font-family: "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Geneva, Verdana, sans-serif;
  width: 100%;
  margin: 40px 0 20px 0;
  font-size: 10px;
  color: rgba(0, 0, 0, 0.5);
  text-align: center;
  color: #404040;
  cursor: default;
  line-height: 1.4em;
}

.copy .love {
  display: inline-block;
  position: relative;
  color: #ce0c15;
}

.right .input-container{
    float: left;
}
.right .input-container input{
        text-align: left;
}

.items-table[_ngcontent-c6] .row[_ngcontent-c6]:nth-child(even){
    margin: 0;
}

.items-table{
      width: 100%;
}
.container{
    background: white;
        padding: 3px 37px 3px 26px;
}

.ng-valid.ng-dirty.ng-valid-parse.ng-touched{
    border: 1px solid #8c60601a;
    padding: 4px;
}

.logo-changer input[type="file"] {
    opacity: 0;
    display: block;
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
}
.logo-changer {
    background-color: #eee;
    border: 3px dotted #c8c8c8;
    display: block;
    margin-bottom: 1rem;
    padding: 1rem;
    border-radius: 8px;
    height: auto;
}
          </style>
        </head>
    <body onload="window.print();window.close()">${printContents}</body>
      </html>`
    );
    popupWin.document.close();
}
}


