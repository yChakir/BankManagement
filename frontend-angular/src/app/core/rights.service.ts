import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RightsService {

  constructor(
    private http: HttpClient
  ) { }

  public findAll(): Observable<Right[]> {
    return this.http.get<Right[]>(`${environment.api.url}/api/v1/rights`);
  }

  public activate(name: string): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/rights/${name}/activate`, null);
  }

  public deactivate(name: String): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/rights/${name}/deactivate`, null);
  }

  public update(name, description): Observable<any> {
    return this.http.put(`${environment.api.url}/api/v1/rights/${name}`, {description: description});
  }
}
