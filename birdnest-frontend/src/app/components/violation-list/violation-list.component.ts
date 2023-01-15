import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ViolationReport } from 'src/app/models/violation-report.model';
import { ViolationService } from 'src/app/services/violation.service';

@Component({
  selector: 'app-violation-list',
  templateUrl: './violation-list.component.html',
  styleUrls: ['./violation-list.component.css'],
})
export class ViolationListComponent {
  @Input() violationReport: ViolationReport = {} as ViolationReport;

  constructor() {}
}