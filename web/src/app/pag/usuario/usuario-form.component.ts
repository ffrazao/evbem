import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Route } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';

import { UsuarioService } from './usuario.service';
import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';
import { CrudConfig } from 'src/app/comum/componente/crud-config';

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
  ) {
    super();
  }

  ngOnInit() {

    this._actr.data.subscribe((data: any) => {
      // carregar as configurações
      if (data.config) {
        // console.log('Recuperar config form');
        this.config = data.config;
        delete this._actr.snapshot.data['config'];
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
    return this._router.config.find(v=>v.path == 'pag')['_loadedConfig'].routes.find(v=>v.path == 'usuario');
  }

  voltar() {
    this.getRoute().data.config = this.config;
    this._router.navigate(this.config.urlPrincipal);
  }

  salvar() {
    this._service.salvar(this.config.formulario.value).subscribe(f => this.config.formulario.patchValue(f));
  }

  vaiParaPrimeiro() {
    this._router.navigate(this.config.vaiParaPrimeiro());
  }

  vaiParaAnterior() {
    this._router.navigate(this.config.vaiParaAnterior());
  }

  vaiPara(_pos: number) {
    this._router.navigate(this.config.vaiPara(_pos));
  }

  vaiParaProximo() {
    this._router.navigate(this.config.vaiParaProximo());
  }

  vaiParaUltimo() {
    this._router.navigate(this.config.vaiParaUltimo());
  }

}