import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EscolheItem } from './escolhe-item';
import { ModalController } from '@ionic/angular';
import { MensagemService } from '../../servico/mensagem/mensagem.service';

@Component({
    selector: 'app-escolhe-item',
    templateUrl: 'escolhe-item.component.html',
    styleUrls: ['escolhe-item.component.scss'],
})
export class EscolheItemComponent implements OnInit {

    private form: FormGroup;

    @Input() items: any[];
    itemsFiltrados: any[];

    @Input() camposPesq: string[];

    @Input() campoIcone: string;

    @Input() campoTitulo: string;

    @Input() campoDescricao: string;

    @Input() varios = false;

    constructor(
        private formBuilder: FormBuilder,
        private modalController: ModalController,
        private mensagem: MensagemService,
    ) { }

    ngOnInit() {
        this.itemsFiltrados = JSON.parse(JSON.stringify(this.items));
        this.form = this.createForm(new EscolheItem());
    }

    private createForm(init: EscolheItem): FormGroup {
        return this.formBuilder.group({
            valor: [init.valor, Validators.required],
        });
    }

    public pesquisaItem(ev) {
        this.itemsFiltrados = this.items.filter((i) =>
            this.camposPesq.map((cp) =>
                i[cp].toLowerCase().indexOf(ev.detail.value.trim().toLowerCase()) >= 0
            ).filter(ii => ii === true).length > 0);
    }

    public async confirmar() {
        if (!this.form.valid) {
            this.mensagem.erro('Dados inválidos!');
            return;
        }
        this.modalController.dismiss(this.form);
    }

    public cancelar() {
        this.modalController.dismiss();
    }

}
