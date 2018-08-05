import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { appConfig } from '../../../app.config';
import { HttpRequestsService } from '../../../core/http-request-api/http-requests.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ValidationService } from '../../../Users/core/services/validation.service';

@Component({
  selector: 'ms-add-menuRole',
  templateUrl: './add-menuRole.component.html',
  styleUrls: ['../../../../assets/scss/myStyle.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    "[@fadeInAnimation]": 'true'
  },

})

export class AddMenuRoleComponent implements OnInit {

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

  menuRolesList;
  roleGroupsList;
  menusList;

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
      menusId: [null, Validators.compose([Validators.required])],
      // description: [null, Validators.compose([Validators.required])]
    });
    this.getMenuRolesData();
    this.getRoleGroupsData()
    this.getmenusData();
  }

  delete(menuRoleId) {
    console.log("Oooops!" + menuRoleId);
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { menuRoleId: menuRoleId } });

    this.http.delete(appConfig.apiUrl + 'menuRole/delete', options).subscribe(data => {
      this.showSuccess()
      this.router.navigate(['/security/menuRole']);
      this.getMenuRolesData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }
  onSubmit() {
    var menusId = this.form.value.menusId;
    var roleGroupId = this.form.value.roleGroupId;
    var description = this.form.value.description;

    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { description: this.form.value.description } });
    this.http.post(appConfig.apiUrl + 'menuRole/add?description=' + description + "&menusId=" + menusId + "&roleGroupId=" + roleGroupId, options).subscribe(data => {
      this.showSuccess()
      this.router.navigate(['/security/menuRole']);
      this.getMenuRolesData();
    }, error => {
      this.showError()
      console.log("Oooops!");
    });
  }
  getMenuRolesData() {
    this.http.get(appConfig.apiUrl + 'menuRole/findAll').map(res => res.json()).subscribe((menuRolesList) => {
      this.menuRolesList = menuRolesList;
    })
  }
  getRoleGroupsData() {
    this.http.get(appConfig.apiUrl + 'roleGroup/findAll').map(res => res.json()).subscribe((roleGroupsList) => {
      this.roleGroupsList = roleGroupsList;
      console.log("**** " + roleGroupsList[0].description);
    })
  }
  getmenusData() {
    this.http.get(appConfig.apiUrl + 'menus/findAll').map(res => res.json()).subscribe((menusList) => {
      this.menusList = menusList;
      console.log("**** " + menusList[0].description);
    })
  }
}
