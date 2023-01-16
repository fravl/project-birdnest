import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ViolationReport } from '../models/violation-report.model';
import { SseService } from './sse.service';

@Injectable({
  providedIn: 'root',
})
export class ViolationService {
  private readonly url = environment.baseUrl + 'violations';

  constructor(private _zone: NgZone, private _sseService: SseService) {}

  getServerSentEvent(): Observable<ViolationReport> {
    return new Observable((observer) => {
      const evs = this._sseService.getEventSource(this.url);

      evs.onmessage = (event) => {
        this._zone.run(() => {
          let violationReport = JSON.parse(event.data) as ViolationReport;
          observer.next(violationReport);
        });
      };

      evs.onerror = (error) => {
        this._zone.run(() => {
          observer.error(error);
        });
      };
    });
  }
}
