import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Validators } from '@angular/forms';

import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';
import { CrudConfig } from 'src/app/comum/componente/crud-config';
import { UsuarioService } from '../servico/usuario.service';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent extends CrudFormComponent implements OnInit {

  constructor(
    private _actr: ActivatedRoute,
    private _service: UsuarioService,
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
        this.config.formulario = this.criarFormulario();
        this.config.formularioOriginal = this.criarFormulario();
      }

      // carregar os dados do formulário
      this.config.formularioOriginal.reset();
      this.config.formulario.reset();
      if (data.formulario && data.formulario[0]) {
        this.config.formularioOriginal.patchValue(data.formulario[0]);
        this.config.formulario.patchValue(data.formulario[0]);
      }
    });
  }

  criarFormulario() {
    return this._formBuilder.group({
      id: [null, []],
      nome: [null, [Validators.required, Validators.maxLength(255)]],
      login: [null, [Validators.required, Validators.maxLength(255)]],
      tipo: [null, [Validators.required, Validators.maxLength(255)]],
      email: [null, [Validators.required, Validators.maxLength(255), Validators.email]],
      ativo: [null, [Validators.required]],
    });
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')['_loadedConfig'].routes.find(v => v.path == 'usuario');
  }

  salvar() {
    this._service.salvar(this.config.formulario.value).subscribe(f => {
      this.config.formularioOriginal.patchValue(f);
      this.config.formulario.patchValue(f);
      this.config.permitirEdicao = false;
      this._toastr.success('Executado com sucesso!', 'Salvar');
    }, e => {
      console.log(e);
      this._toastr.error('Erro no processamento!', 'Salvar');
    });
  }

  incluir() {
    this._service.criar().subscribe(f => {
      this.config.formularioOriginal.patchValue(f);
      this.config.formulario.patchValue(f);
      this.config.permitirEdicao = true;
    }, e => {
      console.log(e);
      this._toastr.error('Erro no processamento!', 'Incluir');
    });
  }

  excluir() {
    this._service.excluir(this.config.formulario.value.id).subscribe(f => {
      this.config.permitirEdicao = false;
      this.config.formulario.reset(this.config.formularioOriginal.value);
      this.voltar();
      this._toastr.success('Executado com sucesso!', 'Excluir');
    }, e => {
      console.log(e);
      this._toastr.error('Erro no processamento!', 'Excluir');
    });
  }

  async editar() {
    if (this.config.permitirEdicao) {
      if (await this.semPendencia()) {
        this.config.permitirEdicao = false;
        this.config.formulario.reset(this.config.formularioOriginal.value);
      } else {
        this.config.permitirEdicao = true;
      }
    } else {
      this.config.permitirEdicao = !this.config.permitirEdicao;
    }
  }

}