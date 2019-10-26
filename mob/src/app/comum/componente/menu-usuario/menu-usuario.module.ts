import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { MenuUsuarioComponent } from './menu-usuario.component';
import { RouterModule } from '@angular/router';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule,
  ],
  exports: [MenuUsuarioComponent],
  declarations: [MenuUsuarioComponent],
  entryComponents: [MenuUsuarioComponent],
})
export class MenuUsuarioComponentModule {}
