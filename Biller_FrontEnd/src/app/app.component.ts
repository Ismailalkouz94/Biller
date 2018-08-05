import {Component, Optional, ViewEncapsulation} from '@angular/core';
import {TranslateService} from 'ng2-translate/ng2-translate';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';


@Component({
  	selector: 'silk-app',
  	template:'<router-outlet></router-outlet>',
    encapsulation: ViewEncapsulation.None
})
export class SilkAppComponent {
   constructor(translate: TranslateService) {
       translate.addLangs(['en', 'ar']);
       translate.setDefaultLang('ar');

       const browserLang: string = translate.getBrowserLang();
       translate.use(browserLang.match(/en|ar/) ? browserLang : 'ar');
     }
}
