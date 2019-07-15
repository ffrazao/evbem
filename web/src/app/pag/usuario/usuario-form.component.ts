import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { FormBuilder, Validators } from '@angular/forms';
import { UsuarioService } from './usuario.service';
import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';
import { Usuario } from 'src/app/entidade/usuario';
import { CrudConfig } from 'src/app/comum/componente/crud-config';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent extends CrudFormComponent implements OnInit {

  constructor(
    protected _config: CrudConfig,
    protected _actr: ActivatedRoute,
    protected _router: Router,
    private _formBuilder: FormBuilder,
    protected _service: UsuarioService,
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
      this.formulario.reset();
      if (data.formulario) {
        this.formulario.patchValue(data.formulario);
      }
      if (data.config && !this.config.ids) {
        this.config.ids = data.config.lista.idList;
        this.config.pos = data.config.lista.pos;
      }
      this.config.novaPos = 1;
      if (!isNaN(this.config.pos)) {
        this.config.novaPos = this.config.pos + 1;
      } else {
        this.config.pos = 0;
      }
    });
  }

  salvar() {
    this._service.salvar(this.formulario.value).subscribe(f => this.formulario.setValue(f));
  }

}