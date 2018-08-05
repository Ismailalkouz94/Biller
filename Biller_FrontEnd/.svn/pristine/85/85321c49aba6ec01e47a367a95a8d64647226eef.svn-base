import { Component, OnInit, ViewEncapsulation, Input, Output, OnChanges, EventEmitter ,ViewChild} from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';

import { PageTitleService } from '../../core/page-title/page-title.service';
import {fadeInAnimation} from "../../core/route-animation/route.animation";

import { Http , Headers  } from '@angular/http';
import 'rxjs/add/operator/map';

import { HttpRequestsService } from '../../core/http-request-api/http-requests.service';
import {NodeService} from '../../shared/guards/tree/nodeservice';
import {Message,MenuItem,TreeNode} from '../../shared/guards/tree/api';
import {Tree} from '../../shared/tree/tree';
import {TreeDragDropService} from '../../shared/guards/tree/api';

@Component({
  selector: 'ms-menuRole',
  templateUrl: './menuRole.component.html',
  styleUrls: ['../../../assets/scss/myStyle.scss'],
    encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },

})

export class MenuRoleComponent implements OnInit {

  public form: FormGroup;
  constructor(private nodeService: NodeService,private fb: FormBuilder,private pageTitleService: PageTitleService, public http: Http){ }


  msgs: Message[];

  @ViewChild('expandingTree')
  expandingTree: Tree;

  filesTree4: TreeNode[];

  
  lazyFiles: TreeNode[];
  

  
  selectedFile2: TreeNode;
  

  
  selectedFiles2: TreeNode[];
  
  items: MenuItem[];
  
  loading: boolean;
      

  ngOnInit() {
      this.loading = true;
      this.nodeService.getFiles().then(files => this.filesTree4 = files);


      this.nodeService.getLazyFiles().then(files => this.lazyFiles = files);
      
      this.items = [
          {label: 'View', icon: 'fa-search', command: (event) => this.viewFile(this.selectedFile2)},
          {label: 'Unselect', icon: 'fa-close', command: (event) => this.unselectFile()}
      ];

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
          this.nodeService.getLazyFiles().then(nodes => event.node.children = nodes);
      }
  }
  
  viewFile(file: TreeNode) {
      this.msgs = [];
      this.msgs.push({severity: 'info', summary: 'Node Selected with Right Click', detail: file.label});
  }
  
  unselectFile() {
      this.selectedFile2 = null;
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
