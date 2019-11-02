import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';
import { LongPressModule } from 'ionic-long-press/dist';

import { RegistroComponent } from './registro.component';
import { RegistroComponentRoutingModule } from './registro-routing.module';
import { ServicoLocalModule } from '../../../../comum/servico/local/servico-local.module';

@NgModule({
    declarations: [RegistroComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        LongPressModule,
        RegistroComponentRoutingModule,
        ServicoLocalModule,
    ],
    providers: [
        BarcodeScanner,
        Geolocation,
    ]
})
export class RegistroComponentModule {
}
