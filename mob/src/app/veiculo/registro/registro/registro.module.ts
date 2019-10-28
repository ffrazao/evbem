import { NgModule } from '@angular/core';
import { RegistroComponent } from './registro.component';
import { RegistroComponentRoutingModule } from './registro-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@NgModule({
    declarations: [RegistroComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        RegistroComponentRoutingModule
    ],
    providers: [
        BarcodeScanner,
        Geolocation,
    ]
})
export class RegistroComponentModule {
}
