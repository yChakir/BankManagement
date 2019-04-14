import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {AccountType, AddAccountType, UpdateAccountType} from "./dto/AccountType";

@Injectable({
  providedIn: 'root'
})
export class AccountTypeService {

  constructor(
    private http: HttpClient
  ) {
  }

  public findAll(): Observable<AccountType[]> {
    return this.http.get<AccountType[]>(`${environment.api.url}/api/v1/account-type`);
  }

  public add(dto: AddAccountType) {
  return this.http.post(`${environment.api.url}/api/v1/account-type`, dto);
  }

  public update(id: number, dto: UpdateAccountType) {
    return this.http.put(`${environment.api.url}/api/v1/account-type/${id}`, dto);
  }

  public delete(id: number) {
    return this.http.delete(`${environment.api.url}/api/v1/account-type/${id}`);
  }

}
