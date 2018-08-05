import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { HttpRequestsService } from '../../../core/http-request-api/http-requests.service';
import { appConfig } from '../../../app.config';
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { Router } from '@angular/router';

@Component({
  selector: 'ms-roleGroup',
  templateUrl: './roleGroup.component.html',
  styleUrls: ['../../../../assets/scss/myStyle.scss']


})

export class RoleGroupComponent implements OnInit {

  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    private pageTitleService: PageTitleService,
    public http: Http,
    private router: Router
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  roleGroupsList;

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

  ngOnInit() {
       this.form = this.fb.group({
      roleGroupId: [null],
      description: [null],

    });
    this.getRoleGroupsData();
    console.log("9999999999999999999999999999999999");
  }

  getRoleGroupsData() {
    this.http.get(appConfig.apiUrl + 'roleGroup/findAll').map(res => res.json()).subscribe((roleGroupsList) => {
      this.roleGroupsList = roleGroupsList;
      console.log("this.roleGroupsList >> " + this.roleGroupsList[0].description);
    })
  }

  delete(roleGroupId) {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { roleGroupId: roleGroupId } });

    this.http.delete(appConfig.apiUrl + 'roleGroup/delete', options).subscribe(data => {
      this.showSuccess()
      this.router.navigate(['/roleGroup/delete']);
      this.getRoleGroupsData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }

  onSubmit() {
    console.log(this.form.value);
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: this.form.value });

    this.http.get(appConfig.apiUrl + 'roleGroup/findByCriteria', options).map(res => res.json()).subscribe((roleGroupsList) => {
      this.roleGroupsList = roleGroupsList;
      console.log("from  submit1" + roleGroupsList);
      for (let item in roleGroupsList) {
        console.log("from  submit1" + item);
      }
    })

  }

  addMenuRole(roleGroupId){
    localStorage.setItem("roleGroupId",roleGroupId);
    this.router.navigate(['/security/menuRole']);

  }
}
