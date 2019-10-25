import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

import { LoginService } from './login.service';
import { Login } from './login';
import { Router } from '@angular/router';
import { ToastController, LoadingController } from '@ionic/angular';

@Component({
  templateUrl: 'login.page.html',
  styleUrls: ['login.page.scss']
})
export class LoginPage implements OnInit {

  private form: FormGroup;

  @ViewChild('focaliza', { static: false })
  private focaliza;

  private passwordType = 'password';

  private passwordIcon = 'eye-off';

  constructor(
    private router: Router,
    private service: LoginService,
    private formBuilder: FormBuilder,
    private toastCtrl: ToastController,
    public loadingCtrl: LoadingController,
  ) {
  }

  ionViewDidEnter() {
    setTimeout(() => {
      this.focaliza.setFocus();
    }, 500);
  }

  ngOnInit() {
    this.form = this.createForm(new Login());
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
    this.exibirCarregando().then((res) => {
      res.present();
      res.onDidDismiss().then((dis) => {
      });
      this.service.login(this.form.value as Login).subscribe((r) => {
        this.router.navigate(['/', 'm', 'home']);
        this.sucesso();
        res.dismiss();
      }, (e) => {
        console.log(e);
        this.falha('Erro no servidor de autenticação!');
        res.dismiss();
      });
    });
    
  }

  private async sucesso() {
    const toast = await this.toastCtrl.create({
      message: 'Login efetuado!',
      duration: 2000,
      color: 'success',
    });
    toast.present();
  }

  private async falha(erro) {
    const toast = await this.toastCtrl.create({
      header: 'Erro ao efetuar Login!',
      message: erro,
      color: 'danger',
      buttons: [
        {
          side: 'start',
          text: 'Ok',
        }
      ]
    });
    toast.present();
  }

  private async exibirCarregando() {
    return await this.loadingCtrl.create({
      message: 'Aguarde...'
    });
  }

}
