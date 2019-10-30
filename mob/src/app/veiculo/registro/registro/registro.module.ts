import { NgModule } from '@angular/core';
import { RegistroComponent } from './registro.component';
import { RegistroComponentRoutingModule } from './registro-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';
import { LongPressModule } from 'ionic-long-press/dist';

@NgModule({
    declarations: [RegistroComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        LongPressModule,
        RegistroComponentRoutingModule
    ],
    providers: [
        BarcodeScanner,
        Geolocation,
    ]
})
export class RegistroComponentModule {
}
