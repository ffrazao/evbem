import { NgModule } from "@angular/core";
import { InicioComponent } from './inicio.component';
import { InicioComponentRoutingModule } from './inicio-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [InicioComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        InicioComponentRoutingModule
    ],
})
export class InicioComponentModule {
}