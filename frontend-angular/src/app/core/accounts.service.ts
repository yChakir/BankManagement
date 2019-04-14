import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Account, AddAccount, UpdateAccount} from "./dto/Account";
import {reject} from "q";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(
    private http: HttpClient
  ) {
  }

  public findAll(): Observable<Account[]> {
    return this.http.get(`${environment.api.url}/api/v1/accounts/own`);
  }

  public findOwn(): Observable<Account[]> {
    return this.http.get(`${environment.api.url}/api/v1/accounts/own`);
  }

  public findWaitingApproval(): Observable<Account[]> {
    return this.http.get(`${environment.api.url}/api/v1/accounts/waiting-for-approval`);
  }

  public add(dto: AddAccount) {
    return this.http.post(`${environment.api.url}/api/v1/accounts`, dto);
  }

  public update(id: number, dto: UpdateAccount): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/accounts/${id}`, dto);
  }

  public approve(id: number): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/accounts/${id}/approve`, null);
  }

  public reject(id: number): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/accounts/${id}/reject`, null);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${environment.api.url}/api/v1/accounts/${id}`);
  }

}
