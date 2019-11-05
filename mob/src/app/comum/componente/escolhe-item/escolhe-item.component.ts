import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-escolhe-item',
    templateUrl: 'escolhe-item.component.html',
    styleUrls: ['escolhe-item.component.scss'],
})
export class EscolheItemComponent {

    @Input() items: any[];

    @Input() camposPesq: string[];

    @Input() campoIcone: string;

    @Input() campoTitulo: string;

    @Input() campoDescricao: string;

    @Input() varios = false;

    constructor() { }

    public pesquisaItem(ev) {
        return this.items.filter(i => this.camposPesq.map(
            cp => i[cp].toLowerCase().indexOf == ev.detail.value.trim().toLowerCase() > -1)
        ).filter(i => i === true).length;
    }

}
