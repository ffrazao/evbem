import { Component } from "@angular/core";
import { CrudComponent } from './crud-component';
import { DialogoService } from './dialogo/dialogo.service';
import { AppInjector } from 'src/app/app.module';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({})
export abstract class CrudFormComponent extends CrudComponent {

  protected _dialogo;
  protected _router;
  protected _formBuilder;
  protected _toastr;

  constructor(
  ) {
    super();
    this._dialogo = AppInjector.get(DialogoService);
    this._router = AppInjector.get(Router);
    this._formBuilder = AppInjector.get(FormBuilder);
    this._toastr = AppInjector.get(ToastrService);
  }

  async semPendencia() {
    let resposta = true;
    if (!this.config.formulario.pristine) {
      resposta = await this._dialogo.confirme('Há dados modificados que ainda não foram salvos no sistema. Deseja descartá-los?');
    }
    return resposta;
  }

  async voltar() {
    if (await this.semPendencia()) {
      this.config.permitirEdicao = false;
      this.getRoute().data.config = this.config; //Object.assign({}, this.config);
      this._router.navigate(this.config.urlPrincipal);
    }
  }

  async vaiParaPrimeiro() {
    if (await this.semPendencia()) {
      this.config.permitirEdicao = false;
      this._router.navigate(this.config.vaiParaPrimeiro());
    }
  }

  async vaiParaAnterior() {
    if (await this.semPendencia()) {
      this.config.permitirEdicao = false;
      this._router.navigate(this.config.vaiParaAnterior());
    }
  }

  async vaiPara(_pos: number) {
    if (await this.semPendencia()) {
      this.config.permitirEdicao = false;
      this._router.navigate(this.config.vaiPara(_pos));
    }
  }

  async vaiParaProximo() {
    if (await this.semPendencia()) {
      this.config.permitirEdicao = false;
      this._router.navigate(this.config.vaiParaProximo());
    }
  }

  async vaiParaUltimo() {
    if (await this.semPendencia()) {
      this.config.permitirEdicao = false;
      this._router.navigate(this.config.vaiParaUltimo());
    }
  }

}