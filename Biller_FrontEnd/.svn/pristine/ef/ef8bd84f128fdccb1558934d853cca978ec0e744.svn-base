import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter, ViewChild, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { PageTitleService } from '../../core/page-title/page-title.service';
import { fadeInAnimation } from "../../core/route-animation/route.animation";
import { HttpClient } from '@angular/common/http';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { HttpRequestsService } from '../../core/http-request-api/http-requests.service';
import { TreeviewItem, TreeviewConfig, DropdownTreeviewComponent, TreeviewHelper } from '../../lib';
import { appConfig } from '../../app.config';
import { Message, MenuItem, TreeNode } from '../../shared/guards/tree/api';
import { Tree } from '../../shared/tree/tree';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'ms-menus',
    templateUrl: './menus.component.html',
    styleUrls: ['../../../assets/scss/myStyle.scss'],
    encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    }
})

export class MenusComponent implements OnInit {

    public form: FormGroup;
    constructor(
        private fb: FormBuilder,
        private pageTitleService: PageTitleService,
        private https: HttpClient,
        private http: Http,
        public toastr: ToastsManager,
        vcr: ViewContainerRef,
        private router: Router
    ) {
        this.toastr.setRootViewContainerRef(vcr);
    }
    getFiles() {
        return this.https.get<any>('assets/showcase/data/files.json')
            .toPromise()
            .then(res => <TreeNode[]>res.data);
    }

    getLazyFiles() {
        return this.https.get<any>('assets/showcase/data/files-lazy.json')
            .toPromise()
            .then(res => <TreeNode[]>res.data);
    }

    msgs: Message[];

    @ViewChild('expandingTree')
    expandingTree: Tree;

    filesTree2: TreeNode[];
    lazyFiles: TreeNode[];
    selectedFile: TreeNode;
    selectedFiles: TreeNode[];


    items: MenuItem[];
    loading: boolean;
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
            description: [null, Validators.compose([Validators.required])],
            parentMenusId: [null],
            state: [null, Validators.compose([Validators.required])],
            type: [null, Validators.compose([Validators.required])],
            icon: [null]
        });

        this.loading = true;

        this.getFiles().then(files => this.filesTree2 = files);

        console.log("TreeNode", this.getFiles().then(files => this.filesTree2 = files));

        this.getLazyFiles().then(files => this.lazyFiles = files);


    }

    nodeSelect(event) {
        this.msgs = [];
        this.msgs.push({ severity: 'info', summary: 'Node Selected', detail: event.node.label });
    }

    nodeUnselect(event) {
        this.msgs = [];
        this.msgs.push({ severity: 'info', summary: 'Node Unselected', detail: event.node.label });
    }

    nodeExpandMessage(event) {
        this.msgs = [];
        this.msgs.push({ severity: 'info', summary: 'Node Expanded', detail: event.node.label });
    }

    nodeExpand(event) {
        if (event.node) {
            //in a real application, make a call to a remote url to load children of the current node and add the new nodes as children
            this.getLazyFiles().then(nodes => event.node.children = nodes);
        }
    }

    viewFile(file: TreeNode) {
        this.msgs = [];
        this.msgs.push({ severity: 'info', summary: 'Node Selected with Right Click', detail: file.label });
    }

    unselectFile() {
        this.selectedFile = null;
    }




    private expandRecursive(node: TreeNode, isExpand: boolean) {
        node.expanded = isExpand;
        if (node.children) {
            node.children.forEach(childNode => {
                this.expandRecursive(childNode, isExpand);
            });
        }
    }
    onSubmit() {
        console.log("**description** " + this.form.value.description);
        console.log("**parentMenusId** " + this.form.value.parentMenusId);
        console.log("**state** " + this.form.value.state);
        console.log("**state** " + this.form.value.type);
        console.log("**state** " + this.form.value.icon);

        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        var link = appConfig.apiUrl + 'menus/add';
        var body = JSON.stringify(this.form.value);
        console.log("data before req " + body);

        this.http.post(link, body, { headers })
            .subscribe(data => {
                this.showSuccess();
                this.router.navigate(['/security/menus']);
            }, error => {
                this.showError();
                console.log("Oooops!");
            })
    }

}
