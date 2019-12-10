import {Component, OnInit} from "@angular/core";
import {NgbCalendar, NgbDate, NgbDateParserFormatter} from "@ng-bootstrap/ng-bootstrap";
import {DatePipe} from "@angular/common";
import {faCalendar} from "@fortawesome/free-solid-svg-icons";
import {TrainsService} from "app/page/trains/trains.service";
import {Observable, of} from "rxjs";
import {catchError, debounceTime, distinctUntilChanged, map, switchMap} from "rxjs/operators";
import {StationModel} from "app/page/trains/station.model";
import {TrainsRequestModel} from "app/page/trains/trains.request.model";
import {ITrain} from "app/page/trains/train.model";

@Component({
    selector: 'app-trains',
    templateUrl: 'trains.component.html'
})
export class TrainsComponent implements OnInit {

    fromDate: NgbDate;
    toDate: NgbDate;
    form: TrainsRequestModel;
    public trains: ITrain[];
    calendarIcon = faCalendar;
    pickup: StationModel;
    dropoff: StationModel;

    constructor(private datePipe: DatePipe,
                private service: TrainsService,
                private formatter: NgbDateParserFormatter,
                private calendar: NgbCalendar) {

        this.trains = [];
        this.fromDate = calendar.getToday();
        this.toDate = calendar.getNext(calendar.getToday(), 'd', 1);
    }

    ngOnInit(): void {
    }

    transform(d: NgbDate, pattern: string): string {
        const date = new Date(d.year, d.month - 1, d.day);
        return this.datePipe.transform(date, pattern);
    }

    searchTrains() {


        this.form = new TrainsRequestModel(
            this.pickup,
            this.dropoff,
            this.transform(this.fromDate, 'yyyy-MM-dd'),
            this.transform(this.toDate, 'yyyy-MM-dd'),
            "00:00:00"
        );

        this.service.getTrains(this.form).subscribe(response => {
            this.trains = [];
            response.body.forEach(value => {
                this.trains.push(value);
            });
        })

    }

    validateInput(currentValue: NgbDate, input: string): NgbDate {
        const parsed = this.formatter.parse(input);
        if (parsed && this.calendar.isValid(NgbDate.from(parsed))) {
            return NgbDate.from(parsed);
        } else {
            return currentValue;
        }
    }

    searchStations = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(300),
            distinctUntilChanged(),
            switchMap(term =>
                this.service.getStations(term).pipe(
                    map(results => results.body),
                    catchError(err => of([]))
                )
            )
        );

    resultTemplate = (state: StationModel) => state.title;

    freeSpaces(index: number, item: ITrain) {
        return item.totalPlaces;
    }

    getWagonType(wagonType: string): string {
        switch (wagonType) {
            case 'П' :
                return 'Плацкарт';
            case 'Л' :
                return 'Люкс';
            case 'К' :
                return 'Купэ';
            case 'С':
                return 'Сидячий'
        }
    }

    getWagonCss(wagonType: string): string {
        switch (wagonType) {
            case 'П' :
                return 'btn-outline-warning';
            case 'Л' :
                return 'btn-outline-danger';
            case 'К' :
                return 'btn-outline-info';
            case 'С':
                return 'btn-outline-success';
        }
    }
}


