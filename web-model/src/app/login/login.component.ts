import { OnInit, Component } from '@angular/core';
import { Login } from './login';
import { AuthService } from '../ferramenta/seguranca/seguranca/auth-service';
import { Router } from '@angular/router';

@Component({
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.sass']
})
export class LoginComponent implements OnInit {

    private login: Login = new Login;

    constructor(
        private servico: AuthService,
        private router: Router,
    ) {
    }

    ngOnInit() {
    }

    public logar() {
        this.servico.login(this.login).then((r) => {
            if (r) {
                this.router.navigate(['/'], {});
            } else {
                alert('Login inválido!');
            }
        }).catch(e => alert(e));
    }

}