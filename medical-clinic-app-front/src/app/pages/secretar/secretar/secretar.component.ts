import {  Component,  OnInit,  ViewChild} from '@angular/core';
import {  FormGroup,  FormControl} from '@angular/forms';
import {  FlexLayoutModule} from '@angular/flex-layout';
import {  Hospitalization} from 'src/app/interfaces/hospitalization';
import {  SecretarService} from 'src/app/services/secretar/secretar.service';
import {  Pacient} from 'src/app/interfaces/pacient';
import {  timeStamp} from 'console';
import {  DialogMoreInfoPacientComponent} from 'src/app/components/dialog-more-info-pacient/dialog-more-info-pacient.component';
import {  MatDialog,  MatPaginator,  MatSort,  MatTableDataSource} from '@angular/material';
import {  DialogAddPacientComponent} from 'src/app/components/dialog-add-pacient/dialog-add-pacient.component';
import { Cardiolog } from 'src/app/interfaces/cardiolog';
@Component({
  selector: 'app-secretar',
  templateUrl: './secretar.component.html',
  styleUrls: ['./secretar.component.scss']
})
export class SecretarComponent implements OnInit {
  listHospitalization: any
  currentList: any;
  pacientL: any;
  cardiologL: any;
  msg: string="";
  displayedColumns = ['cnp', 'firstName', 'lastName', 'emailUser', 'numberUser', 'role', 'action'];
  dataSource!: MatTableDataSource < any > ;


  @ViewChild(MatPaginator, {
    static: true
  })
  paginator!: MatPaginator;

  @ViewChild(MatSort, {
    static: true
  })
  sort!: MatSort;
  constructor(public _secretar: SecretarService, private dialog: MatDialog, ) {}

  ngOnInit() {
    this.allHospitalizationActive();
  }

  allHospitalizationActive() {
    this._secretar.getAllHospitalizationActive().subscribe((response: any) => {
      console.log(response)
      this.listHospitalization = response;
    })
  };
  openAddDialog() {
    this.dialog.open(DialogAddPacientComponent, {
      width: '50%'
    }).afterClosed().subscribe(val => {
      console.log(val)
      if (val === "saveP") {
        this.allHospitalizationActive();
      }
    });
  }; 

  readMoreDialog(element: string) {
    //salvam idul internarii pentru care vrem sa aflam mai multe detalii
    this._secretar.hospitalizationNo = element
    console.log("noHospit =>" + this._secretar.hospitalizationNo)
    this.dialog.open(DialogMoreInfoPacientComponent, {
          width: '40%',
          data: element
        }).afterClosed().subscribe(val => {
          
          if (val === "done") {
            this.allHospitalizationActive();
          }
        })
  }
  externeazaPacient(element: string) {
    
    this._secretar.externarePacient(element).subscribe({
      next: (res) => {
        console.log(res)
      },
      error: (err) => {
        console.log(err)

      }
    })
    location.reload()
  }


}