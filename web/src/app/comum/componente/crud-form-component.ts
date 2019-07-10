import { Component } from "@angular/core";
import { CrudComponent } from './crud-component';

@Component({})
export class CrudFormComponent extends CrudComponent {

  constructor(
    protected _urlPrincipal: string[]
  ) {
    super(_urlPrincipal);
  }

}