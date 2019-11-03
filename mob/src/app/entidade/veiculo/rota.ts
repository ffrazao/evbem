import { Sincrono } from '../../entidade/sincrono';
import { Viagem } from './viagem';

export class Rota implements Sincrono {

    constructor(
        public id?: number,
        public viagem?: Viagem,
        public momento?: Date,
        public ponto?: string,
        public velocidade?: number,
        public idServidor?: number,
        public sincronizado?: Date,
        public apagar?: boolean,
    ) {

    }

}
