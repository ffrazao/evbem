import { Injectable } from "@angular/core";
import { CrudService } from '../ferramenta/servico/crud-service';

@Injectable({ providedIn: 'root' })
export class Crud1Service extends CrudService {

    serviceUrl(): string {        
        return '/crud1';
    }

}