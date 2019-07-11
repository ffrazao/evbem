import { Component } from "@angular/core";
import { CrudComponent } from './crud-component';
import { FormGroup } from '@angular/forms';

@Component({})
export abstract class CrudFormComponent extends CrudComponent {

  protected formulario: FormGroup;

  constructor(
    protected _urlPrincipal: string[]
  ) {
    super(_urlPrincipal);
  }

}