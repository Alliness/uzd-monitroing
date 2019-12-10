import {StationModel} from "app/page/trains/station.model";

export class TrainsRequestModel {

    constructor(
        private from: StationModel,
        private to: StationModel,
        private dateFrom: string,
        private dateUntil: string,
        private time: string
    ) {
    }

    toJson(): Object {
        return {
            date: this.dateFrom,
            dateUntil: this.dateUntil,
            from: this.from.value,
            to: this.to.value,
            time: this.time
        }
    }
}