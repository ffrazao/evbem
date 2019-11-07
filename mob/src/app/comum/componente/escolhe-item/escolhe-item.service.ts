import { Injectable } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { EscolheItemComponent } from './escolhe-item.component';

@Injectable({ providedIn: 'root' })
export class EscolheItemService {

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
        varios = false,
        exibeVarios = false,
    ): Promise<any> {
        return new Promise<any>(async (resolve, reject) => {
            const modal = await this.modalCtrl.create({
                component: EscolheItemComponent,
                componentProps: {
                    items,
                    camposPesq,
                    campoTitulo,
                    campoDescricao,
                    campoIcone,
                    varios,
                    exibeVarios,
                }
            });
            modal.onDidDismiss().then((dados) => {
                if (dados.data !== null) {
                    resolve(dados.data);
                } else {
                    reject();
                }
            });
            return await modal.present();
        });
    }

}
