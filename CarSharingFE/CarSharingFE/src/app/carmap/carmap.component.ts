import { Component, OnInit} from '@angular/core';
declare var L: any;

@Component({
  selector: 'app-carmap',
  templateUrl: './carmap.component.html',
  styleUrls: ['./carmap.component.css']
})
export class CarmapComponent implements OnInit{
  constructor() {}

  ngOnInit(): void {
    const map = L.map('map').setView([51.505, -0.09], 13); // Imposta la posizione e lo zoom iniziale

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    // Aggiungi marcatori per i veicoli disponibili
    const vehicleMarker = L.marker([51.505, -0.09]).addTo(map);
  }
}

