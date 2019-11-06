import { Injectable } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { EscolheItemComponent } from './escolhe-item.component';

@Injectable({ providedIn: 'root' })
export class EscolheItemService {

    private resultadoP: any;

    constructor(
        private modalCtrl: ModalController
    ) {
    }

    public async escolhe(
        items: any[],
        camposPesq: string[],
        campoTitulo: string,
        campoDescricao: string,
        campoIcone: string,
        varios = false
    ): Promise<any> {
        const modal = await this.modalCtrl.create({
            component: EscolheItemComponent,
            componentProps: {
                items,
                camposPesq,
                campoTitulo,
                campoDescricao,
                campoIcone,
                varios
            }
        });
        modal.onDidDismiss().then((dados) => {
            if (dados !== null) {
                this.resultadoP = dados;
            }
        });
        return await modal.present();
    }

    public get resultado() {
        return this.resultadoP;
    }

}
