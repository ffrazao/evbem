import { SafeUrl } from '@angular/platform-browser';

export class UsuarioLocal {
    public username: string;
    // tslint:disable-next-line:variable-name
    public access_token: string;
    public id: number;
    public nome: string;
    public login: string;
    public email: string;
    public foto: [];
    public fotoSafe?: SafeUrl;
}
