import { Geoposition } from '@ionic-native/geolocation/ngx';

export function iniciaCom(str1: string, str2: string): boolean {
    return str1.toLowerCase().substring(0, str2.length) === str2.toLowerCase();
}

export function posicaoEmater() {
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
