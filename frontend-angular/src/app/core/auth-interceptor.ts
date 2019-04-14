import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const idToken = localStorage.getItem('token');

    if (idToken) {
      const cloned = req.clone({
        headers: req.headers.set(environment.jwt.header, `${environment.jwt.bearer + idToken}`)
      });

      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }
}
