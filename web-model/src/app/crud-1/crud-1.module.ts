import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { Crud1Component } from './crud-1.component';
import { Crud1RoutingModule } from './crud-1-routing.module';
import { ListComponent } from './list/list.component';
import { FormComponent } from './form/form.component';

@NgModule({
    declarations: [
        Crud1Component,
        ListComponent,
        FormComponent,
    ],
    imports: [
        CommonModule,
        Crud1RoutingModule,
    ]
})
export class Crud1Module { }