import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter,ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

import { PageTitleService } from '../../core/page-title/page-title.service';
import {fadeInAnimation} from "../../core/route-animation/route.animation";
import { HttpClient } from '@angular/common/http';
import { Http , Headers  } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import { HttpRequestsService } from '../../core/http-request-api/http-requests.service';

import { TreeviewItem, TreeviewConfig, DropdownTreeviewComponent, TreeviewHelper } from '../../lib';
import {Message,MenuItem,TreeNode} from '../../shared/guards/tree/api';
import {Tree} from '../../shared/tree/tree';

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
  constructor(private fb: FormBuilder,private pageTitleService: PageTitleService,private http: HttpClient){

 }
    getFiles() {
    return this.http.get<any>('assets/showcase/data/files.json')
      .toPromise()
      .then(res => <TreeNode[]>res.data);
    }

    getLazyFiles() {
    return this.http.get<any>('assets/showcase/data/files-lazy.json')
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
        

    ngOnInit() {
        this.loading = true;

        this.getFiles().then(files => this.filesTree2 = files);
	console.log("admin ");
        

        
        this.getLazyFiles().then(files => this.lazyFiles = files);
 
        
    }
    
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
}
