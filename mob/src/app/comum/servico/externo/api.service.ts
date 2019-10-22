import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

export abstract class ApiService {

    private url1 = environment.apiUrl;

    constructor(protected http: HttpClient) {
    }

    public get url() {
        return this.url1;
    }

}
