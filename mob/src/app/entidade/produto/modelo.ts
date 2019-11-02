import { Tipo } from './tipo';

export class Modelo {

    constructor(
        public id?: number,
        public codigo?: string,
        public nome?: string,
        public descricao?: string,
        public tipo?: Tipo,
    ){
    }
    
}