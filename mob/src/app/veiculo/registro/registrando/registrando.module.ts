import { NgModule } from "@angular/core";
import { RegistrandoComponent } from './registrando.component';
import { RegistrandoComponentRoutingModule } from './registrando-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [RegistrandoComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        RegistrandoComponentRoutingModule
    ],
})
export class RegistrandoComponentModule {
}