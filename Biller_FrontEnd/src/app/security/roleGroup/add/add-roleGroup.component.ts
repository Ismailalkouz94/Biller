import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { appConfig } from '../../../app.config';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { HttpRequestsService } from '../../../core/http-request-api/http-requests.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Component({
  selector: 'ms-add-roleGroup',
  templateUrl: './add-roleGroup.component.html',
  styleUrls: ['../../../../assets/scss/myStyle.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    "[@fadeInAnimation]": 'true'
  },

})

export class AddRoleGroupComponent implements OnInit {

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
      // roleGroupId: [null],
      description: [null, Validators.compose([Validators.required])]
    });
  }
  onSubmit() {
    let headers = new Headers();
    headers.append("Content-Type", "application/json");
    let options = new RequestOptions({ headers: headers, params: { description: this.form.value.description } });
    this.http.post(appConfig.apiUrl + 'roleGroup/add?description=' + this.form.value.description, options).subscribe(data => {
      this.showSuccess()
      this.router.navigate(['/security/roleGroup']);
    }, error => {
      this.showError()
      console.log("Oooops!");
    });


  }

}
