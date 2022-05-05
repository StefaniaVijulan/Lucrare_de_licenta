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
@Component({
  selector: 'app-secretar',
  templateUrl: './secretar.component.html',
  styleUrls: ['./secretar.component.scss']
})
export class SecretarComponent implements OnInit {
  listHospitalization: any
  currentList: any;
  pacientL: any;

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
    this.allHospit();
    this.currentList = this.allHospit();
  }
  allHospit() {
    this._secretar.getAllHospitalization().subscribe((response: any) => {
      this.listHospitalization = response
      console.log(response[0].startDateHospitalization)
      this.pacientL = new Array < Pacient > (this.listHospitalization.length)
      for (let i = 0; i < this.listHospitalization.length; i++) {
        this._secretar.getSpecificP(this.listHospitalization[i].registrationNoHospitalization).subscribe((data: any) => {
          this.pacientL[i] = data
        })
      }
    })
  };

  readMore(element: string) {
    this._secretar.cnpP = element
    
    this._secretar.moreInfoH(this._secretar.cnpP).subscribe({
      next: (data) => {
        this._secretar.hospitalization = data
        console.log("in readMore")
        console.log(data)
        console.log(this._secretar.hospitalization)
      },
      error: () => {
        console.log("eroare")
      }
    });
    console.log("dupa info H")
    this._secretar.moreInfoP(this._secretar.cnpP).subscribe({
      next: (data) => {
        this._secretar.pacient = data
        console.log(data)
        this.dialog.open(DialogMoreInfoPacientComponent, {
          width: '40%',
          data: element
        }).afterClosed().subscribe(val => {
          console.log(val)
          if (val === "done") {
            this.allHospit();
          }
        })
      },
      error: () => {
        console.log("eroare")
      }
    })
  }
  externeazaPacient(element: string) {
    console.log("externeaza ts")
    console.log(element)
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
  openAddDialog() {
    this.dialog.open(DialogAddPacientComponent, {
      width: '50%'
    }).afterClosed().subscribe(val => {
      console.log(val)
      if (val === "saveP") {
        this.allHospit();
      }
    });
  };
  onPageChange($event) {
    this.currentList =  this.listHospitalization.slice($event.pageIndex*$event.pageSize, $event.pageIndex*$event.pageSize + $event.pageSize);
  }
}