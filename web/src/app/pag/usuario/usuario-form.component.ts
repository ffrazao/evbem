import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { UsuarioService } from './usuario.service';
import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent extends CrudFormComponent implements OnInit {

  public formulario: FormGroup;

  constructor(
    protected _router: Router,
    protected _actr: ActivatedRoute,
    protected _service: UsuarioService,
    private _formBuilder: FormBuilder
  ) {
    super(['/pag', 'usuario']);
  }

  ngOnInit() {
    console.log('form');
    // construir o formulário
    this.formulario = this._formBuilder.group({
      id: [null, []],
      nome: [null, [Validators.required, Validators.maxLength(255)]],
      login: [null, [Validators.required, Validators.maxLength(255)]],
      tipo: [null, [Validators.required, Validators.maxLength(255)]],
      email: [null, [Validators.required, Validators.maxLength(255), Validators.email]],
      ativo: [null, [Validators.required]],
    });
    // carregar o formulário
    this.formulario.patchValue(this._actr.snapshot.data.formulario);
  }

  salvar() {
    this._service.salvar(this.formulario.value).subscribe(f => this.formulario.setValue(f));
  }

}