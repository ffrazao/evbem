import { CrudConfig } from './crud-config';

export abstract class CrudComponent {
  
  constructor(
    protected _config: CrudConfig,
    protected _urlPrincipal: string[],
  ) {
    this._config.urlPrincipal = _urlPrincipal;
  }

  get config() : CrudConfig {
    return this._config;
  }

}