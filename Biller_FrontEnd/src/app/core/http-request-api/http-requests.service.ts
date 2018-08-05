import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ResponseContentType } from '@angular/http';
import 'rxjs/Rx';

@Injectable()
export class HttpRequestsService {

  private requestHeader: HttpHeaders;
  private requestOptions: Object;

  constructor(private http: HttpClient,
    private router: Router) {
    this.setHttpRequestOptions();
  }

  private getDefaultHttpRequestOptions() {
    let requestHeader = new HttpHeaders();
    requestHeader = requestHeader.set('Content-Type', 'application/json; charset=utf-8');
    let requestOptions = new Object({
      headers: requestHeader,
      withCredentials: true
    });
    return requestOptions;
  }

  private setHttpRequestOptions() {
    this.requestOptions = this.getDefaultHttpRequestOptions();
  }

  public setCustomHeader(customRequestHeader: HttpHeaders) {
    this.requestOptions = new Object({
      headers: customRequestHeader,
      withCredentials: true
    });
  }
  public revertCustomHeader() {
    this.setHttpRequestOptions();
  }

  public httpGet(requestUrl: string): Observable<any> {
    return this.http.get(requestUrl, this.requestOptions)
      .map(response => response)
      .catch(this.handleError.bind(this));
  }

  public httpPost(requestUrl: string, requestParams?: Object): Observable<any> {
    return this.http.post(requestUrl, requestParams, this.requestOptions)
      .map(response => response)
      .catch(this.handleError.bind(this));
  }

  public httpPut(requestUrl: string, requestParams?: Object): Observable<any> {
    return this.http.put(requestUrl, requestParams, this.requestOptions)
      .map(response => response)
      .catch(this.handleError.bind(this));
  }

  public httpDelete(requestUrl: string, queryParams?: Map<string, string>): Observable<any> {
    let httpParams: HttpParams = new HttpParams();
    if (queryParams) {
      queryParams.forEach((key: string, value: string) => {
        httpParams = httpParams.set(key, value);
      });
    }

    let requestOptions = new Object({
      params: httpParams,
      withCredentials: true
    });

    return this.http.delete(requestUrl, requestOptions)
      .map(response => response)
      .catch(this.handleError.bind(this));
  }

  public httpHead(requestUrl: string, queryParams?: Map<string, string>): Observable<any> {
    let httpParams: HttpParams = new HttpParams();
    if (queryParams) {
      queryParams.forEach((key: string, value: string) => {
        httpParams.set(key, value);
      });
    }

    let requestOptions = new Object({
      params: httpParams,
      withCredentials: true
    });

    return this.http.head(requestUrl, requestOptions)
      .map(response => response)
      .catch(this.handleError.bind(this));
  }

  private handleError(error: Response | any) {
    console.log(error);
    console.log('Http-Request Error: ', error.status + ' - ' + error.statusText);
    switch (error.status) {
      case 500:
        break;
      case 401:
        this.router.navigate(['']);
        break;
    }
    return Observable.throw(error);
  }

}
