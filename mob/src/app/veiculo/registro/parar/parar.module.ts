import { NgModule } from '@angular/core';
import { PararComponent } from './parar.component';
import { PararComponentRoutingModule } from './parar-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@NgModule({
    declarations: [PararComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        PararComponentRoutingModule
    ],
    providers: [
        Geolocation,
    ]
})
export class PararComponentModule {
}
