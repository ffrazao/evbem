import { Component, OnInit, ViewChild } from '@angular/core';
import { BarcodeScanner } from '@ionic-native/barcode-scanner/ngx';
import { Router, ActivatedRoute } from '@angular/router';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import { Map, tileLayer, marker, icon } from 'leaflet';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { MensagemService } from '../../../../../comum/servico/mensagem/mensagem.service';
import { posicaoEmater } from '../../../../../comum/ferramenta/funcao';
import { ViagemDaoLocal } from '../../../../../dao/local/veiculo/viagem-dao.local';
import { VeiculoDao } from '../../../../../dao/externo/veiculo/veiculo-dao';
import { VeiculoFiltroDto } from '../../../../../transporte/veiculo/veiculo.filtro.dto';
import { ViagemDao } from '../../../../../dao/externo/veiculo/viagem-dao';
import { PessoaDao } from '../../../../../dao/externo/principal/pessoa-dao';
import { PessoaFiltroDto } from '../../../../../transporte/principal/pessoa.filtro.dto';
import { EscolheItemService } from '../../../../../comum/componente/escolhe-item/escolhe-item.service';
import { LoginService } from '../../../../../comum/componente/login/login.service';
import { UsuarioLocal } from '../../../../../comum/componente/login/usuario-local';
import { Pessoa } from '../../../../../entidade/principal/pessoa';
import { Viagem } from 'src/app/entidade/veiculo/viagem';

@Component({
    selector: 'app-viagem-saida',
    templateUrl: './saida.component.html',
    styleUrls: ['./saida.component.scss']
})
export class SaidaComponent implements OnInit {

    private form: FormGroup;

    @ViewChild('focaliza', { static: false })
    private focaliza;

    private map = null;

    private customMarkerIcon = null;

    private posicao: Geoposition = posicaoEmater();

    constructor(
        private router: Router,
        private route: ActivatedRoute,
        private servico: ViagemDao,
        private servicoVeiculo: VeiculoDao,
        private servicoPessoa: PessoaDao,
        private servicoLocal: ViagemDaoLocal,
        private formBuilder: FormBuilder,
        private mensagem: MensagemService,
        private geo: Geolocation,
        private barcodeScanner: BarcodeScanner,
        private escolheItem: EscolheItemService,
        private loginService: LoginService,
        private pessoaDao: PessoaDao,
    ) {
    }

    async ngOnInit() {
        try {
            const viagemInicio = new Viagem();
            const veiculo = '1234';
            
            let condutor = '';
            const usuarioLocal = this.loginService.token;
            if (usuarioLocal.pessoaId > 0) {
                const pessoa = await this.pessoaDao.restaurar([usuarioLocal.pessoaId]);
                if (pessoa != null) {
                    // viagemInicio.condutor = pessoa[0] as Pessoa;
                    // condutor = viagemInicio.condutor.nome;
                }
            }

            this.form = this.createForm(viagemInicio, veiculo, condutor);
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

    private createForm(viagem: Viagem, pesquisaVeiculo: string, pesquisaCondutor: string): FormGroup {
        return this.formBuilder.group({
            pesquisaVeiculo: [pesquisaVeiculo, [Validators.required]],
            // veiculo: [viagem.veiculo, [Validators.required]],
            // dia: [viagem.dia, [Validators.required]],
            pesquisaCondutor: [pesquisaCondutor, [Validators.required]],
            // condutor: [viagem.condutor, [Validators.required]],
            localSaida: [viagem.localSaida, [Validators.required]],
            // hora: [viagem.hora, [Validators.required]],
            // odometro: [viagem.odometro, [Validators.required, Validators.min(1)]],
            // responsavelVeiculo: [viagem.responsavelVeiculo, [Validators.required]],
            // lotacaoVeiculo: [viagem.lotacaoVeiculo, [Validators.required]],
        });
    }

    public salvar() {
        if (!this.form.valid) {
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            this.servico.salvar([this.form.value as Viagem]).subscribe((r) => {
                this.router.navigate(['/', 's', 'veiculo-registrando'], { relativeTo: this.route });
                this.mensagem.sucesso('Viagem iniciada!');
                res.dismiss();
            }, (e) => {
                console.log(e);
                this.mensagem.erro(e);
                res.dismiss();
            });
        });
    }

    initMap() {
        if (!this.posicao || !this.posicao.coords) {
            this.posicao = posicaoEmater();
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

    public pesquisarVeiculo() {
        if (!this.form.get('pesquisaVeiculo').value) {
            this.mensagem.erro('Conteúdo da pesquisa não informado!');
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            // new [this.form.value as ViagemInicio]
            const filtro = new VeiculoFiltroDto();
            filtro.pesq = [];
            filtro.pesq[0] = this.form.get('pesquisaVeiculo').value;
            this.servicoVeiculo.listar(filtro).subscribe(async (r) => {
                res.dismiss();

                if (!r || r.length == 0) {
                    this.mensagem.atencao('Registro não encontrado!');
                    return;
                } else if (r && r.length > 1) {
                    let escolhe = await this.escolheItem.escolhe(r, ['placa'], 'placa', null, null);
                    if (escolhe) {
                        this.form.get('veiculo').setValue(escolhe);
                    } else {
                        this.mensagem.aviso('Nenhum registro não selecionado!');
                        return;
                    }
                } else {
                    this.form.get('veiculo').setValue(r[0]);
                }
                this.mensagem.sucesso('Registro encontrado!');
            }, (e) => {
                res.dismiss();
                console.log(e);
                this.mensagem.erro(JSON.parse(e));
            });
        });
    }

    public pesquisarCondutor() {
        if (!this.form.get('pesquisaCondutor').value) {
            this.mensagem.erro('Conteúdo da pesquisa não informado!');
            return;
        }

        this.mensagem.aguarde().then((res) => {
            res.present();
            // new [this.form.value as ViagemInicio]
            const filtro = new PessoaFiltroDto();
            filtro.pesq = [];
            filtro.pesq[0] = encodeURI(`%${this.form.get('pesquisaCondutor').value}%`);
            this.servicoPessoa.listar(filtro).subscribe(async (r) => {
                res.dismiss();

                if (!r || r.length == 0) {
                    this.mensagem.atencao('Registro não encontrado!');
                    return;
                } else if (r && r.length > 1) {
                    let escolhe = await this.escolheItem.escolhe(r, ['nome'], 'nome', null, null);
                    if (escolhe) {
                        console.log('escolido ==> ', escolhe);
                        this.form.get('condutor').setValue(escolhe);
                    } else {
                        this.mensagem.aviso('Nenhum registro não selecionado!');
                        return;
                    }
                } else {
                    console.log(r[0]);
                    this.form.get('condutor').setValue(r[0]);
                }
                this.mensagem.sucesso('Registro encontrado!');
            }, (e) => {
                res.dismiss();
                console.log(e);
                this.mensagem.erro(JSON.parse(e));
            });
        });
    }

    public qrCode() {
        this.barcodeScanner.scan().then((r) => {
            if (r && r.text && r.text.trim().length) {
                this.form.get('pesquisaVeiculo').setValue(r.text);
                this.pesquisarVeiculo();
            } else {
                this.mensagem.aviso('Conteúdo da pesquisa não informado!');
            }
        }).catch(err => {
            console.log('Error', err);
        });
    }

    public onPress($event, aumenta: boolean) {
        let odometro = this.form.get('odometro').value;
        if (odometro == null) {
            odometro = 0;
        }
        odometro = aumenta ? odometro + 1 : odometro - 1;
        this.form.get('odometro').setValue(odometro > 0 ? odometro : 1);
        console.log(this.form);
    }

}
