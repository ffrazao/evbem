import { Usuario } from './usuario';
import { Confirmacao } from './confirmacao';
import { Perfil } from './perfil';

export interface UsuarioPerfil {
    id: number;
    usuario: Usuario;
    perfil: Perfil;
    ativo: Confirmacao;
    padrao: Confirmacao;
}