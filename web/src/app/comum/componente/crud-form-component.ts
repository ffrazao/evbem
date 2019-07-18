import { Component } from "@angular/core";
import { CrudComponent } from './crud-component';

@Component({})
export abstract class CrudFormComponent extends CrudComponent {
  
  constructor() {
    super();
  }

}