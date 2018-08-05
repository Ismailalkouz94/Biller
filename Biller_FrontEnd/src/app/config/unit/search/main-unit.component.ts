import {
  Component,
  OnInit,
  ViewEncapsulation,
  Input,
  ViewContainerRef
} from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { CustomValidators } from "ng2-validation";
import { TreeviewItem, TreeviewConfig } from '../../../lib';
import { Http, Headers, RequestOptions } from '@angular/http';
import { HttpParams, HttpClient } from '@angular/common/http';
import { appConfig } from '../../../app.config';
import { Router } from '@angular/router';
import { MdDialog, MdDialogRef } from '@angular/material';
import { ConfirmationDialog } from '../../../confirm-dialog/confirmation-dialog';
import { ToastsManager } from "ng2-toastr/ng2-toastr";

@Component({
  selector: "ms-main-unit",
  templateUrl: "./main-unit.component.html",
  styleUrls: ["../../../../assets/scss/myStyle.scss"]
})
export class MainUnitComponent implements OnInit {

  public form: FormGroup;
  dialogRef: MdDialogRef<ConfirmationDialog>;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    public http: Http,
    private router: Router,
    public dialog: MdDialog
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }
  unitsList;
  unitsDate;
  partyList;

  unitId: "";
  name: "";
  partyId: "";

  params;
  showSuccess() {
    this.toastr.success("You are awesome!", "Success!");
  }
  showError() {
    this.toastr.error("This is not good!", "Oops!");
  }
  showWarning() {
    this.toastr.warning("You are being warned.", "Alert!");
  }
  showInfo() {
    this.toastr.info("Just some information for you.");
  }

  showCustom() {
    this.toastr.custom(
      '<span style="color: red">Message in red.</span>',
      null,
      { enableHTML: true }
    );
  }
  ngOnInit() {
    this.form = this.fb.group({
      unitId: [],
      name: [],
      partyId: [],
    });

    this.getUnitsData();
  }

  getUnitsData() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { partyId: sessionStorage.getItem("partyId") } });
    this.http.get(appConfig.apiUrl + 'units/findAll', options).map(res => res.json()).subscribe((data) => {
      this.unitsList = data;
    })

  }



  update(unitId) {
    sessionStorage.setItem("unit", JSON.stringify(unitId));
    this.router.navigate(['/config/update-unit'], { queryParams: { id: unitId } });
  }

  openConfirmationDialog(id) {
    this.dialogRef = this.dialog.open(ConfirmationDialog, {
      disableClose: false
    });
    this.dialogRef.componentInstance.confirmMessage = "Are you sure you want to delete?"

    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.delete(id);
      }
      this.dialogRef = null;
    });
  }
  delete(unitId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { id: unitId } });
    this.http.delete(appConfig.apiUrl + 'units/delete', options).subscribe(data => {
      this.showSuccess()
      this.getUnitsData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });

  }



  findByCriteria(formData) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    this.params = {
      partyId: sessionStorage.getItem('partyId'),
      unitId: formData.unitId,
      name: formData.name,
    };
    for (var key in this.params) {
      if (this.params[key] === null) {
        delete this.params[key];
      }
    }
    let options = new RequestOptions({
      headers: headers,
      params: this.params
    });

    this.http.get(appConfig.apiUrl + "units/findByCriteria", options).map(res => res.json())
      .subscribe(unitsList => {
        this.unitsList = unitsList;
      });
  }

}
