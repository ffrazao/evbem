import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/login/login.service';
import { environment } from 'src/environments/environment';
import { iniciaCom } from '../../ferramenta/funcao';

@Injectable()
export class AutInterceptor implements HttpInterceptor {

    constructor(private service: LoginService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (iniciaCom(req.url, environment.autorizadorUrl) || iniciaCom(req.url, environment.apiUrl)) {
            const token = this.service.token;
            if (token && token.access_token && token.access_token.length) {
                const autReq = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token.access_token}`) });
                return next.handle(autReq);
            }
        }

        return next.handle(req);
    }
}

export const AutInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: AutInterceptor,
    multi: true,
};
