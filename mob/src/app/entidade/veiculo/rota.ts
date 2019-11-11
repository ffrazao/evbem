import { Sincrono } from '../../entidade/sincrono';
import { Viagem } from './viagem';

export class Rota implements Sincrono {

    public idServidor?: number;
    public sincronizado?: Date;
    public apagar?: boolean;

    constructor(
        public id?: number,
        public viagem?: Viagem,
        public momento?: Date,
        public ponto?: string,
        private altitude?: number,
        private precisao?: number,
        private precisaoAltitude?: number,
        private direcao?: number,
        public velocidade?: number,
    ) {

    }

}
