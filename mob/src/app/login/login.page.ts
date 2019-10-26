import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

import { LoginService } from './login.service';
import { Login } from './login';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastController, LoadingController, AlertController } from '@ionic/angular';

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
    private toastCtrl: ToastController,
    private alertCtrl: AlertController,
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
    this.urlRetorno = this.route.snapshot.queryParams.urlRetorno || this.urlRetorno;
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
        this.router.navigateByUrl(this.urlRetorno);
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
    const result = await this.toastCtrl.create({
      message: 'Login efetuado!',
      duration: 2000,
      color: 'success',
    });
    result.present();
  }

  private async falha(erro) {
    const result = await this.alertCtrl.create({
      header: 'Erro ao efetuar Login!',
      message: erro,
      cssClass: 'danger',
      buttons: [
        {
          text: 'Ok',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          }
        }
      ]
    });
    result.present();
  }

  private async exibirCarregando() {
    return await this.loadingCtrl.create({
      message: 'Aguarde...'
    });
  }

}
