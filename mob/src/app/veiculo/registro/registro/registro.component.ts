import { Component, OnInit, ViewChild } from '@angular/core';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { Map, tileLayer, marker, icon } from 'leaflet';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MensagemService } from 'src/app/comum/servico/mensagem/mensagem.service';
import { RegistroService } from './registro.service';
import { ViagemInicio } from './viagem-inicio';

@Component({
    templateUrl: './registro.component.html',
    styleUrls: ['./registro.component.scss']
})
export class RegistroComponent implements OnInit {

    private form: FormGroup;

    @ViewChild('focaliza', { static: false })
    private focaliza;

    private veiculo = null;

    private map = null;

    private customMarkerIcon = null;

    private posicao: Geoposition = this.posicaoEmater();

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private service: RegistroService,
        private formBuilder: FormBuilder,
        private mensagem: MensagemService,
        private geo: Geolocation,
        private barcodeScanner: BarcodeScanner,
    ) {
    }

    async ngOnInit() {
        try {
            this.form = this.createForm(new ViagemInicio(), 'JKO5353', 'Fernando Frazão da Silva');
            this.initMap();
            console.log('captando posição atual');
            this.posicao = await this.geo.getCurrentPosition() as Geoposition;
        } finally {
            console.log(this.posicao);
            this.initMap();
        }
    }

    ionViewDidEnter() {
        setTimeout(() => {
            this.focaliza.setFocus();
        }, 500);
    }

    private createForm(viagem: ViagemInicio, pesquisaVeiculo: string, pesquisaCondutor: string): FormGroup {
        return this.formBuilder.group({
            pesquisaVeiculo: [pesquisaVeiculo, [Validators.required]],
            veiculo: [viagem.veiculo, [Validators.required]],
            dia: [viagem.dia, [Validators.required]],
            pesquisaCondutor: [pesquisaCondutor, [Validators.required]],
            condutor: [viagem.condutor, [Validators.required]],
            localSaida: [viagem.localSaida, [Validators.required]],
            hora: [viagem.hora, [Validators.required]],
            odometro: [viagem.odometro, [Validators.required]],
            responsavelVeiculo: [viagem.responsavelVeiculo, [Validators.required]],
            lotacaoVeiculo: [viagem.lotacaoVeiculo, [Validators.required]],
        });
    }

    public async onSubmit() {
        if (!this.form.valid) {
            return;
        }

        // [routerLink]="['/s/veiculo-registrando']"

        // this.mensagem.aguarde().then((res) => {
        //     res.present();
        //     this.service.login(this.form.value as Login).subscribe((r) => {
        //         this.router.navigateByUrl(this.urlRetorno);
        //         this.mensagem.sucesso('Login efetuado!');
        //         res.dismiss();
        //     }, (e) => {
        //         console.log(e);
        //         this.mensagem.erro('Erro no servidor de autenticação!');
        //         res.dismiss();
        //     });
        // });
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
    }

    public qrCode() {
        this.barcodeScanner.scan().then((r) => {
            console.log('Barcode data', r);
            if (r && r.text && r.text.trim().length) {
                // this.router.navigate(['/', 'veiculo', 'registro', 'registro'], {relativeTo: this.route});
            }
        }).catch(err => {
            console.log('Error', err);
        });
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

    public diminuiOdometro() {
        let odometro: number = this.form.get('odometro').value as number;
        if (odometro != null) {
            this.form.get('odometro').setValue(--odometro);
        }
    }

    public aumentaOdometro() {
        let odometro: number = this.form.get('odometro').value as number;
        if (odometro != null) {
            this.form.get('odometro').setValue(++odometro);
        }
        console.log(this.form);
    }

}
