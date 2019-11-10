import { Injectable } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ViagemPage } from './viagem.page';

@Injectable({providedIn: 'root'})
export class ViagemService {

    constructor(
        private modalCtrl: ModalController
    ) {
    }

    public async exibe(
        ): Promise<any> {
        return new Promise<any>(async (resolve, reject) => {
            const modal = await this.modalCtrl.create({
                component: ViagemPage,
                componentProps: {
                }
            });
            // modal.onDidDismiss().then((dados) => {
            //     if (dados.data !== null) {
            //         resolve(dados.data);
            //     } else {
            //         reject();
            //     }
            // });
            return await modal.present();
        });
    }

}