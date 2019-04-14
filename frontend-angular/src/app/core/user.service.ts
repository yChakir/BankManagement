import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {JwtService} from "./jwt.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private isAuthenticated: Subject<boolean> = new Subject<boolean>();

  private currentAuthenticationState: boolean = false;

  constructor(
    private http: HttpClient,
    private jwtService: JwtService
  ) {
    const token = localStorage.getItem("token");

    if (token) {
      this.startSession(token);
    }
  }

  public login(credentials: Credentials): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.http.post(`${environment.api.url}/login`, credentials, {
        observe: 'response'
      }).subscribe(response => {
        const header = response.headers.get(environment.jwt.header);
        const token = this.jwtService.getJwtFromHeader(header);
        this.startSession(token, true);
        resolve();
      }, response => {
        reject(response);
      });
    })
  }

  public logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("expires_at");
    this.isAuthenticated.next(this.currentAuthenticationState = false);
  }

  public startSession(token: string, store?: boolean) {
    const expire_at = this.jwtService.decodeAndGet(token, 'exp');

    if (store) {
      localStorage.setItem('token', token);
      localStorage.setItem('expires_at', expire_at.toString());
    }

    this.isAuthenticated.next(this.currentAuthenticationState = true);
  }

  public register(registration: Registration): Observable<any> {
    return this.http.post(`${environment.api.url}/api/v1/users/register`, registration);
  }

  public validateEmail(validateEmail: ValidateEmail): Observable<any> {
    return this.http.post(`${environment.api.url}/api/v1/users/validate-email`, validateEmail);
  }

  public forgotPassword(forgotPassword: ForgotPassword): Observable<any> {
    return this.http.post(`${environment.api.url}/api/v1/users/forgot-password`, forgotPassword);
  }

  public resetPassword(resetPassword: ResetPassword): Observable<any> {
    return this.http.post(`${environment.api.url}/api/v1/users/reset-password`, resetPassword);
  }

  public getIsAuthenticated(): Observable<boolean> {
    return this.isAuthenticated.asObservable();
  }

  public getCurrentAuthenticationState(): boolean {
    return this.currentAuthenticationState;
  }

}
