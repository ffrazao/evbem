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
    protected _config: CrudConfig,
    private _actr: ActivatedRoute,
    private _router: Router,
    private _formBuilder: FormBuilder,
    private _service: UsuarioService,
  ) {
    super(_config, ['/pag', 'usuario']);
  }

  ngOnInit() {

    // construir o formulário
    this.formulario = this._formBuilder.group({
      id: [null, []],
      nome: [null, [Validators.required, Validators.maxLength(255)]],
      login: [null, [Validators.required, Validators.maxLength(255)]],
      tipo: [null, [Validators.required, Validators.maxLength(255)]],
      email: [null, [Validators.required, Validators.maxLength(255), Validators.email]],
      ativo: [null, [Validators.required]],
    });

    // carregar os dados do formulário
    this._actr.data.subscribe((data: any) => {
      console.log(12);
      this.formulario.reset();
      if (data.formulario) {
        this.formulario.patchValue(data.formulario);
      }
      if (data.config) {
        //this.config.ids = data.config.lista.idList;
        this.config = data.config;
        delete data['config'];
      }
      this.config.novaPos = 1;
      if (!isNaN(this.config.pos)) {
        this.config.novaPos = this.config.pos + 1;
      } else {
        this.config.pos = 0;
      }
    });
  }

  public getRoute(): Route {
    return this._router.config.find(v=>v.path == 'pag')['_loadedConfig'].routes.find(v=>v.path == 'usuario');
  }

  voltar() {
    let url : string[] = this._urlPrincipal.slice(0);

    this.getRoute().data.config = {
      paginaAtual: this.config.paginaAtual, 
      tamanhoPagina: this.config.tamanhoPagina, 
      filtro: {}, 
      lista: {ids: this.config.ids, pos: this.config.pos},
    };
    this._router.navigate(url);
  }

  salvar() {
    this._service.salvar(this.formulario.value).subscribe(f => this.formulario.patchValue(f));
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