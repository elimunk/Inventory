import { Component, OnInit } from '@angular/core';
import { ItemService } from '../shared/services/item.service';
import { Item } from '../shared/models/item.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public item: Item;
  public items: Item[];
  public showItem: boolean;
  public showList: boolean;
  public amountInput: number;
  public showAddForm: boolean;
  public showFindForm: boolean;
  public titleText: string;
  public idToFind: number = 0;


  constructor(private itemService: ItemService) {
    this.item = new Item();
  }

  ngOnInit() {
    this.ShowHomeMessage('Please choose one of the options above');
  }


  addItem() {
    this.itemService.addItem(this.item).subscribe(
      res => {
        this.item = res;
        if (this.item) {
          alert(`Item no ${this.item.itemId} added sucseffuly`);
          this.item = new Item();
        }
      }, err => {
        alert('Error:' + err.status + '\n' + err.error.message);
      });
  }

  getItem() {
    this.itemService.getItem(this.idToFind).subscribe(
      res => {
        this.item = res;
        if (this.item) {
          this.showItem = true;
          this.showFindForm = false;
          this,this.idToFind=null;
        }
      }, err => {
        alert('Error:' + err.status + '\n' + err.error.message);
      });
  }

  getAllItems() {
    this.itemService.getAllItems().subscribe(
      res => {
        this.items = res;
        if (this.items.length > 0) {
          this.showTheList();
        } else {
          this.ShowHomeMessage('Inventory list is empty');
        }
      }, err => {
        alert('Error:' + err.status + '\n' + err.error.message);
      });
  }

  deleteItem(itemId: number) {
    let con = confirm("Are you shure you want to delete this Item?");
    if (con == true) {
    this.itemService.deleteItem(itemId).subscribe(
      res => {
        alert(`Item no ${itemId} deleted sucseffuly`);
        this.getAllItems();
      }, err => {
        alert('Error:' + err.status + '\n' + err.error.message);
      });
    }
  }

  deposit(itemId: number) {
    this.itemService.depositQuantity(itemId,  this.amountInput).subscribe(
      res => {
        alert(`Deposited (${this.amountInput}) successfully`);
        this.getAllItems();
      }, err => {
        alert('Error:' + err.status + '\n' + err.error.message);
      });
  }

  withdrawal(itemId: number) {
    this.itemService.withrawalQuantity(itemId, this.amountInput).subscribe(
      res => {
        alert(`Withdrawal (${this.amountInput}) successfully`);
        this.getAllItems();
      }, err => {
        alert('Error:' + err.status + '\n' + err.error.message);
      });
  }

  parseAmount(event: any): void {
    this.amountInput = JSON.parse(event.target.value);
  }


  ShowHomeMessage(text: string) {
    this.titleText = text;
    this.showList = false;
    this.showAddForm = false;
    this.showItem = false;
    this.showFindForm = false;
  }

  showTheList() {
    this.titleText = 'Inventory list';
    this.showList = true;
    this.showAddForm = false;
    this.showItem = false;
    this.showFindForm = false;
  }

  showTheAddForm() {
    this.titleText = 'Add item to the list';
    this.showAddForm = true;
    this.showList = false;
    this.showItem = false;
    this.showFindForm = false;
  }

  showFormFindItem() {
    this.showFindForm = true;
    this.titleText = `Find one Item`;
    this.showItem = false;
    this.showList = false;
    this.showAddForm = false;
  }

}
