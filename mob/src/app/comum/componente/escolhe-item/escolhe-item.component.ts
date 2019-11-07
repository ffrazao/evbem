import { Component, Input, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { MensagemService } from '../../servico/mensagem/mensagem.service';

@Component({
    selector: 'app-escolhe-item',
    templateUrl: 'escolhe-item.component.html',
    styleUrls: ['escolhe-item.component.scss'],
})
export class EscolheItemComponent implements OnInit {

    @Input() private valor: any;

    @Input() private items: any[];
    private itemsFiltrados: any[];

    @Input() private camposPesq: string[];

    @Input() private campoIcone: string;

    @Input() private campoTitulo: string;

    @Input() private campoDescricao: string;

    @Input() private varios = false;

    @Input() private exibeVarios = false;

    private indeterminado: boolean;
    private tudo: boolean;

    constructor(
        private modalController: ModalController,
        private mensagem: MensagemService,
    ) { }

    ngOnInit() {
        this.itemsFiltrados = JSON.parse(JSON.stringify(this.items));
    }

    marcaTudo() {
        setTimeout(() => {
            this.itemsFiltrados.forEach(obj => {
                obj.seleciona = this.tudo;
            });
        });
    }

    verificaMarcado() {
        const totalItems = this.itemsFiltrados.length;
        let checked = 0;
        this.itemsFiltrados.map(obj => {
            if (obj.seleciona) checked++;
        });
        if (checked > 0 && checked < totalItems) {
            //If even one item is checked but not all
            this.indeterminado = true;
            this.tudo = false;
        } else if (checked == totalItems) {
            //If all are checked
            this.tudo = true;
            this.indeterminado = false;
        } else {
            //If none is checked
            this.indeterminado = false;
            this.tudo = false;
        }
    }

    public pesquisaItem(ev) {
        this.itemsFiltrados = this.items.filter((i) =>
            this.camposPesq.map((cp) =>
                i[cp].toLowerCase().indexOf(ev.detail.value.trim().toLowerCase()) >= 0
            ).filter(ii => ii === true).length > 0);
    }

    private selecionados() {
        return this.items.filter(i => i.seleciona);
    }

    private invalido() {
        return (this.varios && !this.selecionados().length)
            || (!this.varios && this.valor == null);
    }

    public async confirmar() {
        if (this.invalido()) {
            this.mensagem.erro('Nenhum registro selecionado!');
            return;
        }
        this.modalController.dismiss(this.varios ? this.selecionados() : this.valor);
    }

    public cancelar() {
        this.modalController.dismiss();
    }

}
