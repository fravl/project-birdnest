import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ViolationReport } from '../models/violation-report.model';
import { SseService } from './sse.service';

@Injectable({
  providedIn: 'root',
})
export class ViolationService {
  private readonly url = 'http://localhost:8080/violations';
  private evs: EventSource = this._sseService.getEventSource(this.url);

  private subj = new BehaviorSubject<ViolationReport>({
    droneCaptureTimestamp: new Date(),
    violations: [],
  });

  constructor(private _sseService: SseService) {}

  returnAsObservable() {
    return this.subj.asObservable();
  }

  connectViolationStream(): void {
    this.evs.onopen = (_) => {
      console.log('Opening connection.Ready State is ' + this.evs.readyState);
    };

    this.evs.onmessage = (e) => {
      console.log('Message Received.Ready State is ' + this.evs.readyState);
      let jsonObj = JSON.parse(e.data);
      let violationReport = jsonObj as ViolationReport;
      console.log(violationReport);
      this.subj.next(violationReport);
    };

    this.evs.onerror = (e) => {
      console.log(e);
      if (this.evs.readyState == 0) {
        console.log('Reconnecting…');
      }
    };
  }

  disconnectViolationStream() {
    this.evs.close();
  }
}
