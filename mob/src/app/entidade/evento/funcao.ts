import { Sincrono } from '../sincrono';

export class Funcao implements Sincrono {

    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public codigo?: string,
        public nome?: string,
        public pai?: Funcao,
    ) {
    }

}
