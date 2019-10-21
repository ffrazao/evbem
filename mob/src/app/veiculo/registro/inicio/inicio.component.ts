import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';

@Component({
    templateUrl: './inicio.component.html',
    styleUrls: ['./inicio.component.scss']
})
export class InicioComponent {

    constructor(
        private barcodeScanner: BarcodeScanner,
        private router: Router,
        private route: ActivatedRoute
    ) {
    }

    iniciar() {
        this.barcodeScanner.scan().then(barcodeData => {
            console.log('Barcode data', barcodeData);
            if (barcodeData && barcodeData.text && barcodeData.text.trim().length) {
            }
            this.router.navigate(['/', 'veiculo', 'registro', 'registro'], {relativeTo: this.route});
           }).catch(err => {
               console.log('Error', err);
           });
    }

}
