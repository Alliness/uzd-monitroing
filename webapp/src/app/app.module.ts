import {NgModule} from '@angular/core';
import {BrowserModule} from "@angular/platform-browser";
import {ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RootComponent} from "app/root/root.component";
import {RootModule} from "app/root/root.module";
import {RouterModule} from "@angular/router";
import {DatePipe} from "@angular/common";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
    imports: [NgbModule, HttpClientModule, FontAwesomeModule, BrowserModule, RootModule, ReactiveFormsModule, RouterModule],
    declarations: [RootComponent],
    bootstrap: [RootComponent],
    providers: [
        DatePipe,
        FormData
    ]
})
export class AppModule {

}
