import { NgModule } from "@angular/core";
import { RegistroComponent } from './registro.component';
import { RegistroComponentRoutingModule } from './registro-routing.module';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [RegistroComponent],
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
        RegistroComponentRoutingModule
    ],
})
export class RegistroComponentModule {
}