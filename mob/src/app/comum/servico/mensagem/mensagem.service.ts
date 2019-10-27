import { Injectable } from '@angular/core';
import { AlertController, ToastController, LoadingController } from '@ionic/angular';

@Injectable({ providedIn: 'root' })
export class MensagemService {

    constructor(
        private alertCtrl: AlertController,
        private toastCtrl: ToastController,
        public loadingCtrl: LoadingController,
    ) {
    }

    public atencaoConfirme(mensagem: string): Promise<boolean> {
        return new Promise<boolean>(async (resolve, reject) => {
            const alert = await this.alertCtrl.create({
                header: 'Atenção!',
                message: mensagem,
                backdropDismiss: false,
                buttons: [
                    {
                        text: 'Ok',
                        role: null,
                        handler: () => {
                            resolve(true);
                            alert.dismiss(true);
                        }
                    },
                    {
                        text: 'Cancelar',
                        role: 'cancel',
                        handler: () => {
                            resolve(false);
                            alert.dismiss(false);
                        }
                    }
                ],
            });
            await alert.present();
        });
    }

    public atencao(mensagem: string): Promise<boolean> {
        return new Promise<boolean>(async (resolve, reject) => {
            const alert = await this.alertCtrl.create({
                header: 'Atenção!',
                message: mensagem,
                backdropDismiss: false,
                buttons: [
                    {
                        text: 'Ok',
                        role: 'cancel',
                        handler: () => {
                            resolve(true);
                            alert.dismiss(true);
                        }
                    },
                ],
            });
            await alert.present();
        });
    }

    public erro(mensagem: string): Promise<boolean> {
        return new Promise<boolean>(async (resolve, reject) => {
            const alert = await this.alertCtrl.create({
                header: 'Erro!',
                message: mensagem,
                cssClass: 'danger',
                buttons: [
                    {
                        text: 'Ok',
                        role: 'cancel',
                        handler: () => {
                            resolve(true);
                            alert.dismiss(true);
                        }
                    }
                ]
            });
            await alert.present();
        });
    }

    public async sucesso(mensagem: string) {
        const result = await this.toastCtrl.create({
            message: mensagem,
            duration: 2000,
            color: 'success',
        });
        return await result.present();
    }

    public async aguarde() {
        return await this.loadingCtrl.create({
            message: 'Aguarde...'
        });
    }

}
