import { Component, OnInit } from '@angular/core';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation } from '@ionic-native/geolocation/ngx';

@Component({
    templateUrl: './registro.component.html',
    styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {

    private veiculo = null;

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

    public pesquisar() {
        this.veiculo = { id: 1 };
    }

    public pesquisarPorQRCode() {
        this.barcodeScanner.scan().then((r) => {
            console.log('Barcode data', r);
            if (r && r.text && r.text.trim().length) {
                // this.router.navigate(['/', 'veiculo', 'registro', 'registro'], {relativeTo: this.route});
            }
        }).catch(err => {
            console.log('Error', err);
        });
    }

}
