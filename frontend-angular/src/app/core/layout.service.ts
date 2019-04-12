import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LayoutService {

  private current: boolean = false;

  private isCollapsed: Subject<boolean> = new Subject<boolean>();

  constructor() { }

  toggle() {
    this.isCollapsed.next(this.current = !this.current);
  }

  getIsCollapsed(): Observable<boolean> {
    return this.isCollapsed.asObservable();
  }
}
