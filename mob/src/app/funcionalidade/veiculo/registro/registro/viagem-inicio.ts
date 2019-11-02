import { Veiculo } from '../../../../entidade/veiculo/veiculo';
import { Pessoa } from '../../../../entidade/principal/pessoa';
import { Local } from '../../../../entidade/comum/local';
import { UnidadeOrganizacional } from '../../../../entidade/funcional/unidade-organizacional';

export class ViagemInicio {
    veiculo = new Veiculo();
    dia = (new Date()).toISOString();
    condutor = new Pessoa();
    localSaida = new Local(0, 0);
    hora = '22:00';
    odometro = 0;
    responsavelVeiculo = new Pessoa();
    lotacaoVeiculo = new UnidadeOrganizacional();
}
