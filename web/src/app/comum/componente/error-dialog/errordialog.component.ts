import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  templateUrl: './errordialog.component.html'
})
export class ErrorDialogComponent {

  title = 'Angular-Interceptor';

  constructor(
    @Inject(MAT_DIALOG_DATA)
    public data: string
  ) {
    console.log('constructor data', data);
  }
}