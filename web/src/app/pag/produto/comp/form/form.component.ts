import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Validators } from '@angular/forms';

import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';
import { CrudConfig } from 'src/app/comum/componente/crud-config';
import { ProdutoService } from '../../servico/produto.service';

@Component({
  selector: 'app-produto-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent extends CrudFormComponent implements OnInit {

  modeloList = [{ id: 1, nome: 'Passeio' }, { id: 2, nome: 'Carga' },];

  constructor(
    private _actr: ActivatedRoute,
    private _service: ProdutoService,
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
        this.config = new CrudConfig(['/pag', 'produto']);
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
      ativo: [null, []],
      observacao: [null, []],
      modelo: [null, []],
      numeroSerie: [null, []],
    });
  }

  public getRoute(): Route {
    return this._router.config.find(v => v.path == 'pag')['_loadedConfig'].routes.find(v => v.path == 'produto');
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
    console.log('Incluir');
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