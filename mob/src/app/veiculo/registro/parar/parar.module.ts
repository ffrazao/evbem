import { NgModule } from '@angular/core';
import { PararComponent } from './parar.component';
import { PararComponentRoutingModule } from './parar-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [PararComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        PararComponentRoutingModule
    ],
})
export class PararComponentModule {
}
