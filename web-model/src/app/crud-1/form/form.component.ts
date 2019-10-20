import { OnInit, Component, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.sass']
})
export class FormComponent implements OnInit, OnDestroy {

    private acao: string;
    private paramsInscricao: Subscription;
    private urlInscricao: Subscription;

    constructor(
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.urlInscricao = this.route.url.subscribe(r => {
            console.log;
            this.acao = (r.filter(v => v.path == 'novo').length) ? 'Inclusao' : 'Visualizacao';
        });
        this.paramsInscricao = this.route.params.subscribe(r => {
            console.log(r);
        });
    }

    ngOnDestroy() {
        this.urlInscricao.unsubscribe();
        this.paramsInscricao.unsubscribe();
    }

}