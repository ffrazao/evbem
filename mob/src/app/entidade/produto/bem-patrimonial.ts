import { Pessoa } from '../principal/pessoa';

export class BemPatrimonial {

    constructor(
        private id?: number,
        private observacao?: string,
        private siglaProprietario?: string,
        private identificacaoPatrimonial?: string,
        private pessoa?: Pessoa,
    ) {
    }

}