import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements OnInit {

  private config: any;

  constructor(
    private _fb: FormBuilder
  ) { }

  ngOnInit() {
    this.config = this.criarConfig();
  }

  criarConfig(): any {
    return {
      filtro: this.criarFiltro(),
      tab: this.criarTab(),
      form: this.criarForm(),
      inserir() {
    
      },
      ver() {
        
      },
      excluir() {
        
      },
      editar() {
        
      },
      salvar() {
        
      },
    };
  }

  private criarFiltro() {
    return this._fb.group({
      nome: [null, []],
      login: [null, []],
      perfil: [null, []],
    });
  }

  private criarTab() {
    let result = [];
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'José da Silva', 
      login: 'jose.silva', 
      email: 'jose.silva@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Maria Souza', 
      login: 'maria.souza', 
      email: 'maria.souza@email.com', 
      usuarioPerfilList: [
        {id: 2, perfil: {id: 2, nome: 'COMUM'}},
      ], 
    });
    result.push({
      id: 0, 
      nome: 'Paulo Jorge', 
      login: 'paulo.jorge', 
      email: 'paulo.jorge@email.com', 
      usuarioPerfilList: [
        {id: 1, perfil: {id: 1, nome: 'ADMINISTRADOR'}},
      ], 
    });
    return result;
  }

  public criarForm() {
    return this._fb.group({
      id: [null, []],
      nome: [null, [Validators.required, Validators.maxLength(255)]],
      login: [null, [Validators.maxLength(255)]],
      email: [null, [Validators.maxLength(255)]],
      tipo: [null, [Validators.required]],
      ativo: [null, [Validators.required]],
      usuarioPerfilList: [null, []],
    });
  }

}