import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { MensagemService } from '../../servico/mensagem/mensagem.service';
import { LoginService } from './login.service';
import { Login } from './login';

@Component({
  templateUrl: 'login.page.html',
  styleUrls: ['login.page.scss']
})
export class LoginPage implements OnInit {

  private form: FormGroup;

  @ViewChild('focaliza', { static: false })
  private focaliza;

  private urlRetorno = '/';

  private passwordType = 'password';

  private passwordIcon = 'eye-off';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private service: LoginService,
    private formBuilder: FormBuilder,
    private mensagem: MensagemService,
  ) {
  }


  ngOnInit() {
    this.form = this.createForm(new Login());
    this.urlRetorno = this.route.snapshot.queryParams.urlRetorno || this.urlRetorno;
  }

  ionViewDidEnter() {
    setTimeout(() => {
      this.focaliza.setFocus();
    }, 500);
  }

  private createForm(login: Login): FormGroup {
    return this.formBuilder.group({
      usuario: [login.usuario, Validators.required],
      senha: [login.senha, Validators.required],
    });
  }

  public hideShowPassword() {
    this.passwordType = this.passwordType === 'text' ? 'password' : 'text';
    this.passwordIcon = this.passwordIcon === 'eye-off' ? 'eye' : 'eye-off';
  }

  public async onSubmit() {
    if (!this.form.valid) {
      return;
    }
    this.mensagem.aguarde().then((res) => {
      res.present();
      this.service.login(this.form.value as Login).subscribe((r) => {
        this.router.navigateByUrl(this.urlRetorno);
        this.mensagem.sucesso('Login efetuado!');
        res.dismiss();
      }, (e) => {
        console.log(e);
        this.mensagem.erro('Erro no servidor de autenticação!');
        res.dismiss();
      });
    });
  }

}
