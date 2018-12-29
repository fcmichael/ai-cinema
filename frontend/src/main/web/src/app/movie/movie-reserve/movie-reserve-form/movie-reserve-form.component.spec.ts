import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieReserveFormComponent } from './movie-reserve-form.component';

describe('MovieReserveFormComponent', () => {
  let component: MovieReserveFormComponent;
  let fixture: ComponentFixture<MovieReserveFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieReserveFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieReserveFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
