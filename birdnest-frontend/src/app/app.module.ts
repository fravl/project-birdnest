import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViolationListComponent } from './components/violation-list/violation-list.component';
import { ViolationListContainerComponent } from './components/violation-list-container/violation-list-container.component';

@NgModule({
  declarations: [
    AppComponent,
    ViolationListComponent,
    ViolationListContainerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
