import { Injectable } from '@angular/core';
import { ModalController } from '@ionic/angular';

import { EscolheItemComponent } from './escolhe-item.component';

@Injectable({ providedIn: 'root' })
export class EscolheItemService {

    constructor(
        private modalCtrl: ModalController
    ) {
    }

    public async escolhe(items: any[], varios = false): Promise<any> {
        const modal = await this.modalCtrl.create({
            component: EscolheItemComponent,
            componentProps: {
                items,
                varios
            }
        });
        return await modal.present();
    }

}
