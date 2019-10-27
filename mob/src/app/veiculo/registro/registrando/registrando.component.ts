import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@Component({
    templateUrl: './registrando.component.html',
    styleUrls: ['./registrando.component.scss']
})
export class RegistrandoComponent implements OnInit {

    private posicao: any;

    constructor(
        private barcodeScanner: BarcodeScanner,
        private router: Router,
        private route: ActivatedRoute,
        private geo: Geolocation,
    ) {
    }

    async ngOnInit() {
        this.posicao = await this.geo.getCurrentPosition();
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
