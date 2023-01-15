import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViolationListContainerComponent } from './violation-list-container.component';

describe('ViolationListContainerComponent', () => {
  let component: ViolationListContainerComponent;
  let fixture: ComponentFixture<ViolationListContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViolationListContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViolationListContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
