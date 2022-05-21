import {
  Component,
  OnInit
} from '@angular/core';
import {
  MatDialog
} from '@angular/material';
import {
  DialogMoreInfoPacientComponent
} from 'src/app/components/dialog-more-info-pacient/dialog-more-info-pacient.component';
import {
  DoctorService
} from 'src/app/services/doctor/doctor.service';

@Component({
  selector: 'app-doctor-programari',
  templateUrl: './doctor-programari.component.html',
  styleUrls: ['./doctor-programari.component.scss']
})
export class DoctorProgramariComponent implements OnInit {

  listAppointmentsSpecific: any;
  constructor(public _doctor: DoctorService, private dialog: MatDialog, ) {}

  ngOnInit() {
    this.allAppointmentS();
  }
  allAppointmentS() {
    this._doctor.allTodayAppointementSpecifc().subscribe((res) => {
      this.listAppointmentsSpecific = res;
      console.log(res)
    })
  }
  readMoreDialog(element: string) {
    this._doctor.cnpCurrantpatientService = element;
    console.log("Cnp => ", this._doctor.cnpCurrantpatientService)
    this.dialog.open(DialogMoreInfoPacientComponent, {
      width: '40%',
      data: element
    })
  }

}