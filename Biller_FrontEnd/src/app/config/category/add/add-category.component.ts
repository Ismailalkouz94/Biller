import { Component, OnInit, ViewEncapsulation, ViewContainerRef ,ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { PageTitleService } from '../../../core/page-title/page-title.service';
import { Http, Headers, RequestOptions } from '@angular/http';


import { fadeInAnimation } from "../../../core/route-animation/route.animation";
import { appConfig } from '../../../app.config';

import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

import { ActivatedRoute, Router } from '@angular/router';

import { TreeviewItem, TreeviewConfig, DropdownTreeviewComponent, TreeviewHelper } from '../../../lib';
import {Message,MenuItem,TreeNode} from '../../../shared/guards/tree/api';
import {Tree} from '../../../shared/tree/tree';

@Component({
	selector: 'ms-add-category',
	templateUrl: './add-category.component.html',
	styleUrls: ['../../../../assets/scss/myStyle.scss']
})
export class AddCategoryComponent implements OnInit {

	public form: FormGroup;
	constructor(private fb: FormBuilder,
		 private pageTitleService: PageTitleService
		, public toastr: ToastsManager,
		vcr: ViewContainerRef,
		public http: Http,
		private http2: HttpClient,
		private router: Router,
	) {
		this.toastr.setRootViewContainerRef(vcr);
	}
	posts;
	result;
	partyList;
	parentCategoryId;
	partyId = localStorage.getItem('partyId');
	treeList: Array<any> = [];
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

	showCustom() {
		this.toastr.custom('<span style="color: red">Message in red.</span>', null, { enableHTML: true });
	}
    getFiles() {
		return this.http2.get<any>('http://91.92.136.198:8084/Biller/category/getCategoryTree?partyId='+localStorage.getItem("partyId"))
		  .toPromise()
		  .then(res => <TreeNode[]>res.data);
		}
	
		getLazyFiles() {
		return this.http2.get<any>('http://91.92.136.198:8084/Biller/category/getCategoryTree?partyId='+localStorage.getItem("partyId"))
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
	

		
		loading: boolean;
			
		
		nodeSelect(event) {
			this.msgs = [];
			this.msgs.push({severity: 'info', summary: 'Node Selected', detail: event.node.label});
		}
		
		nodeUnselect(event) {
			this.msgs = [];
			this.msgs.push({severity: 'info', summary: 'Node Unselected', detail: event.node.label});
		}
	
		nodeExpandMessage(event) {
			this.msgs = [];
			this.msgs.push({severity: 'info', summary: 'Node Expanded', detail: event.node.label});
		}
		
		nodeExpand(event) {
			if(event.node) {
				//in a real application, make a call to a remote url to load children of the current node and add the new nodes as children
				this.getLazyFiles().then(nodes => event.node.children = nodes);
			}
		}
		
		viewFile(file: TreeNode) {
			this.msgs = [];
			this.msgs.push({severity: 'info', summary: 'Node Selected with Right Click', detail: file.label});
		}
	
		unselectFile() {
			this.selectedFile = null;
		}
	
	
	
		
		private expandRecursive(node:TreeNode, isExpand:boolean){
			node.expanded = isExpand;
			if(node.children){
				node.children.forEach( childNode => {
					this.expandRecursive(childNode, isExpand);
				} );
			}
		}
	ngOnInit() {
		this.items = this.getMainCategory();
		this.form = this.fb.group({
			parentCategoryId: [null],
			description: [null, Validators.compose([Validators.required])],
			partyId: [null],
		});
		this.loading = true;
		this.getFiles().then(files => this.filesTree2 = files);
		this.getLazyFiles().then(files => this.lazyFiles = files);		
	}

	value = 11;
	items: TreeviewItem[];
	config = TreeviewConfig.create({
		hasFilter: true,
		hasCollapseExpand: true
	});
	onValueChange(value: number) {
		console.log('valueChange raised with value: ' + value);
		this.parentCategoryId = value;
	}

	onSubmit() {
		let headers = new Headers();
		headers.append("Content-Type", "application/json");

		// this.http.get('http://localhost:8088/Biller/partyGroup/findPartyGroupById?partyGroupId=0').map(res => res.json()).subscribe((posts) => {
		// 	this.posts = posts;
		// 	for (let item in posts) {
		// 		console.log("from  submit1", posts[item].partyType);
		// 	}
		// })
		//////
		var link = appConfig.apiUrl + 'category/add';
		var body = JSON.stringify(this.form.value);

		console.log("data before req " + body);
		this.http.post(link, body, { headers })
			.subscribe(data => {
				this.router.navigate(['/config/category']);
			}, error => {
				console.log("Oooops!");
			})
	}

	data;
	getMainCategory(): TreeviewItem[] {

		let headers = new Headers();
		headers.append("Content-Type", "application/json");
		var pId = localStorage.getItem('partyId');
		let options = new RequestOptions({
			headers: headers,
			params: { partyId: pId }
		});
		this.http
			.get(appConfig.apiUrl + "category/getCategoryDropDown", options)
			.map(res => res.json())
			.subscribe(data => {
				this.data = data.data;
				console.log(data);

				console.log("data before loop " + this.data);

				for (var item in this.data) {
					console.log("data from server ", this.data[item].value);
					this.treeList.push(new TreeviewItem({
						text: this.data[item].text, value: this.data[item].value, collapsed: this.data[item].collapsed, children: this.data[item].children


					}));

				}
				
				
			});
		return this.treeList;
	}


}



