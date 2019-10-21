import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

export abstract class ApiService {

    private url1 = environment.apiUrl;

    get url() {
        return this.url1;
    }

}
