import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatTableModule } from '@angular/material/table';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ViolationListComponent } from './components/violation-list/violation-list.component';
import { DateToTimeStringPipe } from './pipes/date-to-time-string.pipe';

@NgModule({
  declarations: [AppComponent, ViolationListComponent, DateToTimeStringPipe],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
