import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieReserveSuccessComponent } from './movie-reserve-success.component';

describe('MovieReserveSuccessComponent', () => {
  let component: MovieReserveSuccessComponent;
  let fixture: ComponentFixture<MovieReserveSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieReserveSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieReserveSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
