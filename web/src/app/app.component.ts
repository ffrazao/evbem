import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { CrudConfig } from './comum/componente/crud-config';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  titulo = 'EvBem';
  envName = environment.envName;

  constructor(
    private _router: Router,
    private _formBuilder: FormBuilder
  ) { }

  public abreUsuario() {
    console.log('ahhh');
    let r = this._router.config.find(v => v.path == 'pag')['_loadedConfig'].routes.find(v => v.path == 'usuario');
    r.data.config = new CrudConfig(['/pag', 'usuario']);
    r.data.config.filtro = this._formBuilder.group({
      nome: [null, []],
      login: ['agata', []],
      perfil: [null, []],
    }); 
    this._router.navigate(['/pag', 'usuario']);
  }
}
