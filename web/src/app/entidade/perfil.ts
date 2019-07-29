import { Confirmacao } from './confirmacao';

export interface Perfil {
    id: number;
    codigo: string;
    nome: string;
    descricao: string;
    administrador: Confirmacao;
    ativo: Confirmacao;
}