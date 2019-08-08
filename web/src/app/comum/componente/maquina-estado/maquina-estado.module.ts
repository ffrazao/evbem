import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaquinaEstadoComponent } from './maquina-estado/maquina-estado.component';
import { VisaoComponent } from './visao/visao.component';

@NgModule({
  declarations: [MaquinaEstadoComponent, VisaoComponent],
  imports: [
    CommonModule
  ],
  exports: [MaquinaEstadoComponent, VisaoComponent],
  //entryComponents: [MaquinaEstadoComponent, VisaoComponent],
})
export class MaquinaEstadoModule { }
