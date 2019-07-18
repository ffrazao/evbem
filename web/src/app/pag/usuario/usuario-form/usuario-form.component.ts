import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Route } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';

import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';
import { CrudConfig } from 'src/app/comum/componente/crud-config';
import { UsuarioService } from '../servico/usuario.service';
import { MatDialog } from '@angular/material';
import { DialogoComponent } from 'src/app/comum/componente/dialogo/dialogo.component';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent extends CrudFormComponent implements OnInit {

  constructor(
    private _router: Router,
    private _actr: ActivatedRoute,
    private _formBuilder: FormBuilder,
    private _service: UsuarioService,
    public dialog: MatDialog,
  ) {
    super();
  }

  ngOnInit() {
    this._actr.data.subscribe((data: any) => {
      // carregar as configurações
      if (data.config) {
        this.config = data.config; //Object.assign({}, data.config);
        delete data['config'];
      } else if (!this.config) {
        this.config = new CrudConfig(['/pag', 'usuario']);
      }
      this.config.novaPos = 1;
      if (!isNaN(this.config.pos)) {
        this.config.novaPos = this.config.pos + 1;
      } else {
        this.config.pos = 0;
      }
      if (!this.config.formulario) {
        // construir o formulário
        this.config.formulario = this._formBuilder.group({
          id: [null, []],
          nome: [null, [Validators.required, Validators.maxLength(255)]],
          login: [null, [Validators.required, Validators.maxLength(255)]],
          tipo: [null, [Validators.required, Validators.maxLength(255)]],
          email: [null, [Validators.required, Validators.maxLength(255), Validators.email]],
          ativo: [null, [Validators.required]],
        });
      }

      // carregar os dados do formulário
      this.config.formulario.reset();
      if (data.formulario) {
        this.config.formulario.patchValue(data.formulario);
      }
    });
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')['_loadedConfig'].routes.find(v => v.path == 'usuario');
  }

  voltar() {
    if (!this.pendencia()) {
      this.getRoute().data.config = this.config; //Object.assign({}, this.config);
      this._router.navigate(this.config.urlPrincipal);
    }
  }

  salvar() {
    this._service.salvar(this.config.formulario.value).subscribe(f => this.config.formulario.patchValue(f));
  }

  vaiParaPrimeiro() {
    if (!this.pendencia()) {
      this._router.navigate(this.config.vaiParaPrimeiro());
    }
  }

  vaiParaAnterior() {
    if (!this.pendencia()) {
      this._router.navigate(this.config.vaiParaAnterior());
    }
  }

  vaiPara(_pos: number) {
    if (!this.pendencia()) {
      this._router.navigate(this.config.vaiPara(_pos));
    }
  }

  vaiParaProximo() {
    if (!this.pendencia()) {
      this._router.navigate(this.config.vaiParaProximo());
    }
  }

  vaiParaUltimo() {
    if (!this.pendencia()) {
      this._router.navigate(this.config.vaiParaUltimo());
    }
  }

  async pendencia() {
    console.log(1);
    if (!this.config.formulario.pristine) {
      console.log(2);
      let result = false;
      let r2 = this.openDialog('Descartar as alterações');
      console.log(5, r2);
    }
    return false;
  }

  openDialog(_mensagem: string) {
    const dialogRef = this.dialog.open(DialogoComponent, {
      width: '350px',
      data: _mensagem
    });
    return dialogRef.afterClosed().toPromise();
  }
}