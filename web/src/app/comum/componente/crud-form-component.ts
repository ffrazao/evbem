import { Component } from "@angular/core";
import { CrudComponent } from './crud-component';
import { FormGroup } from '@angular/forms';
import { CrudConfig } from './crud-config';

@Component({})
export abstract class CrudFormComponent extends CrudComponent {
  
  constructor() {
    super();
  }

}