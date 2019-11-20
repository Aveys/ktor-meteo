# Ktor-meteo
Projet de workshop kotlin

**Librairies:**
* Ktor (Webserver 100% kotlin)
## Data
### Stations
POKO de station de mesure méteo
```JSON
{
  "id": 1,
  "nom": "Station St-Clair",
  "location": "Laval"
}
```

### Points
POKO de points de mesure de température (Utiliser le kelvin et les Extensions Int pour la conversion)
```JSON
{
  "id": 1,
  "stationNumber": 1,
  "date": "2019-11-20",
  "value": 280
}
```

### Endpoints
Voir Application.kt
* CRD pour les Stations (GET DELETE POST)
* CR pour les points
* 1 route de stats