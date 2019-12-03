import { Component, ElementRef, ViewChild, OnInit, AfterViewInit, ViewEncapsulation } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-easybutton';

import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
// import { Map, tileLayer, marker, icon } from 'leaflet';

import { MensagemService } from '../../../../../comum/servico/mensagem/mensagem.service';
import { posicaoEmater } from '../../../../../comum/ferramenta/funcao';

@Component({
    selector: 'app-viagem-chegada',
    templateUrl: './chegada.component.html',
    styleUrls: ['./chegada.component.scss']
})
export class ChegadaComponent implements OnInit {

    @ViewChild('map', {static: false})
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
        const mapTileDefault =
            L.tileLayer('https://{s}.tile.thunderforest.com/transport/{z}/{x}/{y}.png?apikey=93fd0e6776254aa3997cd55752310e15');

        this.map = L.map(this.mapElement.nativeElement, {
            center: [-15.794797016134899, -47.9262052592602],
            zoom: 12,
            maxZoom: 18,
            layers: [mapTileDefault],
            zoomControl: false
        });

        this.map['attributionControl'].remove();

        new FullscreenControl('btn_fullscreen_map').addTo(this.map);

        setTimeout(() => {
            this.map.invalidateSize();
        }, 1000);
    }

    async ngOnInit() {
    //     try {
    //         this.form = this.createForm(new ViagemParar());
    //         this.initMap();
    //         console.log('captando posição atual');
    //         this.posicao = await this.geo.getCurrentPosition() as Geoposition;
    //     } finally {
    //         console.log(this.posicao);
    //         this.initMap();
    //     }
    }

    ionViewDidEnter() {
        setTimeout(() => {
            this.focaliza.setFocus();
        }, 500);
    }

    // private createForm(viagem: ViagemParar): FormGroup {
    //     return this.formBuilder.group({
    //         localChegada: [viagem.localChegada, []],
    //         hora: [viagem.hora, [Validators.required]],
    //         odometro: [viagem.odometro, [Validators.required, Validators.min(1)]],
    //         servico: [viagem.servico, [Validators.required]],
    //     });
    // }

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

export class FullscreenControl extends L.Control.EasyButton {

    public constructor(elementId: string) {
        super({
                id: elementId,
                states: [{
                    stateName: 'expand',
                    icon: 'fa-arrows-alt',
                    title: '',
                    onClick: (control, map) => {
                        control.state('minimize');

                        this.makeFullscreen(map.getContainer());

                        setTimeout(() => map.invalidateSize(), 300);
                    }
                    }, {
                    stateName: 'minimize',
                    icon: 'fa-compress',
                    title: '',
                    onClick: (control, map) => {
                        control.state('expand');

                        this.makeMinimize(map.getContainer());

                        setTimeout(() => map.invalidateSize(), 100);
                    }
                }],
                position: 'bottomright'
        });
    }

    private makeFullscreen(container: HTMLElement) {
        L.DomUtil.addClass(container, 'leaflet-pseudo-fullscreen');
    }

    private makeMinimize(container: HTMLElement) {
        L.DomUtil.removeClass(container, 'leaflet-pseudo-fullscreen');
    }

} 