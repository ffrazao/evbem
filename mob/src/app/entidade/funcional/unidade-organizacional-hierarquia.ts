import { UnidadeOrganizacional } from './unidade-organizacional';

export class UnidadeOrganizacionalHierarquia {

    constructor(
        public id?: number,
        public unidadeOrganizacionalPrincipal?: UnidadeOrganizacional,
        public unidadeOrganizacional?: UnidadeOrganizacional,
        public tipo?: string) {
    }

}
