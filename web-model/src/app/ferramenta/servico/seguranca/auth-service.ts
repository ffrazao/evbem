import { Injectable } from "@angular/core";
import { Usuario } from '../../entidade/sistema/usuario';
import { Login } from 'src/app/login/login';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {

    private usuario = new BehaviorSubject<Usuario>(null);

    constructor() { }

    public login(login: Login): Promise<boolean> {
        return new Promise<boolean>((resolve, reject) => {
            if (login == null || login.login == null || !login.login.length) {
                reject('Login invalido!');
                return;
            }
            let usuario = new Usuario;
            usuario.login = login.login;
            this.usuario.next(usuario);
            resolve(true);
        });
    }

    public logout(): Promise<boolean> {
        return new Promise<boolean>((resolve, reject) => {
            this.usuario.next(null);
            resolve(true);
        });
    }

    public getUsuario(): Observable<Usuario> {
        return this.usuario.asObservable();
    }

    public estaAutenticado(): Promise<boolean> {
        return new Promise((resolve, reject) => {
            this.getUsuario().subscribe((r) => {
                resolve(true  || r != null);
            });
        });
    }

}