import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router } from '@angular/router';
import { LoginService } from 'src/app/login/login.service';
import { ToastController } from '@ionic/angular';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

    constructor(
        private service: LoginService,
        private router: Router,
        private toastCtrl: ToastController,
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        if (!this.service.temToken) {
            this.efetueLogin();
            console.log('Redirecionando', state.url);
            this.router.navigate(['/', 'login'], { queryParams: { urlRetorno: state.url }});
            return false;
        }
        return true;
    }

    private async efetueLogin() {
        const toast = await this.toastCtrl.create({
            message: 'Para acessar este recurso, primeiro efetue o seu login!',
            duration: 4000,
        });
        toast.present();
    }

}
