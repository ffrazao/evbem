import { NgModule } from '@angular/core';
import { ChegadaComponent } from './chegada.component';
import { ChegadaComponentRoutingModule } from './chegada-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@NgModule({
    declarations: [ChegadaComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        ChegadaComponentRoutingModule
    ],
    exports: [
        ChegadaComponent
    ],
    providers: [
        Geolocation,
    ],
    entryComponents: [
        ChegadaComponent
    ]
})
export class ChegadaComponentModule {
}
