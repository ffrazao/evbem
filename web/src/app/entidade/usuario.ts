import { UsuarioPerfil } from './usuario-perfil';
import { Confirmacao } from './confirmacao';

export interface Usuario {
    id: number;
    nome: string;
    login: string;
    email: string;
    tipo: string;
    ativo: Confirmacao;
    usuarioPerfilList: UsuarioPerfil[];
}