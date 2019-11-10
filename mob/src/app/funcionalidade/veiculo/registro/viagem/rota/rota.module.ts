import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RotaComponent } from './rota.component';
import { RotaComponentRoutingModule } from './rota-routing.module';

@NgModule({
    declarations: [RotaComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        RotaComponentRoutingModule,
    ],
    exports: [
        RotaComponent
    ],
    providers: [],
    entryComponents: [
        RotaComponent
    ]
})
export class RotaComponentModule {}
