import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogoComponent } from './dialogo.component';

@Injectable({
  providedIn: 'root'
})
export class DialogoService {

  constructor(private _dialog: MatDialog) { }

  public confirme(_mensagem: string) {
    const dialogRef = this._dialog.open(DialogoComponent, {
      width: '350px',
      data: _mensagem
    });
    return dialogRef.afterClosed().toPromise();
  }

}
