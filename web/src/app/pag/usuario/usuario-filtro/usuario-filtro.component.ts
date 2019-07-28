import { Component, OnInit, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuario-filtro',
  templateUrl: './usuario-filtro.component.html',
  styleUrls: ['./usuario-filtro.component.scss']
})
export class UsuarioFiltroComponent implements OnInit {

  @Input()
  @Output()
  private filtro: FormGroup;

  constructor(
    private _formBuilder: FormBuilder,
    private _router: Router
    ) { }

  ngOnInit() {
    if (!this.filtro) {
      this.filtro = this.criarFormulario();
    }
  }

  criarFormulario() {
    return this._formBuilder.group({
      nome: [null, []],
      login: [null, []],
      perfil: [null, []],
    });
  }

  filtrar() {
    this.filtro.get('perfil').setValue('COMUM');
    alert('Filtrando...' + JSON.stringify(this.filtro.value));
    this._router.navigate(['/pag', 'usuario'], {skipLocationChange: true});
  }

}
