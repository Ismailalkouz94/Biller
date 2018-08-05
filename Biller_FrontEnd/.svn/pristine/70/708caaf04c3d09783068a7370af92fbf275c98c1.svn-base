import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { appConfig } from '../../app.config';
import { PageTitleService } from '../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../core/route-animation/route.animation";
import { ActivatedRoute, Router } from '@angular/router';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';


@Component({
  selector: 'ms-userRole',
  templateUrl: './userRole.component.html',
  styleUrls: ['../../../assets/scss/myStyle.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    "[@fadeInAnimation]": 'true'
  },

})

export class UserRoleComponent implements OnInit {

  public form: FormGroup;
  constructor(
    private fb: FormBuilder,
    private pageTitleService: PageTitleService,
    public http: Http,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    private router: Router
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }


  userRoleList;
  userList;
  roleGroupsList;

  showSuccess() {
    this.toastr.success('You are awesome!', 'Success!');
  }
  showError() {
    this.toastr.error('This is not good!', 'Oops!');
  }
  showWarning() {
    this.toastr.warning('You are being warned.', 'Alert!');
  }
  showInfo() {
    this.toastr.info('Just some information for you.');
  }
  ngOnInit() {
    this.form = this.fb.group({
      roleGroupId: [null, Validators.compose([Validators.required])],
      userLoginId: [null, Validators.compose([Validators.required])],
      description: [null, Validators.compose([Validators.required])]
    });
    this.getUserRoleData();
    this.getUserData();
    this.getRoleGroupsData();
  }

  onSubmit() {
    var userLoginId = this.form.value.userLoginId;
    var roleGroupId = this.form.value.roleGroupId;
    var description = this.form.value.description;

    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { description: this.form.value.description } });
    this.http.post(appConfig.apiUrl + 'userRole/add?description=' + description + "&userLoginId=" + userLoginId + "&roleGroupId=" + roleGroupId, options).subscribe(data => {
      this.showSuccess()
      this.router.navigate(['/security/userRole']);
      this.getUserRoleData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }

  delete(userRoleId) {
    console.log("Oooops!" + userRoleId);
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { userRoleId: userRoleId } });

    this.http.delete(appConfig.apiUrl + 'userRole/delete', options).subscribe(data => {
      this.showSuccess()
      this.router.navigate(['/security/userRole']);
      this.getUserRoleData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }

  getUserRoleData() {
    this.http.get(appConfig.apiUrl + 'userRole/findAll').map(res => res.json()).subscribe((userRoleList) => {
      this.userRoleList = userRoleList;
      console.log("**** " + userRoleList[0].description);
    })
  }

  getUserData() {
    this.http.get(appConfig.apiUrl + 'userLogin/findAll').map(res => res.json()).subscribe((userList) => {
      this.userList = userList;
      console.log("**** " + userList[0].userName);
    })
  }

  getRoleGroupsData() {
    this.http.get(appConfig.apiUrl + 'roleGroup/findAll').map(res => res.json()).subscribe((roleGroupsList) => {
      this.roleGroupsList = roleGroupsList;
      console.log("**** " + roleGroupsList[0].description);
    })
  }
}
