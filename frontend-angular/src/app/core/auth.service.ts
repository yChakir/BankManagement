import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isAuthenticated: Subject<boolean> = new Subject<boolean>();

  constructor() {
  }

  public login() {
    this.isAuthenticated.next(true);
  }

  public logout() {
    this.isAuthenticated.next(false);
  }

  public getIsAuthenticated(): Observable<boolean> {
    return this.isAuthenticated.asObservable();
  }
}
