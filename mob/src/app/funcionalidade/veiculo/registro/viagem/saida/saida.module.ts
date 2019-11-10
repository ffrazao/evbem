import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';
import { LongPressModule } from 'ionic-long-press/dist';

import { SaidaComponent } from './saida.component';
import { SaidaComponentRoutingModule } from './saida-routing.module';
import { ServicoLocalModule } from '../../../../../comum/servico/local/servico-local.module';
import { EscolheItemComponentModule } from '../../../../../comum/componente/escolhe-item/escolhe-item.module';

@NgModule({
    declarations: [SaidaComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        LongPressModule,
        SaidaComponentRoutingModule,
        ServicoLocalModule,
        EscolheItemComponentModule,
    ],
    exports: [
        SaidaComponent
    ],
    providers: [
        BarcodeScanner,
        Geolocation,
    ],
    entryComponents: [
        SaidaComponent
    ],
})
export class SaidaComponentModule {}
