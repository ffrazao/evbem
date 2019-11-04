import { NgModule } from '@angular/core';
import { EscolheItemService } from './escolhe-item.service';
import { EscolheItemComponent } from './escolhe-item.component';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        IonicModule,
        CommonModule,
        FormsModule,
    ],
    declarations: [EscolheItemComponent],
    providers: [EscolheItemService],
    entryComponents: [EscolheItemComponent]
})
export class EscolheItemComponentModule {
}
