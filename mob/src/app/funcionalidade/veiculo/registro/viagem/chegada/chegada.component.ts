import { Component, ElementRef, ViewChild, OnInit, AfterViewInit, ViewEncapsulation } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-easybutton';

import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
// import { Map, tileLayer, marker, icon } from 'leaflet';

import { MensagemService } from '../../../../../comum/servico/mensagem/mensagem.service';
import { posicaoEmater } from '../../../../../comum/ferramenta/funcao';
import { FullScreenControl } from '../../../../../comum/componente/map/full-screen-control';
import { Viagem } from 'src/app/entidade/veiculo/viagem';

@Component({
    selector: 'app-viagem-chegada',
    templateUrl: './chegada.component.html',
    styleUrls: ['./chegada.component.scss']
})
export class ChegadaComponent implements OnInit, AfterViewInit {

    @ViewChild('map', { static: false })
    public mapElement: ElementRef;

    private map: L.Map;

    private form: FormGroup;

    @ViewChild('focaliza', { static: false })
    private focaliza;

    // private map = null;

    // private customMarkerIcon = null;

    private posicao: Geoposition = posicaoEmater();

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private mensagem: MensagemService,
        private geo: Geolocation,
    ) {
    }

    ngAfterViewInit() {
        this.initMap();
    }

    private initMap() {

        const mapTileDefault = L.tileLayer('https://{s}.tile.thunderforest.com/transport/{z}/{x}/{y}.png?apikey=93fd0e6776254aa3997cd55752310e15');

        this.map = L.map(this.mapElement.nativeElement, {
            center: [-15.794797016134899, -47.9262052592602],
            zoom: 12,
            maxZoom: 18,
            layers: [mapTileDefault],
            zoomControl: true
        });

        this.map['attributionControl'].remove();

        new FullScreenControl('btn_fullscreen_map').addTo(this.map);

        this.map.on('click', (e) => console.log('map click event', e));

        setTimeout(() => {
            this.map.invalidateSize();
        }, 1000);
    }

    async ngOnInit() {
        try {
            this.form = this.createForm(new Viagem());
            // this.initMap();
            // console.log('captando posição atual');
            // this.posicao = await this.geo.getCurrentPosition() as Geoposition;
        } finally {
            // console.log(this.posicao);
            // this.initMap();
        }
    }

    ionViewDidEnter() {
        setTimeout(() => {
            this.focaliza.setFocus();
        }, 500);
    }

    private createForm(viagem: Viagem): FormGroup {
        return this.formBuilder.group({
            localChegada: [viagem.localChegada, []],
            hora: [null, [Validators.required]],
            odometro: [null, [Validators.required, Validators.min(1)]],
            servico: [null, [Validators.required]],
        });
    }

    public async onSubmit() {
        if (!this.form.valid) {
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            /*            this.service.salvar(this.form.value as ViagemParar).subscribe((r) => {
                            this.router.navigate(['/m', 's', 'veiculo', 'viagem', 'inicio'], {relativeTo: this.route});
                            this.mensagem.sucesso('Viagem concluída!');
                            res.dismiss();
                        }, (e) => {
                            console.log(e);
                            this.mensagem.erro(e);
                            res.dismiss();
                        });*/
        });
    }

    // initMap2() {
    //     if (!this.posicao || !this.posicao.coords) {
    //         this.posicao = posicaoEmater();
    //     }

    //     if (!this.map) {
    //         this.map = new Map('localChegadaMap').setView([this.posicao.coords.latitude, this.posicao.coords.longitude], 23);
    //     }

    //     tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    //         attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    //     }).addTo(this.map);

    //     this.customMarkerIcon = icon({
    //         iconUrl: 'assets/img/custom-marker-icon.png',
    //         iconSize: [64, 64],
    //         popupAnchor: [0, -20]
    //     });

    //     marker([this.posicao.coords.latitude, this.posicao.coords.longitude],
    //         { icon: this.customMarkerIcon })
    //         .bindPopup(`<b>Local Chegada</b>`, { autoClose: false })
    //         .on('click', () => console.log('ai'))
    //         .addTo(this.map).openPopup();
    // }

    onPress($event, aumenta: boolean) {
        let odometro = this.form.get('odometro').value;
        if (odometro == null) {
            odometro = 0;
        }
        odometro = aumenta ? odometro + 1 : odometro - 1;
        this.form.get('odometro').setValue(odometro > 0 ? odometro : 1);
        console.log(this.form);
    }

}
