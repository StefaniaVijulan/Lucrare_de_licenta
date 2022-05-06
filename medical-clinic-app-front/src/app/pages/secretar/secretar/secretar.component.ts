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
    this.allHospitalization();
    // this.currentList = this.allHospit();
  }

  openAddDialog() {
    this.dialog.open(DialogAddPacientComponent, {
      width: '50%'
    }).afterClosed().subscribe(val => {
      console.log(val)
      if (val === "saveP") {
        this.allHospitalization();
      }
    });
  };



  allHospitalization() {

    this._secretar.getAllHospitalization().subscribe((response: any) => {
      this.listHospitalization = response
      this.pacientL = new Array < Pacient > (this.listHospitalization.length)
      this.cardiologL = new Array < Cardiolog > (this.listHospitalization.length)
      for (let i = 0; i < this.listHospitalization.length; i++) {
        this._secretar.getSpecificP(this.listHospitalization[i].registrationNoHospitalization).subscribe((data: any) => {
          this.pacientL[i] = data
        })
        
      }
      console.log("list hospi")
      this._secretar.hospitaliationListService = this.listHospitalization;
      console.log(this._secretar.hospitaliationListService)
      console.log("list pacient")
      this._secretar.pacientListService = this.pacientL
      console.log(this._secretar.pacientListService)
      console.log("list cardio")
      this._secretar.doctorListService = this.cardiologL
      console.log(this._secretar.doctorListService)
    })
  };

  readMore(element: string) {
    this._secretar.cnpP = element
    this._secretar.doctorListService = this._secretar.getSpecificD().subscribe((res)=>{})
    console.log("doctor specific")
    console.log(this._secretar.doctorListService)
    this._secretar.moreInfoP(this._secretar.cnpP).subscribe({
      next: (data) => {
        this._secretar.pacient = data
       
        this.dialog.open(DialogMoreInfoPacientComponent, {
          width: '40%',
          data: element
        }).afterClosed().subscribe(val => {
          
          if (val === "done") {
            this.allHospitalization();
          }
        })
      },
      error: () => {
        console.log("eroare")
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
 
  onPageChange($event) {
    this.currentList = this.listHospitalization.slice($event.pageIndex * $event.pageSize, $event.pageIndex * $event.pageSize + $event.pageSize);
  }
  afis(elem: string) {
    this._secretar.getAfis(elem).subscribe({
      next: (res) => {
        if (res == null) {
          this.msg = " Pacientul nu exisat"
          console.log(res)
        } else {
          console.log(res)

        }
      },
      error: (err) => {
        console.log(err)

      }
    })
  }

}