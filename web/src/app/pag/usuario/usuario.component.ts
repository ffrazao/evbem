import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudComponent } from 'src/app/comum/componente/crud-component';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { UsuarioService } from './usuario.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent extends CrudComponent implements OnInit {

  // seleçao
  public registro: FormGroup;

  constructor(
    protected _service: UsuarioService,
    protected _router: Router,
    protected _actr: ActivatedRoute,
    private _formBuilder: FormBuilder
  ) {
    super(_router, _actr, ['/pag', 'usuario']);
  }

  ngOnInit() {
    console.log('Embutindo o registro');
    super.ngOnInit();
    this.registro = this._formBuilder.group({
      id: [null, []],
      nome: ['', [Validators.required, Validators.maxLength(255)]],
      login: ['', [Validators.required, Validators.maxLength(255)]],
      tipo: ['', [Validators.required, Validators.maxLength(255)]],
      email: ['', [Validators.required, Validators.maxLength(255), Validators.email]],
      ativo: ['', [Validators.required]],
    });

    this._actr.snapshot.data.registro.email = '123@123';
    this.registro.patchValue(this._actr.snapshot.data.registro);
  }

  salvar() {
    this._service.salvar(this.registro.value)
      .subscribe(
      registro => {
        this.registro.setValue(registro);
      }
    );
  }

}