import { environment } from '../../../../environments/environment';

export class AutorizadorService {

    private url1 = environment.autorizadorUrl;

    get url() {
        return this.url1;
    }

}
