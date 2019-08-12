import { Confirmacao } from './confirmacao';

export interface Produto {
    id: number;
    nome: string;
    login: string;
    email: string;
    tipo: string;
    ativo: Confirmacao;
}