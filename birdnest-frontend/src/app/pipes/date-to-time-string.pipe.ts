import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateToTimeString',
})
export class DateToTimeStringPipe implements PipeTransform {
  transform(date: Date): string {
    let date2: Date = new Date(date);
    return date2.toLocaleTimeString();
  }
}
