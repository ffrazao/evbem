import { Component, OnInit } from '@angular/core';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { Map, tileLayer, marker, icon } from 'leaflet';

@Component({
    templateUrl: './registro.component.html',
    styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {

    private veiculo = null;

    private map = null;

    private customMarkerIcon = null;

    private posicao: Geoposition = this.posicaoEmater();

    constructor(
        private barcodeScanner: BarcodeScanner,
        private router: Router,
        private route: ActivatedRoute,
        private geo: Geolocation,
    ) {
    }

    async ngOnInit() {
        try {
            //this.initMap();
            console.log('captando posição atual');
            this.posicao = await this.geo.getCurrentPosition() as Geoposition;
        } finally {
            console.log(this.posicao);
            //this.initMap();
        }
    }

    initMap() {
        if (!this.posicao || !this.posicao.coords) {
            this.posicao = this.posicaoEmater();
        }

        if (!this.map) {
            this.map = new Map('localSaidaMap').setView([this.posicao.coords.latitude, this.posicao.coords.longitude], 23);
        }

        tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(this.map);

        this.customMarkerIcon = icon({
            iconUrl: 'assets/img/custom-marker-icon.png',
            iconSize: [64, 64],
            popupAnchor: [0, -20]
        });

        marker([this.posicao.coords.latitude, this.posicao.coords.longitude],
            { icon: this.customMarkerIcon })
            .bindPopup(`<b>Local Saída</b>`, { autoClose: false })
            .on('click', () => console.log('ai'))
            .addTo(this.map).openPopup();
    }

    public pesquisar() {
        this.veiculo = { id: 1 };
        //this.initMap();
    }

    public pesquisarPorQRCode() {
        this.initMap();
        // this.barcodeScanner.scan().then((r) => {
        //     console.log('Barcode data', r);
        //     if (r && r.text && r.text.trim().length) {
        //         // this.router.navigate(['/', 'veiculo', 'registro', 'registro'], {relativeTo: this.route});
        //     }
        // }).catch(err => {
        //     console.log('Error', err);
        // });
    }

    private posicaoEmater() {
        return new Object({
            coords: {
                latitude: -15.7398319,
                longitude: -47.9122648,
                accuracy: null,
                altitude: null,
                altitudeAccuracy: null,
                heading: null,
                speed: null
            },
            timestamp: Date.now(),
        }) as Geoposition;
    }

}
