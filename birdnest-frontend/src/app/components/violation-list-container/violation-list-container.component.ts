import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ViolationReport } from 'src/app/models/violation-report.model';
import { ViolationService } from 'src/app/services/violation.service';

@Component({
  selector: 'app-violation-list-container',
  templateUrl: './violation-list-container.component.html',
  styleUrls: ['./violation-list-container.component.css'],
})
export class ViolationListContainerComponent implements OnInit {
  violations$: Observable<ViolationReport> =
    this.violationService.returnAsObservable();

  violationReport: ViolationReport = {} as ViolationReport;

  constructor(private violationService: ViolationService) {}

  ngOnInit(): void {
    this.violationService.connectViolationStream();
    this.violations$.subscribe(
      (violation) => (this.violationReport = violation)
    );
  }
}
