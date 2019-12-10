import {NgModule} from "@angular/core";
import {NavbarComponent} from "app/root/navbar/navbar.component";
import {FooterComponent} from "app/root/footer/footer.component";
import {MainComponent} from "app/root/main/main.component";
import {TrainsComponent} from "app/page/trains/trains.component";
import {RouterModule} from "@angular/router";
import {NgbDatepickerModule, NgbTypeaheadModule} from "@ng-bootstrap/ng-bootstrap";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";


@NgModule({
    exports: [
        NavbarComponent,
        FooterComponent,
        MainComponent,
        TrainsComponent
    ],
    imports: [
        RouterModule.forRoot([
            {path: '', component: MainComponent},
            {path: 'trains', component: TrainsComponent}
        ]),
        NgbDatepickerModule,
        FontAwesomeModule,
        NgbTypeaheadModule,
        FormsModule,
        CommonModule
    ],
    declarations: [NavbarComponent, FooterComponent, MainComponent, TrainsComponent]
})
export class RootModule {

}