import { Violation } from './violation.model';

export interface ViolationReport {
  droneCaptureTimestamp: Date;
  violations: Violation[];
}
