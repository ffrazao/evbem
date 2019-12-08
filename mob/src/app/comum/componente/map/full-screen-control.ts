import * as L from 'leaflet';
import 'leaflet-easybutton';

export class FullScreenControl extends L.Control.EasyButton {

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