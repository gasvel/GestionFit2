import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarClasesAlumnoComponent } from './agregar-clases-alumno.component';

describe('AgregarClasesAlumnoComponent', () => {
  let component: AgregarClasesAlumnoComponent;
  let fixture: ComponentFixture<AgregarClasesAlumnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgregarClasesAlumnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgregarClasesAlumnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
