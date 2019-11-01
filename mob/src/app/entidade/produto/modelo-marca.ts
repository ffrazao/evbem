import { Modelo } from './modelo';
import { Marca } from './marca';

export class ModeloMarca {

    constructor(
        public id?: number,
        public modelo?: Modelo,
        public marca?: Marca,
    ){
    }
    
}
