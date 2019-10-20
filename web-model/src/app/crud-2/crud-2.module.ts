import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { Crud2Component } from './crud-2.component';
import { Crud2RoutingModule } from './crud-2-routing.module';
import { ListComponent } from './list/list.component';
import { FormComponent } from './form/form.component';

@NgModule({
    declarations: [
        Crud2Component,
        ListComponent,
        FormComponent,],
    imports: [
        CommonModule,
        Crud2RoutingModule,
    ]
})
export class Crud2Module { }