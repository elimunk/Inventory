import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../models/item.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  readonly baseUrl: string = 'http://localhost:8080/inventory/';

  constructor(private http: HttpClient) { }

  public addItem(item: Item): Observable<Item> {
    return this.http.post<Item>(`${this.baseUrl}`, item);
  }

  public getItem(itemId: number): Observable<Item> {
    return this.http.get<Item>(`${this.baseUrl}${itemId}`);
  }

  public getAllItems(): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.baseUrl}`);
  }

  public withrawalQuantity(itemId: number, amount: number): Observable<any> {
    return this.http.put<number>(`${this.baseUrl}withdrawal/${itemId}`, amount);
  }

  public depositQuantity(itemId: number, amount: number): Observable<any> {
    return this.http.put<number>(`${this.baseUrl}deposit/${itemId}`, amount);
  }

  public deleteItem(itemId: number): Observable<Item[]> {
    return this.http.delete<Item[]>(`${this.baseUrl}${itemId}`);
  }

}
