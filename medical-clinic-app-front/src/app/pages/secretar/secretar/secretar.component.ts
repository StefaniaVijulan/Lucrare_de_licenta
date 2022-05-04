import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FlexLayoutModule} from '@angular/flex-layout';
@Component({
  selector: 'app-secretar',
  templateUrl: './secretar.component.html',
  styleUrls: ['./secretar.component.scss']
})
export class SecretarComponent implements OnInit {
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  constructor() { }

  ngOnInit() {
  }

  
}
