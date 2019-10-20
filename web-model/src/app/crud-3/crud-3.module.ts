import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { Crud3Component } from './crud-3.component';
import { Crud3RoutingModule } from './crud-3-routing.module';
import { ListComponent } from './list/list.component';
import { FormComponent } from './form/form.component';

@NgModule({
    declarations: [
        Crud3Component,
        ListComponent,
        FormComponent,],
    imports: [
        CommonModule,
        Crud3RoutingModule,
    ]
})
export class Crud3Module { }