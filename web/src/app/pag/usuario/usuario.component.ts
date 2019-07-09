import { Component, AfterViewInit, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

import { CrudComponent } from 'src/app/comum/componente/crud-component';
import { Usuario } from 'src/app/pag/usuario/usuario-list.component';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent extends CrudComponent implements OnInit, AfterViewInit {

  // seleçao
  public registro: FormGroup;

  constructor(
    protected _httpClient: HttpClient,
    protected _router: Router,
    protected _activatedRoute: ActivatedRoute,
    private _formBuilder: FormBuilder
  ) {
    super(_httpClient, _router, _activatedRoute, ['/pag', 'usuario']);
  }

  ngOnInit() {
    super.ngOnInit();
    this.registro = this._formBuilder.group({
      id: [null, []],
      nome: ['', [Validators.required, Validators.maxLength(255)]],
      login: ['', [Validators.required, Validators.maxLength(255)]],
      tipo: ['', [Validators.required, Validators.maxLength(255)]],
      email: ['', [Validators.required, Validators.maxLength(255), Validators.email]],
      ativo: ['', [Validators.required]],
    });
  }

  ngAfterViewInit() {
    console.log(1);
    let frz = this._activatedRoute.data.subscribe(v => console.log(v));

    if (this.id) {
      this._activatedRoute.url.subscribe(url => {
        this._httpClient.get<Usuario>('http://localhost:8080/usuario/' + this.id).subscribe(
          registro => {
            registro.email = '123@123';
            this.registro.patchValue(registro);
          }
        );
      });
    }
  }

  salvar() {
    this._httpClient.post<Usuario>('http://localhost:8080/usuario/', this.registro.value)
      .subscribe(
      registro => {
        this.registro.setValue(registro);
      }
    );
  }

}