import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { AppointmentsRadiology } from 'src/app/interfaces/appointmentRadiology';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-appointments-radiology',
  templateUrl: './appointments-radiology.component.html',
  styleUrls: ['./appointments-radiology.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class AppointmentsRadiologyComponent implements OnInit {


  expandedElement: AppointmentsRadiology | null;
  columnsToDisplay = ['dataAppointmentRadiology','hourAppointmentRadiology','cnpP', 'firstName', 'lastName'];
  dataSource !: MatTableDataSource<any>;


  @ViewChild(MatPaginator, {static: true}) 
  paginator!: MatPaginator;

  @ViewChild(MatSort, {static: true}) 
  sort!: MatSort;
  constructor( 
    public _moderator: ModeratorService ) {
       
       }

  ngOnInit(): void {
    this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
    this.allAppointmentRadiology();
  }
  setRoleCase(data){
    for(let item of data){
      item.role = item.role.substring(0,1)+ item.role.substring(1).toLowerCase()
      //console.log(item.role)
    }
  }
  allAppointmentRadiology(){
    return this._moderator.getAllAppointmentsR()
    .subscribe((res:any)=>{
      console.log("response")
     this.dataSource =  new MatTableDataSource(res);
     this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
    }
   )};

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }}