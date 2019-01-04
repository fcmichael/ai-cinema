import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProgrammeComponent } from './admin-programme.component';

describe('AdminProgrammeComponent', () => {
  let component: AdminProgrammeComponent;
  let fixture: ComponentFixture<AdminProgrammeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminProgrammeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProgrammeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
