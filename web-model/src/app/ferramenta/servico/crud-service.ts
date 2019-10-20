import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { FiltroDto } from '../entidade/filtro-dto';

@Injectable({ providedIn: 'root' })
export abstract class CrudService {

    private list: [];

    private form: any;

    constructor(private http: HttpClient) {
    }

    abstract serviceUrl(): string;

    criar() {
    }
    
    restorarById(id: number) {
        return this.http.get<[]>(`${environment.API_URL}${this.serviceUrl()}/${id}`);
    }

    restorarByFiltro(filtro: FiltroDto) {
        let params = new HttpParams;
        //params.append('filtro', filtro);
        return this.http.get<[]>(`${environment.API_URL}${this.serviceUrl()}`, {params});
    }

    update() {

    }

    delete() {

    }

}