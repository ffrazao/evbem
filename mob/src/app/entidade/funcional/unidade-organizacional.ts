import { Empregador } from './empregador';
import { UnidadeOrganizacionalTipo } from './unidade-organizacional-tipo';
import { GrupoSocial } from '../pessoa/grupo-social';

export class UnidadeOrganizacional extends GrupoSocial {

    constructor(
        public id?: number,
        public nome?: string,
        public empregador?: Empregador,
        public unidadeOrganizacionalTipo?: UnidadeOrganizacionalTipo,
    ) {
        super(id, nome);
    }

}
