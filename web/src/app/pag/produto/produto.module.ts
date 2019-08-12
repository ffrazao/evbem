import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatFormFieldModule, MatInputModule, MatPaginatorModule, MatProgressSpinnerModule, MatRippleModule, MatTableModule, MatCheckboxModule, MatDialogModule } from '@angular/material';

import { ProdutoRoutingModule } from './produto-routing.module';
import { TabComponent } from './comp/tab/tab.component';
import { FormComponent } from './comp/form/form.component';
import { FiltroComponent } from './comp/filtro/filtro.component';
import { ResCriarService } from './servico/res-criar.service';
import { ResVerService } from './servico/res-ver.service';
import { ResListarService } from './servico/res-listar.service';

@NgModule({
  declarations: [TabComponent, FormComponent, FiltroComponent],
  imports: [
    CommonModule,
    ProdutoRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatRippleModule,
    MatTableModule,
    MatCheckboxModule,
    MatDialogModule,
  ],
  exports: [
    MatDialogModule
  ],
  providers: [ResCriarService, ResVerService, ResListarService]
})
export class ProdutoModule { }
