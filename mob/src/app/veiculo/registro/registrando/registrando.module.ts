import { NgModule } from '@angular/core';
import { RegistrandoComponent } from './registrando.component';
import { RegistrandoComponentRoutingModule } from './registrando-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@NgModule({
    declarations: [RegistrandoComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        RegistrandoComponentRoutingModule,
    ],
    providers: [
        BarcodeScanner,
        Geolocation,
    ]
})
export class RegistrandoComponentModule {
}
