import { Pilot } from './pilot.model';

export interface Violation {
  id: number;
  lastSeen: Date;
  closestDistanceToNest: number;
  pilot: Pilot;
}
