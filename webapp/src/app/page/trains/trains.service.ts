import {Injectable} from "@angular/core";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {TrainModel} from "app/page/trains/train.model";
import {StationModel} from "app/page/trains/station.model";
import {TrainsRequestModel} from "app/page/trains/trains.request.model";

@Injectable({providedIn: 'root'})
export class TrainsService {

    private routes;

    constructor(protected http: HttpClient) {
        this.routes = {
            stations: '/api/stations',
            trains: '/api/trains'
        }
    }

    public getStations(term: string): Observable<HttpResponse<StationModel[]>> {
        return this.http.get<StationModel[]>(this.routes.stations, {params: {'term': term}, observe: 'response'});
    }

    public getTrains(form: TrainsRequestModel): Observable<HttpResponse<TrainModel[]>> {
        return this.http.post<TrainModel[]>(this.routes.trains, form.toJson(), {observe: 'response'})
    }
}