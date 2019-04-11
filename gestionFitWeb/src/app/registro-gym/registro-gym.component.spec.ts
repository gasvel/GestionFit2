import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroGymComponent } from './registro-gym.component';

describe('RegistroGymComponent', () => {
  let component: RegistroGymComponent;
  let fixture: ComponentFixture<RegistroGymComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistroGymComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistroGymComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
