import { CrudConfig } from './crud-config';
import { Route } from '@angular/router';

export abstract class CrudComponent {

  private _config: CrudConfig;

  constructor() {}

  get config() : CrudConfig {
    return this._config;
  }

  set config(_config : CrudConfig) {
    this._config = _config;
  }

  abstract getRoute(): Route;

}