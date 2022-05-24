import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { AppointmentsHematology } from 'src/app/interfaces/appointmentHematology';
import { HematologService } from 'src/app/services/hematolog/hematolog.service';
import { ModeratorService } from 'src/app/services/moderator/moderator.service';

@Component({
  selector: 'app-hematolog-all-result',
  templateUrl: './hematolog-all-result.component.html',
  styleUrls: ['./hematolog-all-result.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class HematologAllResultComponent implements OnInit {


    expandedElement: AppointmentsHematology | null;
    columnsToDisplay = ['dataAppointmentHematology','hourAppointmentHematology','cnpP', 'firstName', 'lastName'];
    dataSource !: MatTableDataSource<any>;
  
  
    @ViewChild(MatPaginator, {static: true}) 
    paginator!: MatPaginator;
  
    @ViewChild(MatSort, {static: true}) 
    sort!: MatSort;
    constructor( 
      public _hematolog: HematologService ) {
         
         }
  
    ngOnInit(): void {
      this.paginator._intl.itemsPerPageLabel="Numarul de elemente afisate:";
      this.allResult();
    }
 
    
    allResult(){
      console.log("intra in all appointment")
      return this._hematolog.getAllResult()
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
