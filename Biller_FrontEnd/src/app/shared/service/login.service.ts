import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class UsersService {
  private user = new BehaviorSubject<Array<any>>([]);
  cast = this.user.asObservable();

  constructor() { }

  setMenu(menuList){
    this.user.next(menuList);
  }

}