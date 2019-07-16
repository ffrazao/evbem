import { Component } from "@angular/core";
import { CrudComponent } from './crud-component';
import { FormGroup } from '@angular/forms';
import { CrudConfig } from './crud-config';

@Component({})
export abstract class CrudFormComponent extends CrudComponent {

  private _formulario: FormGroup;

  constructor(
    protected _config: CrudConfig,
    protected _urlPrincipal: string[]
  ) {
    super(_config, _urlPrincipal);
  }

  get formulario(): FormGroup {
    return this._formulario;
  }

  set formulario(_formulario: FormGroup) {
    this._formulario = _formulario;
  }

}