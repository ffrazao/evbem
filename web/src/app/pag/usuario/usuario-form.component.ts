import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { FormBuilder, Validators } from '@angular/forms';
import { UsuarioService } from './usuario.service';
import { CrudFormComponent } from 'src/app/comum/componente/crud-form-component';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent extends CrudFormComponent implements OnInit {

  constructor(
    protected _actr: ActivatedRoute,
    protected _router: Router,
    private _formBuilder: FormBuilder,
    protected _service: UsuarioService,
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

    if (this._actr.snapshot.data.config) {
      this.ids = this._actr.snapshot.data.config.lista.idList;
      this.pos = this._actr.snapshot.data.config.lista.pos;
    }
  }

  salvar() {
    this._service.salvar(this.formulario.value).subscribe(f => this.formulario.setValue(f));
  }

  public vaiPara(pos): void {
    pos = ("" + pos).trim();
    if ((pos || pos == 0) && !isNaN(pos)) {
      let url = this._urlPrincipal.slice(0);
      //url.push(this.ids.join());
      url.push(this.ids[pos]);
      this._router.navigate(url);
    }
  }

}