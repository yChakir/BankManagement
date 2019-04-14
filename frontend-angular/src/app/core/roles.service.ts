import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {AddRole, Role, UpdateRole} from "./dto/Role";

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(
    private http: HttpClient
  ) {
  }

  public findAll(): Observable<Role[]> {
    return this.http.get(`${environment.api.url}/api/v1/roles`);
  }

  public add(role: AddRole) {
    return this.http.post(`${environment.api.url}/api/v1/roles`, role);
  }

  public update(id: number, role: UpdateRole): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/roles/${id}`, role);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${environment.api.url}/api/v1/roles/${id}`);
  }
}
