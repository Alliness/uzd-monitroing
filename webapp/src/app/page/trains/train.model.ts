import {StationModel} from "app/page/trains/station.model";

export interface ITrain {

    from: DirectionModel;
    to: DirectionModel;
    number: string;
    travelTime: string;
    wagons: WagonModel[];
    totalPlaces: number;
}

export class TrainModel {
    constructor(
        public from: DirectionModel,
        public to: DirectionModel,
        public number: string,
        public travelTime: string,
        public wagons: WagonModel[],
        public totalPlaces: number
    ) {
    }
}

export class DirectionModel extends StationModel {
    constructor(public value: number, public title: string, public date: string, public time: string) {
        super(value, title);
    }
}

export class WagonModel {
    constructor(public places: number, public wagonClass: string, public wagonType: string) {
    }
}