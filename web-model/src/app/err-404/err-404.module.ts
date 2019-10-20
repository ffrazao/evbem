import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { Err404Component } from './err-404.component';
import { Err404RoutingModule } from './err-404-routing.module';

@NgModule({
    declarations: [ Err404Component],
    imports: [
        CommonModule, 
        Err404RoutingModule
    ]
})
export class Err404Module {}