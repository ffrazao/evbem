import { NgModule } from '@angular/core';
import { MatButtonModule, MatCheckboxModule, MatMenuModule, MatIconModule } from '@angular/material';

@NgModule({
    imports: [MatMenuModule, MatIconModule, MatButtonModule, MatCheckboxModule],
    exports: [MatMenuModule, MatIconModule, MatButtonModule, MatCheckboxModule],
})
export class AngularMaterialModule { }